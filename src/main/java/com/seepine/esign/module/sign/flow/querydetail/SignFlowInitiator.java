package com.seepine.esign.module.sign.flow.querydetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签署流程发起方 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowInitiator {
  /** 个人发起方信息 */
  PsnInitiator psnInitiator;
  /** 机构发起方信息 */
  OrgInitiator orgInitiator;

  /** 个人发起方信息 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PsnInitiator {
    /** 个人发起方账号ID */
    String psnId;
    /** 个人发起方姓名 */
    String psnName;
  }

  /** 机构发起方信息 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OrgInitiator {
    /** 机构发起方账号ID */
    String orgId;
    /** 机构发起方企业名称 */
    String orgName;
    /** 机构发起方的经办人 */
    Transactor transactor;

    /** 机构发起方的经办人 */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transactor {
      /** 经办人账号ID */
      String psnId;
      /** 经办人姓名 */
      String psnName;

      /** 经办人账号信息 */
      Signer.PsnAccount psnAccount;
    }
  }
}
