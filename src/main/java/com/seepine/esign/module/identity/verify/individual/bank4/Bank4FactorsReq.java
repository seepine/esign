package com.seepine.esign.module.identity.verify.individual.bank4;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人银行卡4要素信息比对
 *
 * <p>https://open.esign.cn/doc/opendoc/identity_service/ofppps
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank4FactorsReq implements ESignReq<Bank4FactorsRes> {
  /** 身份证号（大陆二代身份证） */
  String idNo;
  /** 姓名 */
  String name;
  /** 银行卡号（银联卡号） */
  String cardNo;
  /** 银行预留手机号（非短信通知手机号） */
  String mobileNo;

  @Override
  public String url() {
    return "/v2/identity/verify/individual/bank4Factors";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<Bank4FactorsRes> clazz() {
    return Bank4FactorsRes.class;
  }
}
