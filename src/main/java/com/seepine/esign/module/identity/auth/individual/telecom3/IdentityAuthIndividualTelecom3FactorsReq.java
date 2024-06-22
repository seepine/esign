package com.seepine.esign.module.identity.auth.individual.telecom3;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【手机号认证】运营商3要素核身
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/wry7rc#VehrP">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualTelecom3FactorsReq implements ESignReq<IdentityAuthIndividualTelecom3FactorsRes> {
  /** 姓名 */
  String name;
  /** 身份证号 */
  String idNo;
  /** 手机号（仅限中国大陆11位手机号） */
  String mobileNo;
  /** 自定义业务标识，将在异步通知及跳转时携带返回对接方 */
  String contextId;
  /** 实名结束后异步通知地址,具体见"异步通知"章节说明 */
  String notifyUrl;
  /**
   * 指定运营商3要素信息比对结果详情
   *
   * <p>ADVANCED - 详情版（核验失败时会返回具体不匹配信息）
   *
   * <p>STANDARD - 普通版（默认值）
   */
  String grade;
  /**
   * 指定运营商3要素信息比对来源，默认0，但建议传2
   *
   * <p>0 - 权威库比对； 对用户3要素进行权威数据源的比对，收取运营商3要素信息比对费用
   *
   * <p>1 - 可信数据比对；
   * 可信核身数据有效期内，直接发送短信验证码，仅收取短信费用，若无可信数据，接口会报错（限在自2024年4月12日起的一年内，在当前appId下做过运营商3要素核身认证场景使用，并且需保证姓名、身份证号和手机号三者与之前的认证数据完全一致）
   *
   * <p>2 - 优先策略比对； 优先进行可信数据源比对，若无可信数据，则进行权威库比对（为了避免直接使用 “1- 可信数据比对”造成接口报错，建议传2，由e签宝自动判断）
   */
  Integer source;

  @Override
  public String url() {
    return "/v2/identity/auth/api/individual/telecom3Factors";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthIndividualTelecom3FactorsRes> clazz() {
    return IdentityAuthIndividualTelecom3FactorsRes.class;
  }
}
