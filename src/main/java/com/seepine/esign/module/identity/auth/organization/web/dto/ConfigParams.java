package com.seepine.esign.module.identity.auth.organization.web.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigParams {
  /**
   * 指定页面上不可修改的信息属性，未指定的信息属性可以修改。
   *
   * <p>name - 组织机构名称 certNo - 组织机构证件号 legalRepName - 法定代表人姓名 legalRepCertNo - 法定代表人身份证号 agentName -
   * 办理人姓名 agentIdNo - 办理人证件号
   */
  List<String> orgUneditableInfo;
}
