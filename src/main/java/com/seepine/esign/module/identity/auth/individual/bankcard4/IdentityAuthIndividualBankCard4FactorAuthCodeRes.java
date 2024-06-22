package com.seepine.esign.module.identity.auth.individual.bankcard4;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityAuthIndividualBankCard4FactorAuthCodeRes extends ESignRes {
  /** 业务数据 */
  Object data;
}
