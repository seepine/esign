package com.seepine.esign.module.identity.verify.organization.factors3;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织机构3要素信息比对
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/vickfs">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerifyOrganizationFactors3Req
    implements ESignReq<IdentityVerifyOrganizationFactors3Res> {
  /** 组织机构名称 */
  String name;
  /** 组织机构证件号,支持15位工商注册号或统一社会信用代码 */
  String orgCode;
  /** 组织法定代表人姓名 */
  String legalRepName;

  @Override
  public String url() {
    return "/v2/identity/verify/organization/verify";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityVerifyOrganizationFactors3Res> clazz() {
    return IdentityVerifyOrganizationFactors3Res.class;
  }
}
