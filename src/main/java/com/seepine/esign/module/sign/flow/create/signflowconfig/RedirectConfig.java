package com.seepine.esign.module.sign.flow.create.signflowconfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 重定向配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedirectConfig {
  /** 签署完成后跳转页面（需符合 https /http 协议地址） */
  String redirectUrl;
  /**
   * 操作完成重定向跳转延迟时间，单位秒（可选值0、3，默认值为 3）
   *
   * <p>传0时，签署完成直接跳转重定向地址； 传3时，展示签署完成结果页，倒计时3秒后，自动跳转重定向地址。 【注】当redirectUrl不传的情况下，该字段无需传入，签署完成不跳转
   */
  Integer redirectDelayTime;
}
