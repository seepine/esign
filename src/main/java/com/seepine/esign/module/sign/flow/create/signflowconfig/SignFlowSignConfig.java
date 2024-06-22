package com.seepine.esign.module.sign.flow.create.signflowconfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签署人配置类 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowSignConfig {
  /**
   * 签署终端类型
   *
   * <p>1 - 网页（自适配H5/PC样式）
   *
   * <p>2 - 支付宝
   */
  String availableSignClientTypes;
  /**
   * 签署页面是否显示“一键落章”按钮
   *
   * <p>true - 显示“一键落章”
   *
   * <p>false - 不显示“一键落章”
   */
  Boolean showBatchDropSealButton;
  /**
   * 签署模式，默认：NORMAL
   *
   * <p>NORMAL - 中国大陆签
   *
   * <p>GLOBAL - 海外签
   */
  String signMode;
  /**
   * 专属云项目ID
   *
   * <p>补充说明：
   *
   * <p>专属云：文件需要存储在开发者本地系统，购买了专属云服务时使用； 专属云项目ID需要先在【获取文件上传地址】接口传入，这里传同一个项目ID才可用。
   */
  String dedicatedCloudId;
}
