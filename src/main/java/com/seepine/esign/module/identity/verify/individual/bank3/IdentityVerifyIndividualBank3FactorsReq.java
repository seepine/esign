package com.seepine.esign.module.identity.verify.individual.bank3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人银行卡3要素信息比对
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/yp6dhb">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerifyIndividualBank3FactorsReq implements ESignReq<IdentityVerifyIndividualBank3FactorsRes> {
  /** 身份证号（大陆二代身份证） */
  String idNo;
  /** 姓名 */
  String name;
  /** 银行卡号（银联卡号） */
  String cardNo;

  /**
   * 是否详情版，返回详细错误
   *
   * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/vqoype">文档</a>
   */
  @JsonIgnore Boolean isDetail;

  @Override
  public String url() {
    return Boolean.TRUE.equals(this.isDetail)
        ? "/v2/identity/verify/individual/bank3Factors/detail"
        : "/v2/identity/verify/individual/bank3Factors";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityVerifyIndividualBank3FactorsRes> clazz() {
    return IdentityVerifyIndividualBank3FactorsRes.class;
  }
}
