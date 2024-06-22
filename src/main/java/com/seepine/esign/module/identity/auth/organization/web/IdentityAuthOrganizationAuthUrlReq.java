package com.seepine.esign.module.identity.auth.organization.web;

import com.seepine.esign.common.ESignReq;
import com.seepine.esign.module.identity.auth.organization.web.dto.ConfigParams;
import com.seepine.esign.module.identity.auth.organization.web.dto.ContextInfo;
import com.seepine.esign.module.identity.auth.organization.web.dto.OrgEntity;
import com.seepine.esign.module.identity.auth.organization.web.enums.AgentAuthType;
import com.seepine.esign.module.identity.auth.organization.web.enums.AuthType;
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
public class IdentityAuthOrganizationAuthUrlReq
    implements ESignReq<IdentityAuthOrganizationAuthUrlRes> {

  /** 指定默认认证方式（打开认证页面所展示的第一个认证方式） */
  AuthType authType;
  /** 指定页面可选的认证方式 */
  List<AuthType> availableAuthTypes;

  /** 指定页面可选的办理人认证方式 */
  List<AgentAuthType> agentAvailableAuthTypes;
  /**
   * 指定办理人通过银行卡认证或运营商认证方式时，是否使用详情版，如指定则核验失败可返回具体不匹配信息，传空默认为普通版。
   *
   * <p>PSN_BANK4_AUTHCODE - 个人银行卡四要素认证 PSN_TELECOM_AUTHCODE - 个人运营商三要素认证
   */
  List<String> agentAuthAdvancedEnabled;

  /** 接收实名认证链接短信通知的手机号。传入手机号会发送邀请实名认证短信通知到该用户，并收取一次短信费用，不传入则不发送，不产生短信费用 */
  String receiveUrlMobileNo;

  /** 组织机构基本信息 */
  OrgEntity orgEntity;

  /** 认证配置信息 */
  ConfigParams configParams;

  /** 业务方交互上下文信息 */
  ContextInfo contextInfo;

  @Override
  public String url() {
    return "/v2/identity/auth/web/orgAuthUrl";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthOrganizationAuthUrlRes> clazz() {
    return IdentityAuthOrganizationAuthUrlRes.class;
  }
}
