package com.seepine.esign.module.sign.flow.create.signer.org;

import com.seepine.esign.module.sign.flow.create.signer.enums.OrgIDCardType;
import com.seepine.esign.module.sign.flow.create.signer.enums.PsnIDCardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 机构签署方信息（将展示在机构认证页面） */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgInfo {
  /** 组织机构证件号 */
  String orgIDCardNum;
  /**
   * 企业/机构证件类型，可选值如下：
   *
   * <p>CRED_ORG_USCC - 统一社会信用代码
   *
   * <p>CRED_ORG_REGCODE - 工商注册号
   */
  OrgIDCardType orgIDCardType;


  /** 法定代表人姓名 */
  String legalRepName;
  /** 法定代表人证件号 */
  String legalRepIDCardNum;
  /**
   * 法定代表人证件类型，可选值如下：
   *
   * <p>CRED_PSN_CH_IDCARD - 中国大陆居民身份证（默认值）
   *
   * <p>CRED_PSN_CH_HONGKONG - 香港来往大陆通行证（回乡证）
   *
   * <p>CRED_PSN_CH_MACAO - 澳门来往大陆通行证（回乡证）
   *
   * <p>CRED_PSN_CH_TWCARD - 台湾来往大陆通行证（台胞证）
   *
   * <p>CRED_PSN_PASSPORT - 护照
   */
  PsnIDCardType legalRepIDCardType;
}
