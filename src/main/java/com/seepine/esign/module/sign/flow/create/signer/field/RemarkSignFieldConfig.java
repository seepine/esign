package com.seepine.esign.module.sign.flow.create.signer.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 备注区配置信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemarkSignFieldConfig {
  /**
   * 备注文字输入方式
   *
   * <p>1 - 手写抄录输入，2 - 键盘自由输入
   */
  Integer inputType;
  /** 是否可以移动备注区 true - 可以，false - 不可以 */
  Boolean movableSignField;
  /**
   * 自由模式
   *
   * <p>true - 是，false - 否
   */
  Boolean freeMode;
  /** 备注文字预设置内容 */
  String remarkContent;
  /** 备注区生成的图片文件filekey，开发者可忽略此字段 */
  String remarkImageFileKey;
  /** 备注区位置 */
  SignFieldPositionBean signFieldPositionBean;
  /** 用户页面实际签字内容 */
  String actualContent;
}
