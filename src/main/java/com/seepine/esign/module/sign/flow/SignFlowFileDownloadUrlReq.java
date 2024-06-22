package com.seepine.esign.module.sign.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下载已签署文件及附属材料
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/pdf-sign3/kczf8g">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowFileDownloadUrlReq implements ESignReq<SignFlowFileDownloadUrlRes> {
  /** 签署流程ID */
  @JsonIgnore String signFlowId;

  @Override
  public String url() {
    return "/v3/sign-flow/" + this.signFlowId + "/file-download-url";
  }

  @Override
  public Method method() {
    return Method.GET;
  }

  @Override
  public Class<SignFlowFileDownloadUrlRes> clazz() {
    return SignFlowFileDownloadUrlRes.class;
  }
}
