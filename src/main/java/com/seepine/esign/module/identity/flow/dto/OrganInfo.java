package com.seepine.esign.module.identity.flow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganInfo {
  /** 组织用户id */
  String accountId;
  /** 组织机构名称 */
  String name;
  /** 组织机构证件号 */
  String certNo;
  /**
   * 组织机构证件类型
   *
   * <p>ORGANIZATION_USC_CODE 统一社会信用代码
   *
   * <p>ORGANIZATION_REG_CODE 企业工商注册号
   */
  String certType;
  /** 法定代表人姓名 */
  String legalRepName;
  /**
   * 法定代表人国籍/地区
   *
   * <p>MAINLAND 中国大陆
   *
   * <p>FOREIGN 非中国地区
   *
   * <p>HONGKONG_MACAO 中国香港/澳门地区
   *
   * <p>TAIWAN 中国台湾省地区
   */
  String legalRepNationality;
  /** 法定代表人证件号 */
  String legalRepCertNo;
  /** 法定代表人证件类型 */
  String legalRepCertType;
}
