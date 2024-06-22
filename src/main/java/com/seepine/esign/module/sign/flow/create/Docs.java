package com.seepine.esign.module.sign.flow.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 待签署文件信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Docs {
  /** 必填，待签署文件ID */
  String fileId;
  /** 文件名称 */
  String fileName;
}
