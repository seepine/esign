package com.seepine.esign.module.identity.auth.organization.web.enums;

public enum AgentAuthType {
  /** 个人运营商三要素 */
  PSN_TELECOM_AUTHCODE,
  /** 个人银行四要素 */
  PSN_BANK4_AUTHCODE,
  /**
   * 个人刷脸认证,【注】当选择个人刷脸认证方式默认包含以下四种分项的刷脸方式。
   *
   * <p>如需指定一种或其中几种具体的刷脸方式可选值如下
   */
  PSN_FACEAUTH_BYURL,
  /** 个人刷脸认证-芝麻人脸识别 */
  PSN_FACEAUTH_BYURL_ZHIMA,
  /** 个人刷脸认证-微众人脸识别 */
  PSN_FACEAUTH_BYURL_WEIZHONG,
  /** 个人刷脸认证-快捷人脸识别 */
  PSN_FACEAUTH_BYURL_ESIGN,
  /** 个人刷脸认证-微信小程序人脸识别 */
  PSN_FACEAUTH_BYURL_WE_CHAT;
}
