package com.seepine.esign.module.identity.auth.organization.web.dto;

import com.seepine.esign.enums.CertType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgEntity {
  /** 组织机构名称 */
  String name;

  /** 组织机构证件号 */
  String certNo;

  /** 法人证件类型 */
  CertType legalRepCertType;

  /** 法定代表人身份证号 */
  String legalRepCertNo;

  /** 法定代表人姓名 */
  String legalRepName;

  /** 当前实名认证办理人姓名 */
  String agentName;

  /** 当前实名认证办理人证件号 */
  String agentIdNo;
  /**
   * 组织机构类型
   *
   * <p>1 - 企业类，2 - 个体工商户 ，99 - 其他组织
   */
  Integer organizationType;
}
