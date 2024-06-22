package com.seepine.esign.module.identity.verify.individual.telecom3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人运营商3要素信息比对
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/cgs6ee">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerifyIndividualTelecom3FactorsReq
    implements ESignReq<IdentityVerifyIndividualTelecom3FactorsRes> {
  /** 身份证号（大陆二代身份证） */
  String idNo;
  /** 姓名 */
  String name;
  /** 手机号（中国大陆3大运营商） */
  String mobileNo;
  /**
   * 是否详情版，返回详细错误
   *
   * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/kyken6">文档</a>
   */
  @JsonIgnore Boolean isDetail;

  @Override
  public String url() {
    return Boolean.TRUE.equals(this.isDetail)
        ? "/v2/identity/verify/individual/telecom3Factors/detail"
        : "/v2/identity/verify/individual/telecom3Factors";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityVerifyIndividualTelecom3FactorsRes> clazz() {
    return IdentityVerifyIndividualTelecom3FactorsRes.class;
  }
}
