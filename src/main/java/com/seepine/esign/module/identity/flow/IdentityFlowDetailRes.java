package com.seepine.esign.module.identity.flow;

import com.seepine.esign.common.ESignRes;
import com.seepine.esign.module.identity.flow.dto.IndivInfo;
import com.seepine.esign.module.identity.flow.dto.OrganInfo;
import com.seepine.esign.module.identity.flow.enums.ObjectType;
import com.seepine.esign.module.identity.flow.enums.Status;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityFlowDetailRes extends ESignRes {
  /** 认证流程ID */
  String flowId;

  /** 认证流程状态 */
  Status status;

  /** 认证主体类型 */
  ObjectType objectType;

  /** 流程创建时间（unix毫秒时间戳） */
  Long startTime;

  /** 流程最后更新时间（unix毫秒时间戳） */
  Long endTime;
  /** 认证失败的原因 */
  String failReason;

  /** 实名认证所使用的认证类型 */
  String authType;

  /** 企业实名认证中，经办人所使用的认证类型（只有企业认证流程才会返回） */
  String agentAuthType;

  /** 认证长链接，链接永久有效 */
  String url;

  /** 认证短链接，有效期30天 */
  String shortLink;

  /** 企业组织基本信息 */
  OrganInfo organInfo;
  /** 个人基本信息对象 */
  IndivInfo indivInfo;
}
