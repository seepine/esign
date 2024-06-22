package com.seepine.esign.module.sign.flow.create.signer.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 备注区位置 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFieldPositionBean {
  /** 备注区所在页码 */
  String positionPage;
  /** 备注区所在X坐标 */
  String positionX;
  /** 备注区所在Y坐标 */
  String positionY;
}
