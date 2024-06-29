package com.seepine.esign.test.sign.flow;

import com.seepine.esign.module.sign.SignFlowDto;
import com.seepine.esign.module.sign.SignFlowRes;
import com.seepine.esign.module.sign.SignFlowUtil;
import com.seepine.esign.test.ESignClientHelp;
import com.seepine.esign.test.sign.file.FileKeywordPositionsTest;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class SignFlowCreateByFileTest {

  public static void main(String[] args) throws IOException {
    // 模拟获取合同文件
    ByteArrayOutputStream contract = getFile();

    // 快速创建合同，若想自由控制，可进入 SignFlowUtil.create 参考
    SignFlowRes res =
        SignFlowUtil.create(
            ESignClientHelp.build(),
            SignFlowDto.builder()
                .contractName("自由职业者协议.docx")
                .contractFile(contract)
                .orgSignKeyword("甲方：") // .orgSignPositions() 也支持直接输入坐标
                .psnSignKeyword("乙方（自由职业者）：") // .psnSignPositions() 也支持直接输入坐标
                .orgSealId("c889d40b-5125-4dd5-9533-fbe139d536a1")
                .orgSealPagingAll(true)
                .psnFullName("黄**")
                .psnPhone("15080***360")
                .psnSealStyles("0") // 只能手写
                .build());
    System.out.println(res);
  }

  public static ByteArrayOutputStream getFile() throws IOException {
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
    return contract;
  }
}
