package com.seepine.esign.module.identity.auth.individual.bankcard4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【银行卡认证】重新发送验证码
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/nf4xwp#s2LtA">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualBankCard4FactorAuthCodeReq implements ESignReq<IdentityAuthIndividualBankCard4FactorAuthCodeRes> {
  /** 认证流程ID */
  @JsonIgnore String flowId;

  @Override
  public String url() {
    return "/v2/identity/auth/api/individual/" + this.flowId + "/bankCard4FactorAuthCode";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthIndividualBankCard4FactorAuthCodeRes> clazz() {
    return IdentityAuthIndividualBankCard4FactorAuthCodeRes.class;
  }
}
