package com.seepine.esign.module.identity.auth.individual.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 业务方交互上下文信息，有统计需求或者分账需求必填部分参数 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextInfo {
  /** 自定义业务标识 在异步通知时发送回发起方，直接对接客户建议上传相关订单Id或会员Id，渠道分销商建议上传客户唯一Id */
  String contextId;
  /** 发起方接收实名认证状态变更通知的地址 */
  String notifyUrl;
  /**
   * 认证发起来源，默认值为 BROWSER
   *
   * <p>BROWSER - 浏览器
   *
   * <p>APP - 移动端APP
   */
  String origin;
  /** 认证结束后页面跳转地址 */
  String redirectUrl;
  /**
   * 认证完成是否显示结果页，默认值为 true
   *
   * <p>true - 显示结果，false - 不显示结果
   */
  Boolean showResultPage;
}
