package com.seepine.esign.test.identity.auth;

import com.seepine.esign.module.identity.auth.individual.telecom3.IdentityAuthIndividualTelecom3FactorsReq;
import com.seepine.esign.module.identity.auth.individual.telecom3.IdentityAuthIndividualTelecom3FactorsRes;
import com.seepine.esign.module.identity.auth.individual.telecom3.IdentityAuthIndividualTelecom3FactorsVerifyReq;
import com.seepine.esign.module.identity.auth.individual.telecom3.IdentityAuthIndividualTelecom3FactorsVerifyRes;
import com.seepine.esign.test.ESignClientHelp;
import java.util.Scanner;

public class Telecom3Test {
  public static void main(String[] args) {

    IdentityAuthIndividualTelecom3FactorsRes res =
        ESignClientHelp.build()
            .execute(
                IdentityAuthIndividualTelecom3FactorsReq.builder()
                    .name("黄**")
                    .idNo("35021*************")
                    .mobileNo("15080******")
                    .build());
    System.out.println(res);
    if (!res.isSuccess()) {
      System.out.println("发送失败");
      return;
    }
    while (true) {
      System.out.println("请输入验证码");
      Scanner sc = new Scanner(System.in);
      String code = sc.nextLine();
      IdentityAuthIndividualTelecom3FactorsVerifyRes verifyRes =
          ESignClientHelp.build()
              .execute(
                  IdentityAuthIndividualTelecom3FactorsVerifyReq.builder()
                      .flowId(res.getFlowId())
                      .authcode(code)
                      .build());
      System.out.println(verifyRes);
      if (verifyRes.isSuccess()) {
        break;
      }
    }
  }
}
