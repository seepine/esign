package com.seepine.esign.module.ocr.idcard;

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
public class OcrIdCardReq implements ESignReq<OcrIdCardRes> {
  private static final String PREFIX = "data:image";
  private static final String DELIMITER = ";base64,";
  /**
   * 身份证信息面图片BASE64字符串。
   *
   * <p>注意不要带图片BASE64前缀“data:image/jpeg;base64,”
   *
   * <p>图片类型支持：jpg，jpeg，png，bmp
   *
   * <p>图片建议分辨率为1024*768，图片大小控制在3M以内
   *
   * <p>infoImg与emblemImg至少传一个值
   */
  String infoImg;
  /**
   * 身份证国徽面图片BASE64字符串
   *
   * <p>注意不要带图片BASE64前缀“data:image/jpeg;base64,”
   *
   * <p>图片类型支持：jpg，jpeg，png，bmp
   *
   * <p>图片建议分辨率为1024*768，图片大小控制在3M以内
   *
   * <p>infoImg与emblemImg至少传一个值
   */
  String emblemImg;

  @Override
  public String url() {
    if (infoImg != null) {
      if (infoImg.startsWith(PREFIX)) {
        int idx = infoImg.indexOf(DELIMITER);
        if (idx > 0) {
          infoImg = infoImg.substring(idx + DELIMITER.length());
        }
      }
    }
    if (emblemImg != null) {
      if (emblemImg.startsWith(PREFIX)) {
        int idx = emblemImg.indexOf(DELIMITER);
        if (idx > 0) {
          emblemImg = emblemImg.substring(idx + DELIMITER.length());
        }
      }
    }
    return "/v2/identity/auth/api/ocr/idcard";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<OcrIdCardRes> clazz() {
    return OcrIdCardRes.class;
  }
}
