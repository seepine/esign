package com.seepine.esign.module.identity.flow.enums;

public enum Status {
  /** 已发起，该状态下e签宝不会计费 */
  INIT,
  /** 进行中 */
  ING,
  /** 已成功 */
  SUCCESS,
  /** 已失败 */
  FAIL
}