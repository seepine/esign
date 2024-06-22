package com.seepine.esign.module.sign.flow.create;

import com.seepine.esign.module.sign.flow.create.signflowconfig.AuthConfig;
import com.seepine.esign.module.sign.flow.create.signflowconfig.NoticeConfig;
import com.seepine.esign.module.sign.flow.create.signflowconfig.RedirectConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签署流程配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowConfig {
  /** 签署流程主题（将展示在签署通知和签署页的任务信息中） */
  private String signFlowTitle;
  /**
   * 签署截止时间， unix时间戳（毫秒）格式，
   *
   * <p>补充说明：
   *
   * <p>默认在签署流程创建后的90天时截止（指定值最大不能超过90天，只能指定90天内的时间戳）。签署中如需延期请调用【延期签署截止时间】接口。
   */
  Long signFlowExpireTime;

  /**
   * 自动开启签署流程，默认值 true
   *
   * <p>true - 自动开启（发起签署流程，将直接进入“签署中”状态）
   *
   * <p>false - 非自动开启（发起“草稿”状态的签署流程，需调用【开启签署流程】接口后流程进入“签署中”状态）
   *
   * <p>补充说明：
   *
   * <p>自动开启的流程不允许再追加待签署文件，点击这里了解更多流程状态说明。
   */
  Boolean autoStart;
  /**
   * 所有签署方签署完成后流程自动完结，默认值 false
   *
   * <p>true - 自动完结
   *
   * <p>false - 非自动完结，需调用【完结签署流程】接口完结
   *
   * <p>补充说明：
   *
   * <p>设置了自动完结的流程中不允许再追加签署区、抄送方，点击这里了解更多流程状态说明。
   */
  Boolean autoFinish;

  /** 重定向配置项 */
  RedirectConfig redirectConfig;
  /** 接收相关回调通知的Web地址，详见【签署回调通知接收说明】 */
  String notifyUrl;

  //  /**
  //   * 身份校验配置项（当开发者指定的签署人信息与该签署人在e签宝已有的身份信息不一致时如何处理），默认：true
  //   *
  //   * <p>true - 接口报错（提示：传入的指定签署人信息与实名信息不一致相关报错）
  //   *
  //   * <p>false - 不报错，正常发起（签署人可以在签署链接中修改账号信息，开发者再通过回调通知接收相关改动信息，详见【签署人更正个人信息回调通知】）
  //   *
  //   * <p>补充说明：
  //   *
  //   * <p>关于用户侧页面报错账号信息不一致：点击查看 解决方案
  //   */
  //  Boolean identityVerify;
  //  /** 计费配置项 */
  //  ChargeConfig chargeConfig;
  //  /** 签署配置项 */
  //  SignFlowSignConfig signConfig;
  /** 流程整体通知配置项 */
  NoticeConfig noticeConfig;
  /** 流程整体认证配置项 */
  AuthConfig authConfig;
  //  /**
  //   * 企业合同归档文件夹ID（点击这里了解更多合同管理功能）
  //   *
  //   * <p>补充说明：
  //   *
  //   * <p>通过e签宝SaaS官网进行设置和复制文件夹ID：登录官网企业空间首页-合同管理-企业合同-已归档合同中新建分类-详情中复制分类ID
  //   * 归档文件夹ID必须在合同发起方的e签宝官网企业空间下建立 如果合同需要归档分类到多个文件夹中，该字段中可以传入多个文件夹ID，最多可传入10个
  //   */
  //  List<String> contractGroupIds;
}
