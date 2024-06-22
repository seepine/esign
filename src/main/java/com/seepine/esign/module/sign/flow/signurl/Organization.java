package com.seepine.esign.module.sign.flow.signurl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构签署方
 *
 * <p>一个流程中存在经办人代多个机构签署时，通过此参数分别获取对应机构的签署链接；
 *
 * <p>orgId与orgName二选一传入（必须与发起签署时账号保持一致）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
  /** 机构账号ID */
  String orgId;
  /** 机构名称 */
  String orgName;
}
