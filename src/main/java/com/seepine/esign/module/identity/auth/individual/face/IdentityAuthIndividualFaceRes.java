package com.seepine.esign.module.identity.auth.individual.face;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityAuthIndividualFaceRes extends ESignRes {
  /** 实名认证流程ID */
  String flowId;

  /**
   * 刷脸认证短链接
   *
   * <p>指定TENCENT（腾讯云刷脸认证）时，链接有效时长为2分钟；
   *
   * <p>指定ZHIMACREDIT（支付宝刷脸认证）时，链接有效时长为23小时；
   *
   * <p>指定ESIGN（快捷刷脸认证）时，链接有效时长为60分钟；
   *
   * <p>（如果用户超过链接有效期未操作，需要重新发起新的流程获取新的链接给用户操作）
   */
  String authUrl;

  /**
   * 刷脸认证长链接
   *
   * <p>指定TENCENT（腾讯云刷脸认证）时，链接有效时长为2分钟；
   *
   * <p>指定ZHIMACREDIT（支付宝刷脸认证）时，链接有效时长为23小时；
   *
   * <p>指定ESIGN（快捷刷脸认证）时，链接有效时长为60分钟；
   *
   * <p>（如果用户超过链接有效期未操作，需要重新发起新的流程获取新的链接给用户操作）
   */
  String originalUrl;

  /** 链接失效时间（Unix时间戳格式，单位：毫秒） */
  Long expire;
}
