package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFileUploadUrlRes extends ESignRes {
  /** 文件ID（请注意保管好文件ID，可用于后续制作模板、签署相关操作） */
  String fileId;
  /** 文件上传地址，链接有效期60分钟。（请继续按下方的步骤二，将文件流上传到该地址中） */
  String fileUploadUrl;
}
