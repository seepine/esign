package com.seepine.esign.test.sign.flow;

import com.seepine.esign.module.sign.SignFlowQuery;
import com.seepine.esign.module.sign.SignFlowUtil;
import com.seepine.esign.test.ESignClientHelp;

public class SignFlowQueryDetailTest {
  public static void main(String[] args) {
    String signFlowId = "ab9ad219faf44e79b9189b52dbfdabdc";

    // 快速查询流程，若想查询更多信息，可进入 SignFlowUtil.query 参考
    SignFlowQuery res = SignFlowUtil.query(ESignClientHelp.build(), signFlowId);
    System.out.println(res);
  }
}
