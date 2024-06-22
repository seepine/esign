package com.seepine.esign.module.identity.auth.individual.web;

import com.seepine.esign.common.ESignReq;
import com.seepine.esign.module.identity.auth.individual.web.dto.ConfigParams;
import com.seepine.esign.module.identity.auth.individual.web.dto.ContextInfo;
import com.seepine.esign.module.identity.auth.individual.web.dto.IndivInfo;
import com.seepine.esign.module.identity.auth.individual.web.enums.AuthType;
import com.seepine.http.Method;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取个人核身认证地址
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/sg2nty">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualAuthUrlReq
    implements ESignReq<IdentityAuthIndividualAuthUrlRes> {
  /** 指定默认认证类型（打开认证页面所展示的第一个认证类型） */
  AuthType authType;
  /** 指定页面显示认证方式 */
  List<AuthType> availableAuthTypes;
  /**
   * 指定通过银行卡认证或运营商认证方式时，是否使用详情版，如指定则核验失败可返回具体不匹配信息，传空默认为普通版。
   *
   * <p>PSN_BANK4_AUTHCODE - 个人银行卡四要素认证 PSN_TELECOM_AUTHCODE - 个人运营商三要素认证
   * 【注】详情版：需要单独购买，具体购买方式请咨询e签宝工作人员；
   *
   * <p>普通版：信息比对核验失败，不会返回具体的不匹配信息。
   */
  List<AuthType> authAdvancedEnabled;
  /** 个人实名认证的基本信息 */
  IndivInfo indivInfo;
  /** 认证配置信息 */
  ConfigParams configParams;
  /** 业务方交互上下文信息，有统计需求或者分账需求必填部分参数 */
  ContextInfo contextInfo;

  @Override
  public String url() {
    return "/v2/identity/auth/web/indivAuthUrl";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthIndividualAuthUrlRes> clazz() {
    return IdentityAuthIndividualAuthUrlRes.class;
  }
}
