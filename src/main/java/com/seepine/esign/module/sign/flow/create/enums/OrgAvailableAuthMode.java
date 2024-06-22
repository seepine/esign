package com.seepine.esign.module.sign.flow.create.enums;

/** 机构实名认证方式可选项 */
public enum OrgAvailableAuthMode {
  /** 组织机构对公账户打款认证 */
  ORG_BANK_TRANSFER,
  /** 企业支付宝认证 */
  ORG_ALIPAY_CREDIT,
  /** 组织机构授权委托书认证 */
  ORG_LEGALREP_AUTHORIZATION,
  /** 法定代表人本人认证 */
  ORG_LEGALREP
}
