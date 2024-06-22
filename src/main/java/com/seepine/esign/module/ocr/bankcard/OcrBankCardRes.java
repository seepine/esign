package com.seepine.esign.module.ocr.bankcard;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class OcrBankCardRes extends ESignRes {
  /** 业务Id */
  String verifyId;
  /** 银行卡号，623xxxxxxxxxxxx979 */
  String bankCardNo;
  /** 银行名称，杭州商业银行 */
  String bankName;
  /**
   * 银行卡类型，
   *
   * <p>类型包含3种：“借记卡”、信用卡”以及“不能识别”
   */
  String bankCardType;
}
