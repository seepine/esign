package com.seepine.esign.module.sign.flow.querydetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 抄送方 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Copier {
  /** 个人抄送方信息 */
  CopierPsnInfo copierPsnInfo;
  /** 机构抄送方信息 */
  CopierOrgInfo copierOrgInfo;

  /** 机构抄送方信息 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CopierOrgInfo {
    /** 机构账号ID */
    String orgId;
    /** 机构抄送方名称 */
    String orgName;
  }

  /** 个人抄送方信息 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CopierPsnInfo {
    /** 个人抄送方账号 */
    String psnId;
    /** 个人抄送方账号，手机号或邮箱 */
    String psnAccount;
  }
}
