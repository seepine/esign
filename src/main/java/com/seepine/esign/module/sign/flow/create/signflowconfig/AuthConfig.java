package com.seepine.esign.module.sign.flow.create.signflowconfig;

import com.seepine.esign.module.sign.flow.create.enums.OrgAvailableAuthMode;
import com.seepine.esign.module.sign.flow.create.enums.PsnAvailableAuthMode;
import com.seepine.esign.module.sign.flow.create.enums.WillingnessAuthMode;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 流程整体认证配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthConfig {
  /** 签署意愿认证方式可选项 */
  List<WillingnessAuthMode> willingnessAuthModes;
  /** 个人实名认证方式可选项 */
  List<PsnAvailableAuthMode> psnAvailableAuthModes;
  /** 机构实名认证方式可选项 */
  List<OrgAvailableAuthMode> orgAvailableAuthModes;
  /** 智能视频认证模板ID，请联系交付顾问提供 */
  List<String> audioVideoTemplateId;
}
