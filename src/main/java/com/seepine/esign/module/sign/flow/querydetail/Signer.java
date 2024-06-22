package com.seepine.esign.module.sign.flow.querydetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signer {
  /** 个人签署方信息 */
  PsnInfo psnSigner;
  /** 机构签署方信息 */
  OrgSigner orgSigner;

  /**
   * 签署方类型
   *
   * <p>0 -个人，1 -机构（包含法定代表人和经办人签)
   *
   * <p>【注】具体的机构类型可以根据签署区中的signFieldSealType字段判断
   */
  Integer signerType;
  /** 签署顺序 */
  Integer signOrder;
  /**
   * 当前签署状态
   *
   * <p>0 - 等待签署
   *
   * <p>1 - 签署中
   *
   * <p>2 - 已签署
   *
   * <p>3 - 等待审批
   *
   * <p>4 - 已拒签
   */
  Integer signStatus;

  /**
   * 签署任务类型
   *
   * <p>0 - 会签
   *
   * <p>1 - 或签
   */
  Integer signTaskType;

  /** 个人签署方信息 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PsnInfo {
    /** 个人账号ID */
    String psnId;
    /** 个人姓名 */
    String psnName;
    /** 个人账号标识（手机号/邮箱） */
    PsnAccount psnAccount;
  }

  /** 个人账号标识（手机号/邮箱） */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PsnAccount {
    /** 个人手机号 */
    String accountMobile;
    /** 个人邮箱号 */
    String accountEmail;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OrgSigner {
    /** 机构账号ID */
    String orgId;
    /** 机构名称 */
    String orgName;
    /** 个人账号标识（手机号/邮箱） */
    PsnInfo transactor;
  }
}
