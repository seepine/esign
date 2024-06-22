package com.seepine.esign.module.sign.flow.create;

import com.seepine.esign.module.sign.flow.create.signer.PsnSignerInfo;
import com.seepine.esign.module.sign.flow.create.signflowconfig.NoticeConfig;
import com.seepine.esign.module.sign.flow.create.signer.OrgSignerInfo;
import com.seepine.esign.module.sign.flow.create.signer.SignField;
import com.seepine.esign.module.sign.flow.create.signer.SingerSignConfig;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签署方信息（指参与签署的个人或者机构） */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signer {
  /** 签署人配置项 */
  SingerSignConfig signConfig;
  /** 设置签署方的通知方式 */
  NoticeConfig noticeConfig;
  //  /** 签署方认证配置项 */
  //  AuthConfig authConfig;

  /**
   * 签署方类型，0 - 个人，1 - 机构，2 - 法定代表人，3 - 经办人签
   *
   * <p>若指定签署方为个人，则psnSignerInfo为必传项； 若指定签署方为机构或法定代表人手动签署（autoSign参数为false）时，则orgSignerInfo为必传项；
   * 若指定经办人签，在同级数组内必须还有机构类型存在，且orgSignerInfo为必传项，即：指定3 - 经办人签的前提是必须同时存在1 - 机构，且经办人签属于企业合同，不在个人名下。
   */
  Integer signerType;
  
  /** 机构签署方信息 */
  OrgSignerInfo orgSignerInfo;
  /** 个人签署方信息 */
  PsnSignerInfo psnSignerInfo;
  /** 签署区信息（设置签署方 盖章/签名/文字输入的区域） */
  List<SignField> signFields;
}
