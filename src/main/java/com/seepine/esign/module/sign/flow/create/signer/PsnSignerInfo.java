package com.seepine.esign.module.sign.flow.create.signer;

import com.seepine.esign.module.sign.flow.create.signer.psn.PsnInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 个人签署方信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsnSignerInfo {
  /** 必填，个人账号标识（手机号或邮箱）用于登录e签宝官网的凭证 */
  String psnAccount;
  /** 个人签署方身份信息 */
  PsnInfo psnInfo;
}
