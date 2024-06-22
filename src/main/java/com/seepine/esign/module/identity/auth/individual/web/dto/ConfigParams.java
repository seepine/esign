package com.seepine.esign.module.identity.auth.individual.web.dto;

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
   * 设置个人认证页面上不可修改的基本信息。传空表示可以修改
   *
   * <p>name - 姓名 certNo - 证件号 mobileNo - 手机号 bankCardNo - 银行卡号
   */
  List<String> indivUneditableInfo;
}
