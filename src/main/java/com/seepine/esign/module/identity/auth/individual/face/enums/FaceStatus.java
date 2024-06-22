package com.seepine.esign.module.identity.auth.individual.face.enums;

/** 刷脸结果状态 */
public enum FaceStatus {
  /** 刷脸地址已申请 */
  ING,
  /** 刷脸地址已使用，但尚未接收到刷脸结果 */
  SCAN,
  /** 刷脸认证通过 */
  SUCCESS,
  /** 刷脸认证失败 */
  FAIL,
}
