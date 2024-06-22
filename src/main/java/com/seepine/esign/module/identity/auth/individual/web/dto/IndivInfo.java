package com.seepine.esign.module.identity.auth.individual.web.dto;

import com.seepine.esign.enums.CertType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndivInfo {
  /** 个人银行卡号（仅支持银联卡） */
  String bankCardNo;
  /** 个人证件号 */
  String certNo;
  /** 个人证件类型 */
  CertType certType;
  /** 个人手机号 */
  String mobileNo;
  /** 个人姓名 */
  String name;
}
