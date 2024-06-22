package com.seepine.esign.module.identity.verify.individual.idcard2;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人2要素信息比对
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/glz1it#JDExT">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerifyIndividualIdCard2Req
    implements ESignReq<IdentityVerifyIndividualIdCard2Res> {
  /**
   * 身份证号（大陆二代身份证）
   *
   * <p>支持中国大陆二代身份证
   *
   * <p>支持港澳台居民居住证（82、81、83开头）
   */
  String idNo;
  /** 姓名 */
  String name;

  @Override
  public String url() {
    return "/v2/identity/verify/individual/base";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityVerifyIndividualIdCard2Res> clazz() {
    return IdentityVerifyIndividualIdCard2Res.class;
  }
}
