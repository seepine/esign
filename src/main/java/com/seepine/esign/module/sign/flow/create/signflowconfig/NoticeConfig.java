package com.seepine.esign.module.sign.flow.create.signflowconfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 设置签署方的通知方式类 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeConfig {
  /**
   * 通知类型，通知发起方、签署方、抄送方，默认不通知（值为""空字符串），允许多种通知方式，请使用英文逗号分隔
   *
   * <p>传空 - 不通知（默认值）
   *
   * <p>1 - 短信通知（如果套餐内带“分项”字样，请确保开通【电子签名流量费（分项）认证】中的子项：【短信服务】，否则短信通知收不到）
   *
   * <p>2 - 邮件通知
   *
   * <p>【注】个人账号中需要绑定短信/邮件才有对应的通知方式。
   */
  String noticeTypes;
  /**
   * 通知给企业印章用印审批人员的通知类型，按照账号中的手机号或邮箱的填写情况进行通知。 true - 发送消息（短信+邮件+e签宝官网站内信）
   *
   * <p>（如果套餐内带“分项”字样，请确保开通【电子签名流量费（分项）认证】中的子项：【短信服务】，否则短信通知收不到） false - 不发送消息
   *
   * <p>【注】不传值默认取noticeTypes配置的通知方式
   */
  Boolean examineNotice;
}
