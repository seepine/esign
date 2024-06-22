package com.seepine.esign.module.ocr.idcard;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class OcrIdCardRes extends ESignRes {
  /** 业务Id */
  String verifyId;
  /** 姓名 */
  String name;
  /** 身份证号 */
  String idNo;
  /** 性别，男/女 */
  String gender;
  /** 出生日期，YYYY/MM/DD */
  String birthDay;
  /** 民族，汉 */
  String nation;
  /** 住址 */
  String address;
  /** 有效期限，YYYY.MM.DD-YYYY.MM.DD 或 YYYY.MM.DD-长期 */
  String validityPeriod;
  /** 发证机关 */
  String issuedBy;
}
