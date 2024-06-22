package com.seepine.esign.module.sign.flow.querydetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 附属材料信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
  /** 附属材料文件ID */
  String fileId;
  /** 附属材料文件名称 */
  String fileName;
  /** 附属材料文件下载链接（有效期为60分钟，过期后可以重新调用接口获取新的下载地址） */
  String downloadUrl;

  /**
   * 是否为签署方上传的附件
   *
   * <p>true - 是
   *
   * <p>false - 否
   */
  Boolean signerUpload;
}
