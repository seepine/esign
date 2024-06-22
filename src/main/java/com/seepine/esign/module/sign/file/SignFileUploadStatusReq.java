package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFileUploadStatusReq implements ESignReq<SignFileUploadStatusRes> {

  /** 必填 path 文件ID */
  String fileId;

  /**
   * 是否返回文件首页的长宽值，默认值 false
   *
   * <p>true - 返回长宽值
   *
   * <p>false - 不返回长宽值（字段会返回，值为null）
   */
  Boolean pageSize;

  @Override
  public String url() {
    return "/v3/files/" + this.fileId;
  }

  @Override
  public Method method() {
    return Method.GET;
  }

  @Override
  public Class<SignFileUploadStatusRes> clazz() {
    return SignFileUploadStatusRes.class;
  }
}
