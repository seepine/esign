package com.seepine.esign.module.identity.verify.individual.idcard2;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityVerifyIndividualIdCard2Res extends ESignRes {
  /** 信息比对业务Id，请注意保存该参数 */
  String verifyId;
}
