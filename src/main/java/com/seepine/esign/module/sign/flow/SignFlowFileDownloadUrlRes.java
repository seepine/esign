package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignRes;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFlowFileDownloadUrlRes extends ESignRes {
  /** 签署文件信息 */
  List<File> files;
  /** 附属材料信息 */
  List<File> attachments;
  /**
   * 海外签证书报告下载地址（有效期为60分钟，过期后可以重新调用接口获取新的下载地址）
   *
   * <p>【注】默认中国大陆签署不返回值
   */
  String certificateDownloadUrl;

  @Data
  public static class File {
    /** 签署文件ID */
    String fileId;
    /** 签署文件名称 */
    String fileName;
    /** 已签署文件下载链接（有效期为60分钟，过期后可以重新调用接口获取新的下载地址） */
    String downloadUrl;
  }
}
