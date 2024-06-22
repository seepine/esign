package com.seepine.esign.module.sign.flow.initiaterescission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 指定本次解约使用自动签署的机构签署方 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoSignOrg {
  /** 机构签署方账号ID（orgName与orgId二选一传值即可） */
  String orgId;
  /** 机构签署方名称（orgName与orgId二选一传值即可） */
  String orgName;
  /** 若自动签，该参数必传，且必须传入解约参与方的印章ID（必须是企业印章ID，不能使用法人章） */
  String sealId;
}
