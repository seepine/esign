package com.seepine.esign.enums;

/** 证件类型 */
public enum CertType {
  /** 中国大陆身份证（默认值） */
  INDIVIDUAL_CH_IDCARD,
  /** 台湾来往大陆通行证（台胞证） */
  INDIVIDUAL_CH_TWCARD,
  /** 港澳来往大陆通行证（回乡证） */
  INDIVIDUAL_CH_HONGKONG_MACAO,
  /** 护照 */
  INDIVIDUAL_PASSPORT,
  /** 港澳台居民居住证（18位810开头） */
  INDIVIDUAL_CH_RESIDENCE_PERMIT_HK_MO_TW,
  /** 外国人永久居留身份证（18位9开头） */
  INDIVIDUAL_CH_GREEN_CARD;
}
