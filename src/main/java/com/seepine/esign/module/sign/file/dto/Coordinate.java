package com.seepine.esign.module.sign.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
  /** X坐标 */
  Double positionX;
  /** Y坐标 */
  Double positionY;
}
