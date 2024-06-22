package com.seepine.esign.module.ocr.idcard;

import com.seepine.esign.common.ESignRes;
import com.seepine.tool.util.LocalDateUtil;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

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

  public LocalDate getBirthDayDate() {
    if (birthDay == null) {
      return null;
    }
    return LocalDateUtil.parse(birthDay, "yyyy/MM/dd");
  }

  public String getStartDate() {
    if (validityPeriod == null) {
      return null;
    }
    String[] arr = validityPeriod.split("-");
    if (arr.length < 1) {
      return null;
    }
    return LocalDateUtil.format(LocalDateUtil.parse(arr[0], "yyyy.MM.dd"));
  }

  public String getEndDate() {
    if (validityPeriod == null) {
      return null;
    }
    String[] arr = validityPeriod.split("-");
    if (arr.length < 2) {
      return null;
    }
    if (arr[1].equals("长期")) {
      return "长期";
    }
    return LocalDateUtil.format(LocalDateUtil.parse(arr[1], "yyyy.MM.dd"));
  }
}
