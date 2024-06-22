package com.seepine.esign.module.identity.auth.individual.web;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityAuthIndividualAuthUrlRes extends ESignRes {
  /** 认证流程ID */
  String flowId;
  /**
   * 个人实名认证短链接（链接有效期30天）
   *
   * <p>沙箱环境域名：https://smlt.tsign.cn
   *
   * <p>正式环境域名：https://t.tsign.cn
   */
  String shortLink;
  /**
   * 个人实名认证长链接（链接永久有效）
   *
   * <p>沙箱环境域名：https://smlfront.esign.cn
   *
   * <p>正式环境域名：https://idverify.esign.cn
   */
  String url;
}
