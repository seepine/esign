package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询签署流程详情
 *
 * <p><a href="https://qianxiaoxia.yuque.com/opendoc/kxf3gi/mccuu2">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowQueryFaceReq implements ESignReq<SignFlowQueryFaceRes> {
  /** 签署流程ID（等价于SaaS API V3版本的signFlowId） */
  String flowId;
  /** 签署人账号ID（等价于SaaS API V3版本的psnId） */
  String accountId;
  /** 签署主体ID（一般是企业签署，传机构账号ID，等价于SaaS API V3版本的orgId） */
  String authorizedAccountId;

  @Override
  public String url() {
    return "/v2/signflows/identity/detail";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFlowQueryFaceRes> clazz() {
    return SignFlowQueryFaceRes.class;
  }
}
