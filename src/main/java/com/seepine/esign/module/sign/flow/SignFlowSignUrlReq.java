package com.seepine.esign.module.sign.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.esign.module.sign.flow.signurl.Operator;
import com.seepine.esign.module.sign.flow.signurl.Organization;
import com.seepine.esign.module.sign.flow.create.signflowconfig.RedirectConfig;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取签署页面链接
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/pdf-sign3/pvfkwd">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowSignUrlReq implements ESignReq<SignFlowSignUrlRes> {
  /** 签署流程ID */
  @JsonIgnore String signFlowId;

  /**
   * 是否需要登录打开链接（默认值 false）
   *
   * <p>true - 需登录打开链接，false - 免登录
   */
  Boolean needLogin;
  /**
   * 链接类型（默认值 2）
   *
   * <p>1 - 预览链接（仅限查看，不能签署）， 2 - 签署链接
   */
  Integer urlType;
  /**
   * 个人签署方（机构签署传经办人信息）
   *
   * <p>当获取签署链接场景，需传入当前流程流转到的签署操作人信息。
   *
   * <p>psnAccount与psnId二选一传入（必须与发起签署时的账号保持一致）
   *
   * <p>【注】大多数场景必传字段，如不传该参数，后台默认自动带入appId对应主体信息，获取平台方预览合同地址
   */
  Operator operator;
  /**
   * 机构签署方
   *
   * <p>一个流程中存在经办人代多个机构签署时，通过此参数分别获取对应机构的签署链接；
   *
   * <p>orgId与orgName二选一传入（必须与发起签署时账号保持一致）
   */
  Organization organization;
  /** 重定向配置项 */
  RedirectConfig redirectConfig;
  /** AppScheme，用于唤起App。 */
  String appScheme;

  @Override
  public String url() {
    return "/v3/sign-flow/" + this.signFlowId + "/sign-url";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFlowSignUrlRes> clazz() {
    return SignFlowSignUrlRes.class;
  }
}
