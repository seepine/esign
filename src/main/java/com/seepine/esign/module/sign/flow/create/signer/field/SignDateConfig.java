package com.seepine.esign.module.sign.flow.create.signer.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignDateConfig {
  /**
   * 日期格式
   *
   * <p>yyyy年MM月dd日（默认值）
   *
   * <p>yyyy-MM-dd
   *
   * <p>yyyy/MM/dd
   *
   * <p>yyyy-MM-dd HH:mm:ss
   */
  String dateFormat;
  /** 日期字体大小，默认值 12px */
  Integer fontSize;
  /**
   * 是否显示签署日期，默认值 0
   *
   * <p>0 - 不显示，1 - 固定位置显示，2 - 不固定位置
   *
   * <p>补充说明：
   *
   * <p>传1时，可以设置签署日期位置的X、Y坐标（如不传入位置坐标，日期默认显示在签署区下方，但不传位置用户手动签署时可移动日期位置，传了位置则不可移动）
   * 传2时，由用户在签署页面中选择是否开启显示签署日期（默认不开启），开启后日期位置可在页面移动调整。
   */
  Integer showSignDate;
  /** 签署日期所在位置X坐标，当showSignDate为 1- 固定位置显示时生效。 */
  Double signDatePositionX;
  /** 签署日期所在位置Y坐标，当showSignDate为 1- 固定位置显示时生效。 */
  Double signDatePositionY;
}
