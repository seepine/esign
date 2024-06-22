package com.seepine.esign.module.sign.flow.signurl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 指定签署操作人
 *
 * <p>当获取签署链接场景，需传入当前流程流转到的签署操作人信息。（如不传该参数，后台默认自动带入appId对应主体信息）
 *
 * <p>psnAccount与psnId二选一传入（必须与发起签署时的账号保持一致）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operator {
  /** 签署操作人账号标识（手机号/邮箱号） */
  String psnAccount;
  /** 签署操作人账号ID（个人账号ID） */
  String psnId;
}
