package com.seepine.esign.module.sign.flow.create.signflowconfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 计费配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeConfig {
  /**
   * 计费模式，默认0
   *
   * <p>0 - 接口集成方付费（指应用ID所属企业）
   *
   * <p>1 - 发起方企业付费（signFlowInitiator参数对应企业）
   */
  Integer chargeMode;
  /**
   * 订单套餐类型，默认为普通订单套餐。
   *
   * <p>可选值：DISTRIBUTION - 生态伙伴订单套餐
   *
   * <p>【注】只有已登记成为e签宝生态伙伴之后，才允许传值DISTRIBUTION
   */
  String orderType;
}
