package com.seepine.esign.module.sign.flow.create.signer;

import com.seepine.esign.module.sign.flow.create.signer.org.OrgInfo;
import com.seepine.esign.module.sign.flow.create.signer.org.OrgSignerTransactor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构签署方信息
 *
 * <p>当签署主体为企业/机构用户、autoSign参数为false手动签章时，请必须传入此对象
 * 当企业/机构用户选择静默签署，autoSign参数为true自动落章时，此对象可以不传，后台会默认取appId所属主体企业盖章
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgSignerInfo {
  /** 机构名称（账号标识） */
  String orgName;
  /** 机构签署方信息（将展示在机构认证页面） */
  OrgInfo orgInfo;
  /**
   * 企业/机构经办人信息
   *
   * <p>企业/机构手动签署（autoSign为false），经办人信息必传； 企业/机构自动落章（autoSign为true），请不要传该参数。
   */
  OrgSignerTransactor transactorInfo;
}
