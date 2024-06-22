package com.seepine.esign.module.identity.verify.organization.factors2;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织机构2要素信息比对
 *
 * <p>对企业、律所、非工商社会组织的组织机构名称，组织证件号（统一社会信用代码）一致性做核验
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/nkcwuq">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerifyOrganizationFactors2Req
    implements ESignReq<IdentityVerifyOrganizationFactors2Res> {
  /** 组织机构名称 */
  String name;
  /** 组织机构证件号,支持15位工商注册号或统一社会信用代码 */
  String orgCode;

  @Override
  public String url() {
    return "/v2/identity/verify/organization/enterprise/base";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityVerifyOrganizationFactors2Res> clazz() {
    return IdentityVerifyOrganizationFactors2Res.class;
  }
}
