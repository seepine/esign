package com.seepine.esign.module.identity.auth.individual.telecom3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【手机号认证】短信验证码校验
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/ddklrl">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualTelecom3FactorsVerifyReq implements ESignReq<IdentityAuthIndividualTelecom3FactorsVerifyRes> {
  /** 认证流程ID */
  @JsonIgnore String flowId;
  /** 短信验证码，用户收到的6位数字验证码 */
  String authcode;

  @Override
  public String url() {
    return "/v2/identity/auth/pub/individual/" + this.flowId + "/telecom3Factors";
  }

  @Override
  public Method method() {
    return Method.PUT;
  }

  @Override
  public Class<IdentityAuthIndividualTelecom3FactorsVerifyRes> clazz() {
    return IdentityAuthIndividualTelecom3FactorsVerifyRes.class;
  }
}
