package com.seepine.esign.common;

import java.util.Map;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ESignProperties {
  /** 对应易签宝appId */
  public String accessKeyId;
  /** 对应易签宝appKey */
  public String accessKeySecret;
  /**
   * 服务端地址
   *
   * <p>正式生产环境:https://openapi.esign.cn
   *
   * <p>沙箱模拟环境:https://smlopenapi.esign.cn
   */
  public String endpoint;
  /** 其他参数 */
  public Map<String, String> claims;
}
