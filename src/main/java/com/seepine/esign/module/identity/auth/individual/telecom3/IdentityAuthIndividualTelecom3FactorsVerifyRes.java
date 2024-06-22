package com.seepine.esign.module.identity.auth.individual.telecom3;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityAuthIndividualTelecom3FactorsVerifyRes extends ESignRes {
  /** 认证流程ID */
  String flowId;
}
