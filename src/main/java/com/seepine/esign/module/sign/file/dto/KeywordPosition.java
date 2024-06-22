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
public class KeywordPosition {
  /** 关键字 */
  String keyword;
  /** 关键字是否检索到坐标值 */
  Boolean searchResult;
  /** 关键字位置信息 */
  List<Positions> positions;
}
