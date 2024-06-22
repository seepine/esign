package com.seepine.esign.test.sign.file;

import com.seepine.esign.module.sign.file.SignFileKeywordPositionsReq;
import com.seepine.esign.module.sign.file.SignFileKeywordPositionsRes;
import com.seepine.esign.module.sign.file.SignFileUploadUtil;
import com.seepine.esign.test.ESignClientHelp;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class FileKeywordPositionsTest {
  public static void main(String[] args) throws IOException {
    // 读合同文件
    FileInputStream is =
        new FileInputStream(
            Objects.requireNonNull(FileKeywordPositionsTest.class.getResource("/contract.docx"))
                .getFile());
    ByteArrayOutputStream contract = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      contract.write(buffer, 0, bytesRead);
    }
    is.close();

    // 上传获得 fileId
    String fileId = SignFileUploadUtil.uploadV3(ESignClientHelp.build(), "contract.docx", contract);
    System.out.println(fileId);

    SignFileKeywordPositionsRes res =
        ESignClientHelp.build()
            .execute(
                SignFileKeywordPositionsReq.builder()
                    .fileId(fileId)
                    .keywords(Arrays.asList("甲方：", "乙方（自由职业者）："))
                    .build());
    if (res.isSuccess()) {
      System.out.println("查询成功，" + res.getKeywordPositions());
    } else {
      System.out.println("查询失败，" + res);
    }
  }
}
