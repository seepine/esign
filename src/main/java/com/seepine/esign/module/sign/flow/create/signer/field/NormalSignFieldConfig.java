package com.seepine.esign.module.sign.flow.create.signer.field;

import com.seepine.esign.module.sign.flow.create.signer.enums.OrgSealBizType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 签章区配置项 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NormalSignFieldConfig {
  /**
   * 是否自由签章，默认值 false
   *
   * <p>true - 是，false - 否
   *
   * <p>补充说明：
   *
   * <p>自由签章 指不限制印章、签署位置、签章样式（单页、骑缝）、和签章个数。 自由签章模式下，无需传normalSignFieldConfig对象下的其他参数。
   */
  Boolean freeMode;
  /**
   * 是否后台自动落章，默认值 false
   *
   * <p>true - 后台自动落章（无感知），false - 签署页手动签章
   *
   * <p>补充说明：
   *
   * <p>当签署方为个人时，不支持自动签章。 当签署方为机构（且非应用Id所属企业），静默签署自动落章需先经过印章授权，点击查看印章授权规则。
   * 当签署方为应用Id所属主体企业自身静默签署时，支持后台自动落章。
   */
  Boolean autoSign;

  /** 是否可以移动签章区 true - 可以，false - 不可以 */
  Boolean movableSignField;

  /**
   * 指定印章ID（印章ID是e签宝SaaS官网的印章编号）
   *
   * <p>【注】不指定印章ID则取默认印章，一般用于企业自动盖章需要切换默认印章场景
   */
  String assignedSealId;
  /** 手动签章时页面可选的印章列表（印章ID是e签宝SaaS官网的印章编号） */
  List<String> availableSealIds;
  /**
   * 页面可选机构印章类型，默认值ALL（多项请使用英文逗号分隔）
   *
   * <p>ALL - 显示所有类型的印章
   *
   * <p>PUBLIC - 机构主体公章
   *
   * <p>CONTRACT - 合同专用章
   *
   * <p>FINANCE - 财务专用章
   *
   * <p>PERSONNEL -人事专用章
   *
   * <p>COMMON -其他类印章（无具体业务类型的章）
   */
  OrgSealBizType orgSealBizTypes;
  /**
   * 页面可选个人印章样式，默认值0和1（英文逗号分隔）
   *
   * <p>0 - 手写签名
   *
   * <p>1 - 姓名印章
   *
   * <p>2 - 手写签名AI校验
   */
  String psnSealStyles;
  /**
   * 签章区尺寸（正方形的边长，单位为px）
   *
   * <p>补充说明：
   *
   * <p>指定的签署区的宽度，高度等比缩放；不指定默认以印章原始大小加盖 不能与signFieldWidth、signFieldHeight同时传入
   */
  Integer signFieldSize;
  /**
   * 签署区宽度（矩形的左右边距距离，单位为px）
   *
   * <p>补充说明：
   *
   * <p>印章需要自定义规格时传入该参数（根据指定的签署区宽高适配）；不指定默认以印章原始大小加盖 与signFieldHeight搭配使用，但不能与signFieldSize同时传入
   */
  Integer signFieldWidth;
  /**
   * 签署区高度（矩形的上下边距距离，单位为px）
   *
   * <p>补充说明：
   *
   * <p>印章需要自定义规格时传入该参数（根据指定的签署区宽高适配）；不指定默认以印章原始大小加盖 与signFieldWidth搭配使用，但不能与signFieldSize同时传入
   */
  Integer signFieldHeight;
  /**
   * 签章区样式
   *
   * <p>1 - 单页签章，2 - 骑缝签章
   */
  Integer signFieldStyle;
  /** 签章区位置信息 */
  SignFieldPosition signFieldPosition;
}
