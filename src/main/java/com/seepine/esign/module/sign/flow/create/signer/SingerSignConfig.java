package com.seepine.esign.module.sign.flow.create.signer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingerSignConfig {
  /**
   * 设置签署方的签署顺序
   *
   * <p>按序签时支持传入顺序值 1 - 255（值小的先签署） 同时签时，允许值重复
   */
  Integer signOrder;
  /** 设置签署页面强制阅读倒计时时间，默认值为 0（单位：秒，最大值999） */
  Integer forcedReadingTime;
  /**
   * 签署人是否需要免意愿快捷签署，默认false
   *
   * <p>true - 需要
   *
   * <p>false - 不需要
   *
   * <p>补充说明：
   *
   * <p>免意愿快捷签署需要提前联系与您对接的交付顾问开通后才可用；
   * 免意愿快捷签署是指用户在e签宝页面签署过程中勾选同意《快捷签署服务协议》后，当前用户在约定时间内（默认7天）再次在当前开发者appId、当前终端设备下签署即可免除意愿认证，直接签署成功。
   */
  Boolean agreeSkipWillingness;
  /**
   * 签署任务类型，默认值为 0
   *
   * <p>0 - 会签（所有指定的签署方均必须签署）
   *
   * <p>1 - 或签（多个签署方中，任意一方签署即可完成签署流程）
   *
   * <p>场景对接说明详见：【会签与或签】
   *
   * <p>或签场景补充说明：
   *
   * <p>指定的签署方数量必须>=2，其中任意一方签署即可 所有签署方和签署区的配置以及签署的文件需要一致 或签不允许自动签署 不允许同一个经办人代不同的主体或签
   */
  Integer signTaskType;
  /**
   * 签署前提示弹框自定义签署声明--文案标题（最多20字）
   *
   * <p>补充说明：
   *
   * <p>当前签署方在签署页面进入后，展示该弹框提示，点击“我已知悉上述内容”按钮后关闭弹框，进入签署合同页。 必须与下方signTipsContent字段配套使用。
   */
  String signTipsTitle;
  /**
   * 签署前提示弹框自定义签署声明--文案内容（最多500字）
   *
   * <p>补充说明：
   *
   * <p>当前签署方在签署页面进入后，展示该弹框提示，点击“我已知悉上述内容”按钮后关闭弹框，进入签署合同页。 必须与上方signTipsTitle字段配套使用。
   */
  String signTipsContent;

  List<UploadFile> uploadFiles;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UploadFile {
    /**
     * 附件的标题描述，会显示在签署详情页内
     *
     * <p>比如：身份证信息面、身份证国徽页
     */
    String uploadDescription;
    /**
     * 此附件是否必传，默认true
     *
     * <p>true - 必传
     *
     * <p>false - 非必传
     *
     * <p>【注】如设置了必传，但是签署方在页面没有上传是无法提交签署的
     */
    Boolean required;
  }
}
