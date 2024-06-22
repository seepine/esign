package com.seepine.esign.test.ocr;

import com.seepine.esign.module.ocr.idcard.OcrIdCardReq;
import com.seepine.esign.module.ocr.idcard.OcrIdCardRes;
import com.seepine.esign.test.ESignClientHelp;

public class OcrIdCardTest {
  public static void main(String[] args) {
    // 替换为真实身份证人像面
    String img = "data:image/jpeg;base64,/9j/4AAQS********KYH//2Q==";

    OcrIdCardRes res =
      ESignClientHelp.build()
        .execute(
         OcrIdCardReq.builder()
            .infoImg(img)
            .build());
    System.out.println(res.isSuccess());
    System.out.println(res);
  }
}
