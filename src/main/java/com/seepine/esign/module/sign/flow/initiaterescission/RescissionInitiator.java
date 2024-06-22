package com.seepine.esign.module.sign.flow.initiaterescission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 合同解约发起方信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RescissionInitiator {
  /** 解约发起人信息 */
  Psn psnInitiator;
  /** 解约发起机构信息 */
  Org orgInitiator;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Psn {
    /** 个人账号ID */
    String psnId;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Org {
    String orgId;
    Transactor transactor;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Transactor {
    /** 经办人账号ID */
    String psnId;
  }
}
