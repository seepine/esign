package com.seepine.esign.module.sign.flow.create.signer.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签章区位置信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFieldPosition {
  /** 骑缝章模式选择 ALL - 全部页盖骑缝章，AssignedPages - 指定页码盖骑缝章 */
  String acrossPageMode;
  /** 签章区所在页码 */
  String positionPage;
  /** 签章区所在X坐标（当signFieldStyle为2即骑缝签章时，该参数不生效，可不传值） */
  Double positionX;
  /** 签章区所在Y坐标 */
  Double positionY;
}
