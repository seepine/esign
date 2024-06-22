package com.seepine.esign.test;

import com.seepine.esign.common.ESignClient;
import com.seepine.esign.common.ESignProperties;

public class ESignClientHelp {
  public static ESignClient build() {
    return new ESignClient(
        ESignProperties.builder()
            .endpoint("https://smlopenapi.esign.cn")
            .accessKeyId("7438987***")
            .accessKeySecret("677627c4caf44b63405bb496bfd7****")
            .build());
  }
}
