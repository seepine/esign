package com.seepine.esign.module.identity.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询认证信息
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/duoa44">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityFlowDetailReq implements ESignReq<IdentityFlowDetailRes> {
  @JsonIgnore String flowId;

  @Override
  public String url() {
    return "/v2/identity/auth/api/common/" + this.flowId + "/detail";
  }

  @Override
  public Method method() {
    return Method.GET;
  }

  @Override
  public Class<IdentityFlowDetailRes> clazz() {
    return IdentityFlowDetailRes.class;
  }
}
