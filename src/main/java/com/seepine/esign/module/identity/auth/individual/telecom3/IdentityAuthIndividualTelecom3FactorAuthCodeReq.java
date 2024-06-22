package com.seepine.esign.module.identity.auth.individual.telecom3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【手机号认证】重新发送验证码
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/fxr4yh#s2LtA">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualTelecom3FactorAuthCodeReq
    implements ESignReq<IdentityAuthIndividualTelecom3FactorAuthCodeRes> {
  /** 认证流程ID */
  @JsonIgnore String flowId;

  @Override
  public String url() {
    return "/v2/identity/auth/api/individual/" + this.flowId + "/telecom3FactorAuthCode";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthIndividualTelecom3FactorAuthCodeRes> clazz() {
    return IdentityAuthIndividualTelecom3FactorAuthCodeRes.class;
  }
}
