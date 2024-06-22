package com.seepine.esign.module.sign.flow.create.signer.org;

import com.seepine.esign.module.sign.flow.create.signer.enums.PsnIDCardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsnInfo {

  /** 经办人姓名 */
  String psnName;
  /** 经办人证件号 */
  String psnIDCardNum;
  /**
   * 经办人证件类型，可选值如下：
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
  PsnIDCardType psnIDCardType;
}
