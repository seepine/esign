package com.seepine.esign.module.sign.file.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Positions {
  /** 关键字所在页码 */
  Integer pageNum;
  /** 关键字XY坐标值 */
  List<Coordinate> coordinates;
}
