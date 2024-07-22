package com.seepine.esign.module.sign;

import com.seepine.esign.common.ESignClient;
import com.seepine.esign.common.ESignException;
import com.seepine.esign.module.sign.file.SignFileKeywordPositionsReq;
import com.seepine.esign.module.sign.file.SignFileKeywordPositionsRes;
import com.seepine.esign.module.sign.file.SignFileUploadUtil;
import com.seepine.esign.module.sign.file.dto.KeywordPosition;
import com.seepine.esign.module.sign.file.dto.Positions;
import com.seepine.esign.module.sign.flow.*;
import com.seepine.esign.module.sign.flow.create.Docs;
import com.seepine.esign.module.sign.flow.create.SignFlowConfig;
import com.seepine.esign.module.sign.flow.create.Signer;
import com.seepine.esign.module.sign.flow.create.enums.WillingnessAuthMode;
import com.seepine.esign.module.sign.flow.create.signer.PsnSignerInfo;
import com.seepine.esign.module.sign.flow.create.signer.SignField;
import com.seepine.esign.module.sign.flow.create.signer.field.NormalSignFieldConfig;
import com.seepine.esign.module.sign.flow.create.signer.field.SignFieldPosition;
import com.seepine.esign.module.sign.flow.create.signer.psn.PsnInfo;
import com.seepine.esign.module.sign.flow.create.signflowconfig.AuthConfig;
import com.seepine.esign.module.sign.flow.create.signflowconfig.RedirectConfig;
import com.seepine.esign.module.sign.flow.signurl.Operator;
import com.seepine.http.exception.HttpException;
import com.seepine.tool.exception.ValidateRunException;
import com.seepine.tool.util.Objects;
import com.seepine.tool.util.Retry;
import com.seepine.tool.util.Strings;
import com.seepine.tool.util.Validate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SignFlowUtil {

  /**
   * 创建签约
   *
   * @param eSignClient 连接实例
   * @param dto 数据dto
   * @return SignFlowRes {signFlowId,url}
   */
  public static SignFlowRes create(ESignClient eSignClient, SignFlowDto dto)
      throws ValidateRunException, ESignException {
    Validate.nonBlank(dto.getContractName(), "合同文件名不能为空");

    //    Validate.nonBlank(dto.getOrgSealId(), "企业印章id不能为空");
    //    Validate.nonBlank(dto.getPsnPhone(), "个人手机号不能为空");
    //    Validate.nonBlank(dto.getPsnFullName(), "个人姓名不能为空");

    int idx = dto.getContractName().lastIndexOf(".");
    if (idx < 0) {
      throw new ValidateRunException("合同文件名必须包含后缀，例如 合同.docx");
    }
    String showTitle = dto.getContractName().substring(0, idx);

    // 上传获得 fileId
    String fileId =
        SignFileUploadUtil.uploadV3(eSignClient, dto.getContractName(), dto.getContractFile());

    List<Positions> orgSignPositions =
        Objects.require(dto.getOrgSignPositions(), Collections.emptyList());
    List<Positions> psnSignPositions =
        Objects.require(dto.getPsnSignPositions(), Collections.emptyList());
    if (Objects.isEmpty(orgSignPositions) && Objects.isEmpty(psnSignPositions)) {
      boolean hasOrg = Objects.nonBlank(dto.orgSealId);
      boolean hasPsn = Objects.nonBlank(dto.psnPhone);
      List<String> keywords = new ArrayList<>();
      if (hasOrg) {
        Validate.nonBlank(dto.getOrgSignKeyword(), "企业盖章位置坐标和关键词不能都为空");
        keywords.add(dto.getOrgSignKeyword());
      }
      if (hasPsn) {
        Validate.nonBlank(dto.getPsnSignKeyword(), "个人盖章位置坐标和关键词不能都为空");
        keywords.add(dto.getPsnSignKeyword());
      }
      if (hasOrg || hasPsn) {
        SignFileKeywordPositionsRes postRes =
            eSignClient.execute(
                SignFileKeywordPositionsReq.builder().fileId(fileId).keywords(keywords).build());
        if (!postRes.isSuccess()) {
          throw new ESignException(postRes.getMessage());
        }
        Optional<KeywordPosition> findFirst =
            postRes.getKeywordPositions().stream()
                .filter(item -> !item.getSearchResult())
                .findFirst();
        if (findFirst.isPresent()) {
          throw new ESignException("合同模板中未找到印章坐标：" + findFirst.get().getKeyword());
        }
        if (hasOrg) {
          orgSignPositions = postRes.getKeywordPositions().get(0).getPositions();
          if (hasPsn) {
            psnSignPositions = postRes.getKeywordPositions().get(1).getPositions();
          }
        } else {
          psnSignPositions = postRes.getKeywordPositions().get(0).getPositions();
        }
      }
    }

    List<Signer> signers = new ArrayList<>();
    // 设置企业
    if (Objects.nonBlank(dto.getOrgSealId())) {
      Validate.isTrue(orgSignPositions.size() > 0, "企业盖章位置坐标和关键词不能都为空");
      signers.add(
          Signer.builder()
              // 机构
              .signerType(1)
              .signFields(
                  orgSignPositions.stream()
                      .map(
                          item ->
                              SignField.builder()
                                  .fileId(fileId)
                                  .normalSignFieldConfig(
                                      NormalSignFieldConfig.builder()
                                          .autoSign(true)
                                          .assignedSealId(dto.orgSealId)
                                          .signFieldStyle(1) // 1单页 2骑缝签章
                                          .signFieldPosition(
                                              SignFieldPosition.builder()
                                                  .positionPage(item.getPageNum().toString())
                                                  .positionX(
                                                      item.getCoordinates().get(0).getPositionX())
                                                  .positionY(
                                                      item.getCoordinates().get(0).getPositionY())
                                                  .build())
                                          .build())
                                  .build())
                      .collect(Collectors.toList()))
              .build());
    }
    // 设置个人
    if (Objects.nonBlank(dto.getPsnPhone())) {
      Validate.isTrue(psnSignPositions.size() > 0, "个人盖章位置坐标和关键词不能都为空");
      Validate.nonBlank(dto.psnFullName, "个人姓名不能为空");
      signers.add(
          Signer.builder()
              // 个人
              .signerType(0)
              .psnSignerInfo(
                  PsnSignerInfo.builder()
                      .psnAccount(dto.psnPhone)
                      .psnInfo(PsnInfo.builder().psnName(dto.psnFullName).build())
                      .build())
              .signFields(
                  psnSignPositions.stream()
                      .map(
                          item ->
                              SignField.builder()
                                  .fileId(fileId)
                                  .normalSignFieldConfig(
                                      NormalSignFieldConfig.builder()
                                          .signFieldStyle(1)
                                          .psnSealStyles(dto.getPsnSealStyles())
                                          .signFieldPosition(
                                              SignFieldPosition.builder()
                                                  .positionPage(item.getPageNum().toString())
                                                  .positionX(
                                                      item.getCoordinates().get(0).getPositionX())
                                                  .positionY(
                                                      item.getCoordinates().get(0).getPositionY())
                                                  .build())
                                          .build())
                                  .build())
                      .collect(Collectors.toList()))
              .build());
    }
    // 是否骑缝章
    if (Objects.nonBlank(dto.getOrgSealId()) && Boolean.TRUE.equals(dto.orgSealPagingAll)) {
      signers
          .get(0)
          .getSignFields()
          .add(
              SignField.builder()
                  .fileId(fileId)
                  .normalSignFieldConfig(
                      NormalSignFieldConfig.builder()
                          .autoSign(true)
                          .assignedSealId(dto.orgSealId)
                          .signFieldStyle(2) // 1单页 2骑缝签章
                          .signFieldPosition(
                              SignFieldPosition.builder()
                                  .positionPage(orgSignPositions.get(0).getPageNum().toString())
                                  .positionX(
                                      orgSignPositions
                                          .get(0)
                                          .getCoordinates()
                                          .get(0)
                                          .getPositionX())
                                  .positionY(
                                      orgSignPositions
                                          .get(0)
                                          .getCoordinates()
                                          .get(0)
                                          .getPositionY())
                                  .build())
                          .build())
                  .build());
    }
    SignFlowCreateByFileRes res =
        eSignClient.execute(
            SignFlowCreateByFileReq.builder()
                // 设置合同
                .docs(Collections.singletonList(Docs.builder().fileId(fileId).build()))
                .signFlowConfig(
                    SignFlowConfig.builder()
                        .signFlowTitle(showTitle)
                        .autoStart(true)
                        .autoFinish(true)
                        .authConfig(
                            AuthConfig.builder()
                                .willingnessAuthModes(
                                    Objects.require(
                                        dto.psnWillingnessAuthModes,
                                        Arrays.asList(
                                            WillingnessAuthMode.PSN_FACE_ALIPAY,
                                            WillingnessAuthMode.PSN_FACE_ESIGN,
                                            WillingnessAuthMode.PSN_FACE_WECHAT,
                                            // 腾讯云刷脸，需要联系交付顾问开通
                                            WillingnessAuthMode.PSN_FACE_TECENT)))
                                .build())
                        // 通知回调
                        .notifyUrl(dto.notifyUrl)
                        .build())
                .signers(signers)
                .build());
    if (!res.isSuccess()) {
      throw new ESignException(res.getMessage());
    }
    if (Objects.isBlank(dto.psnPhone)) {
      return SignFlowRes.builder().signFlowId(res.getSignFlowId()).build();
    }
    SignFlowSignUrlRes getUrlRes =
        eSignClient.execute(
            SignFlowSignUrlReq.builder()
                .signFlowId(res.getSignFlowId())
                .operator(Operator.builder().psnAccount(dto.psnPhone).build())
                .redirectConfig(RedirectConfig.builder().redirectUrl(dto.redirectUrl).build())
                .build());
    if (!getUrlRes.isSuccess()) {
      throw new ESignException(getUrlRes.getMessage());
    }
    return SignFlowRes.builder()
        .signFlowId(res.getSignFlowId())
        .shortUrl(getUrlRes.getShortUrl())
        .url(getUrlRes.getUrl())
        .build();
  }

  public static SignFlowQuery query(ESignClient eSignClient, String signFlowId) {
    return query(eSignClient, signFlowId, false);
  }
  /**
   * 查询流程结果
   *
   * @param eSignClient 连接实例
   * @param signFlowId 流程id
   * @return 查询结果，status和downloadUrl
   */
  public static SignFlowQuery query(ESignClient eSignClient, String signFlowId, boolean queryFace)
      throws ESignException {
    Validate.nonBlank(signFlowId, "流程id不能为空");
    SignFlowQueryDetailRes res;
    AtomicReference<Exception> theE = new AtomicReference<>();
    res =
        Retry.run(
            3,
            200,
            () -> {
              try {
                return eSignClient.execute(
                    SignFlowQueryDetailReq.builder().signFlowId(signFlowId).build());
              } catch (HttpException e) {
                theE.set(e);
                return null;
              }
            });
    if (res == null) {
      if (Objects.require(theE.get().getMessage(), Strings.EMPTY)
          .contains("unexpected end of stream on")) {
        throw new ESignException("网络异常，请重试");
      } else {
        throw new ESignException(theE.get());
      }
    }
    if (!res.isSuccess()) {
      throw new ESignException(res.getMessage());
    }
    // 机构签署方信息
    com.seepine.esign.module.sign.flow.querydetail.Signer.OrgSigner orgSigner = null;
    // 个人签署方信息
    com.seepine.esign.module.sign.flow.querydetail.Signer.PsnInfo psnSigner = null;
    if (res.getSigners().size() > 0) {
      orgSigner =
          res.getSigners().stream()
              .filter(item -> Objects.equals(item.getSignerType(), 1))
              .findFirst()
              .orElse(com.seepine.esign.module.sign.flow.querydetail.Signer.builder().build())
              .getOrgSigner();
      psnSigner =
          res.getSigners().stream()
              .filter(item -> Objects.equals(item.getSignerType(), 0))
              .findFirst()
              .orElse(com.seepine.esign.module.sign.flow.querydetail.Signer.builder().build())
              .getPsnSigner();
    }
    if (orgSigner == null) {
      orgSigner = com.seepine.esign.module.sign.flow.querydetail.Signer.OrgSigner.builder().build();
    }
    if (psnSigner == null) {
      psnSigner = com.seepine.esign.module.sign.flow.querydetail.Signer.PsnInfo.builder().build();
    }
    if (res.isFinish()) {
      // 下载合同
      SignFlowFileDownloadUrlRes downloadUrlRes =
          eSignClient.execute(SignFlowFileDownloadUrlReq.builder().signFlowId(signFlowId).build());
      if (!downloadUrlRes.isSuccess()) {
        throw new ESignException(downloadUrlRes.getMessage());
      }
      String facePhotoUrl = null;
      if (queryFace) {
        // 人脸
        SignFlowQueryFaceRes faceRes =
            eSignClient.execute(
                SignFlowQueryFaceReq.builder()
                    .flowId(signFlowId)
                    .accountId(psnSigner.getPsnId())
                    .build());
        faceRes.transfer();
        if (faceRes.isSuccess()) {
          facePhotoUrl = faceRes.getIdentityDetail().getFacePhotoUrl();
        }
      }
      return SignFlowQuery.builder()
          .signFlowStatus(res.getSignFlowStatus())
          // 只取签署文件第一个
          .downloadUrl(downloadUrlRes.getFiles().get(0).getDownloadUrl())
          .facePhotoUrl(facePhotoUrl)
          .orgId(orgSigner.getOrgId())
          .orgName(orgSigner.getOrgName())
          .psnId(psnSigner.getPsnId())
          .psnName(psnSigner.getPsnName())
          .psnPhone(psnSigner.getPsnAccount().getAccountMobile())
          .build();
    }
    return SignFlowQuery.builder()
        .signFlowStatus(res.getSignFlowStatus())
        .orgId(orgSigner.getOrgId())
        .orgName(orgSigner.getOrgName())
        .psnId(psnSigner.getPsnId())
        .psnName(psnSigner.getPsnName())
        .psnPhone(psnSigner.getPsnAccount().getAccountMobile())
        .build();
  }
}
