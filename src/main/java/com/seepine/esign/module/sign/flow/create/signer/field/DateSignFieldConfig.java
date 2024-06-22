package com.seepine.esign.module.sign.flow.create.signer.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 独立签署日期配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateSignFieldConfig {
  /**
   * 日期格式
   *
   * <p>yyyy年MM月dd日（默认值）
   *
   * <p>yyyy-MM-dd
   *
   * <p>yyyy/MM/dd
   */
  String dateFormat;
  /** 日期字体大小，默认值12px */
  Integer fontSize;
  /** 指定签署日期位置页码 */
  Integer signDatePositionPage;
  /** 签署日期所在位置X坐标 */
  Float signDatePositionX;
  /** 签署日期所在位置Y坐标 */
  Float signDatePositionY;
}
