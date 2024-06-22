package com.seepine.esign.module.ocr.bankcard;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcrBankCardReq implements ESignReq<OcrBankCardRes> {
  private static final String PREFIX = "data:image";
  private static final String DELIMITER = ";base64,";
  /**
   * 银行卡正面照照片base64数据。
   *
   * <p>注意不要带图片BASE64前缀“data:image/jpeg;base64,”
   *
   * <p>图片类型支持：jpg，jpeg，png，bmp
   *
   * <p>图片建议分辨率为1024*768，图片大小控制在3M以内
   */
  String img;

  @Override
  public String url() {
    if (img != null) {
      if (img.startsWith(PREFIX)) {
        int idx = img.indexOf(DELIMITER);
        if (idx > 0) {
          img = img.substring(idx + DELIMITER.length());
        }
      }
    }
    return "/v2/identity/auth/api/ocr/bankcard";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<OcrBankCardRes> clazz() {
    return OcrBankCardRes.class;
  }
}
