package com.seepine.esign.module.identity.auth.individual.face;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询个人刷脸状态
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/qeig5n">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualFaceQueryReq implements ESignReq<IdentityAuthIndividualFaceQueryRes> {
  /** 发起刷脸时的id */
  @JsonIgnore String flowId;

  @Override
  public String url() {
    return "/v2/identity/auth/pub/individual/" + this.flowId + "/face";
  }

  @Override
  public Method method() {
    return Method.GET;
  }

  @Override
  public Class<IdentityAuthIndividualFaceQueryRes> clazz() {
    return IdentityAuthIndividualFaceQueryRes.class;
  }
}
