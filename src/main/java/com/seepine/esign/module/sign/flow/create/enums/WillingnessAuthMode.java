package com.seepine.esign.module.sign.flow.create.enums;

/** 签署意愿认证方式可选项 */
public enum WillingnessAuthMode {
  /** 短信验证码 */
  CODE_SMS,
  /** 支付宝刷脸 */
  PSN_FACE_ALIPAY,
  /** 腾讯云刷脸 */
  PSN_FACE_TECENT,
  /** 快捷刷脸 */
  PSN_FACE_ESIGN,
  /** 微信小程序刷脸 */
  PSN_FACE_WECHAT,
  /** 以下方式如需使用，请联系交付顾问开通： - 支付宝智能视频认证 */
  PSN_AUDIO_VIDEO_ALIPAY,
  /** 以下方式如需使用，请联系交付顾问开通： - 微信智能视频认证 */
  PSN_AUDIO_VIDEO_WECHAT,
  /** 以下方式如需使用，请联系交付顾问开通： - 签署密码 */
  SIGN_PWD
}
