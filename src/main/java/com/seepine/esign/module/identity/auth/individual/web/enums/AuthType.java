package com.seepine.esign.module.identity.auth.individual.web.enums;

/** 指定默认认证类型（打开认证页面所展示的第一个认证类型） */
public enum AuthType {

  /** 个人银行卡四要素认证 */
  PSN_BANK4_AUTHCODE,
  /** 个人运营商三要素认证 */
  PSN_TELECOM_AUTHCODE,
  /** 个人刷脸认证 */
  PSN_FACEAUTH_BYURL
}
