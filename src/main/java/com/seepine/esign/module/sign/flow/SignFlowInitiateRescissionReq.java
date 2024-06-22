package com.seepine.esign.module.sign.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.esign.module.sign.flow.create.SignFlowConfig;
import com.seepine.esign.module.sign.flow.create.signer.org.OrgSignerTransactor;
import com.seepine.esign.module.sign.flow.initiaterescission.AutoSignOrg;
import com.seepine.esign.module.sign.flow.initiaterescission.RescissionInitiator;
import com.seepine.http.Method;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发起合同解约
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/pdf-sign3/rcgt2karhmz75k1i">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowInitiateRescissionReq implements ESignReq<SignFlowInitiateRescissionRes> {
  /** 已完成状态的签署流程ID */
  @JsonIgnore String signFlowId;

  /** 本次需要解约的签署文件ID列表（一次解约最多可添加10份文件） */
  List<String> rescindFileList;
  /**
   * 解约原因（传对应的数字枚举值）
   *
   * <p>1 - 条款内容有误
   *
   * <p>2 - 印章选择错误
   *
   * <p>3 - 签署人信息错误
   *
   * <p>4 - 合作终止
   *
   * <p>5 - 其他
   */
  String rescindReason;
  /** 解约原因说明，最长200字 */
  String rescindReasonNotes;
  /**
   * 合同解约发起方信息
   *
   * <p>仅原流程中的签署方或发起方可以发起解约； 发起方需先经过 用户授权（代个人/企业用户发起合同签署权限）； psnInitiator与orgInitiator二选一传入。
   */
  RescissionInitiator rescissionInitiator;
  /** 解约流程配置项 */
  SignFlowConfig signFlowConfig;
  /**
   * 指定本次解约使用自动签署的机构签署方
   *
   * <p>补充说明：只有appId所属的平台方企业或者授权平台方印章的企业才能自动签署 跨企业印章授权的解约自动签，必须传入印章归属方的授权方（委托方）企业信息
   * 同一个企业，必须选择平台自动签和经办人手动签中的一种，且不能同时选择（autoSignOrg和orgSignerTransactor）
   */
  AutoSignOrg autoSignOrg;
  /**
   * 指定本次解约机构签署方经办人信息
   *
   * <p>补充说明：可以在这里指定更换原有签署机构的经办人； 若原有流程是机构自动签署的话是没有经办人信息的，可以通过autoSignOrg指定自动签署。
   */
  OrgSignerTransactor orgSignerTransactor;

  @Override
  public String url() {
    return "/v3/sign-flow/" + this.signFlowId + "/initiate-rescission";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFlowInitiateRescissionRes> clazz() {
    return SignFlowInitiateRescissionRes.class;
  }
}
