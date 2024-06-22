package com.seepine.esign.module.sign.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询签署流程详情
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/pdf-sign3/xxk4q6">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowQueryDetailReq implements ESignReq<SignFlowQueryDetailRes> {
  /** 签署流程ID */
  @JsonIgnore String signFlowId;

  @Override
  public String url() {
    return "/v3/sign-flow/" + this.signFlowId + "/detail";
  }

  @Override
  public Method method() {
    return Method.GET;
  }

  @Override
  public Class<SignFlowQueryDetailRes> clazz() {
    return SignFlowQueryDetailRes.class;
  }
}
