package com.seepine.esign.module.sign.flow.create.signer.psn;

import com.seepine.esign.module.sign.flow.create.signer.enums.PsnIDCardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 经办人身份信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsnInfo {
  /** 个人姓名（强烈建议传入签署人姓名） */
  String psnName;
  /** 个人证件号 */
  String psnIDCardNum;
  /** 个人证件类型，可选值如下： */
  PsnIDCardType psnIDCardType;
}
