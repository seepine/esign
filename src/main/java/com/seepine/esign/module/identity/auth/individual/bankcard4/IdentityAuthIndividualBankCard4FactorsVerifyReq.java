package com.seepine.esign.module.identity.auth.individual.bankcard4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【银行卡认证】预留手机号校验
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/lwq4an">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualBankCard4FactorsVerifyReq implements ESignReq<IdentityAuthIndividualBankCard4FactorsVerifyRes> {
  /** BankCard4FactorsRes 获得的流程id */
  @JsonIgnore String flowId;
  /** 短信验证码 */
  String authcode;

  @Override
  public String url() {
    return "/v2/identity/auth/pub/individual/" + this.flowId + "/bankCard4Factors";
  }

  @Override
  public Method method() {
    return Method.PUT;
  }

  @Override
  public Class<IdentityAuthIndividualBankCard4FactorsVerifyRes> clazz() {
    return IdentityAuthIndividualBankCard4FactorsVerifyRes.class;
  }
}
