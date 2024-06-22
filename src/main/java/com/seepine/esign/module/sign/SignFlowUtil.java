package com.seepine.esign.module.sign;

import com.seepine.esign.common.ESignClient;
import com.seepine.esign.common.ESignException;
import com.seepine.esign.module.sign.file.SignFileKeywordPositionsReq;
import com.seepine.esign.module.sign.file.SignFileKeywordPositionsRes;
import com.seepine.esign.module.sign.file.SignFileUploadUtil;
import com.seepine.esign.module.sign.file.dto.KeywordPosition;
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
import com.seepine.tool.exception.ValidateRunException;
import com.seepine.tool.util.Objects;
import com.seepine.tool.util.Validate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Validate.nonBlank(dto.getOrgSignKeyword(), "企业盖章位置关键词不能为空");
    Validate.nonBlank(dto.getPsnSignKeyword(), "个人盖章位置关键词不能为空");
    Validate.nonBlank(dto.getOrgSealId(), "企业印章id不能为空");
    Validate.nonBlank(dto.getPsnPhone(), "个人手机号不能为空");
    Validate.nonBlank(dto.getPsnFullName(), "企业印章id不能为空");

    int idx = dto.getContractName().lastIndexOf(".");
    if (idx < 0) {
      throw new ValidateRunException("合同文件名必须包含后缀，例如 合同.docx");
    }
    String showTitle = dto.getContractName().substring(0, idx);

    // 上传获得 fileId
    String fileId =
        SignFileUploadUtil.uploadV3(eSignClient, dto.getContractName(), dto.getContractFile());

    SignFileKeywordPositionsRes postRes =
        eSignClient.execute(
            SignFileKeywordPositionsReq.builder()
                .fileId(fileId)
                .keywords(Arrays.asList(dto.getOrgSignKeyword(), dto.getPsnSignKeyword()))
                .build());
    if (!postRes.isSuccess()) {
      throw new ESignException(postRes.getMessage());
    }
    Optional<KeywordPosition> findFirst =
        postRes.getKeywordPositions().stream().filter(item -> !item.getSearchResult()).findFirst();
    if (findFirst.isPresent()) {
      throw new ESignException("合同模板中未找到印章坐标：" + findFirst.get().getKeyword());
    }
    KeywordPosition orgPosition = postRes.getKeywordPositions().get(0);
    KeywordPosition psnPosition = postRes.getKeywordPositions().get(1);

    List<Signer> signers =
        new ArrayList<>(
            Arrays.asList(
                Signer.builder()
                    // 机构
                    .signerType(1)
                    .signFields(
                        orgPosition.getPositions().stream()
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
                                                            item.getCoordinates()
                                                                    .get(0)
                                                                    .getPositionX()
                                                                + 50)
                                                        .positionY(
                                                            item.getCoordinates()
                                                                .get(0)
                                                                .getPositionY())
                                                        .build())
                                                .build())
                                        .build())
                            .collect(Collectors.toList()))
                    .build(),
                Signer.builder()
                    // 个人
                    .signerType(0)
                    .psnSignerInfo(
                        PsnSignerInfo.builder()
                            .psnAccount(dto.psnPhone)
                            .psnInfo(PsnInfo.builder().psnName(dto.psnFullName).build())
                            .build())
                    .signFields(
                        psnPosition.getPositions().stream()
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
                                                            item.getCoordinates()
                                                                    .get(0)
                                                                    .getPositionX()
                                                                + 50)
                                                        .positionY(
                                                            item.getCoordinates()
                                                                    .get(0)
                                                                    .getPositionY()
                                                                + 10)
                                                        .build())
                                                .build())
                                        .build())
                            .collect(Collectors.toList()))
                    .build()));
    // 是否骑缝章
    if (Boolean.TRUE.equals(dto.orgSealPagingAll)) {
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
                                  .positionPage(
                                      orgPosition.getPositions().get(0).getPageNum().toString())
                                  .positionX(
                                      orgPosition
                                              .getPositions()
                                              .get(0)
                                              .getCoordinates()
                                              .get(0)
                                              .getPositionX()
                                          + 50)
                                  .positionY(
                                      orgPosition
                                          .getPositions()
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
                        // 通知回调 .notifyUrl()
                        .build())
                .signers(signers)
                .build());
    if (!res.isSuccess()) {
      throw new ESignException(res.getMessage());
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

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignFlowRes {
    /** 签署流程ID（建议开发者保存此流程ID） */
    String signFlowId;
    /** 签署短链接（有效期180天） */
    String shortUrl;
    /**
     * 签署长链接（永久有效）
     *
     * <p>【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
     */
    String url;
  }

  /**
   * 查询流程结果
   *
   * @param eSignClient 连接实例
   * @param signFlowId 流程id
   * @return 查询结果，status和downloadUrl
   */
  public static SignFlowQuery query(ESignClient eSignClient, String signFlowId)
      throws ESignException {
    Validate.nonBlank(signFlowId, "流程id不能为空");
    SignFlowQueryDetailRes res =
        eSignClient.execute(SignFlowQueryDetailReq.builder().signFlowId(signFlowId).build());
    if (!res.isSuccess()) {
      throw new ESignException(res.getMessage());
    }
    // 机构签署方信息
    com.seepine.esign.module.sign.flow.querydetail.Signer.OrgSigner orgSigner =
        res.getSigners().get(0).getOrgSigner();
    // 个人签署方信息
    com.seepine.esign.module.sign.flow.querydetail.Signer.PsnInfo psnSigner =
        res.getSigners().get(1).getPsnSigner();
    if (res.isFinish()) {
      // 下载合同
      SignFlowFileDownloadUrlRes downloadUrlRes =
          eSignClient.execute(SignFlowFileDownloadUrlReq.builder().signFlowId(signFlowId).build());
      if (!downloadUrlRes.isSuccess()) {
        throw new ESignException(downloadUrlRes.getMessage());
      }
      return SignFlowQuery.builder()
          .signFlowStatus(res.getSignFlowStatus())
          // 只取签署文件第一个
          .downloadUrl(downloadUrlRes.getFiles().get(0).getDownloadUrl())
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

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignFlowQuery {
    /**
     * 当前流程的状态
     *
     * <p>0 - 草稿
     *
     * <p>1 - 签署中
     *
     * <p>2 - 完成
     *
     * <p>3 - 撤销
     *
     * <p>5 - 过期（签署截至日期到期后触发）
     *
     * <p>7 - 拒签
     */
    Integer signFlowStatus;

    /**
     * 是否完成签署
     *
     * @return true/false
     */
    public boolean isFinish() {
      return Integer.valueOf(2).equals(signFlowStatus);
    }
    /** 已签署文件下载链接（有效期为60分钟，过期后可以重新调用接口获取新的下载地址） */
    String downloadUrl;

    String orgId;
    String orgName;

    String psnId;
    String psnName;
    String psnPhone;
  }
}
