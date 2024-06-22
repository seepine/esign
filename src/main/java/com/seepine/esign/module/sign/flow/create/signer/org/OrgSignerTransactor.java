package com.seepine.esign.module.sign.flow.create.signer.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 指定本次解约机构签署方经办人信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgSignerTransactor {
  /**
   * 经办人账号标识，手机号或邮箱（指定orgName时，该参数为必传项）
   *
   * <p>【注】为了保证签署人准确，建议配合姓名信息传入
   */
  String psnAccount;
  /**
   * 经办人身份信息
   */
  PsnInfo psnInfo;

}
