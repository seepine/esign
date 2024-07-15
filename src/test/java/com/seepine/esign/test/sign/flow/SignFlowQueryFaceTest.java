package com.seepine.esign.test.sign.flow;

import com.seepine.esign.module.sign.flow.SignFlowQueryFaceReq;
import com.seepine.esign.module.sign.flow.SignFlowQueryFaceRes;
import com.seepine.esign.test.ESignClientHelp;

public class SignFlowQueryFaceTest {
  public static void main(String[] args) {
    SignFlowQueryFaceRes res =
        ESignClientHelp.build()
            .execute(
                SignFlowQueryFaceReq.builder()
                    .flowId("ab87adf3e8464604b101e77defab6f**")
                    .accountId("dc965ac5bafc4f52a053bf79a2d494**")
                    .build());
    res.transfer();
    if (res.isSuccess()) {
      System.out.println(res);
    } else {
      System.out.println("查询失败，" + res);
    }
  }
}
