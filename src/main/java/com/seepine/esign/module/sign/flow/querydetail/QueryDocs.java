package com.seepine.esign.module.sign.flow.querydetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 待签署文件信息 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryDocs {
  /** 签署文件ID */
  String fileId;
  /** 文件名称 */
  String fileName;
  /** 文件编辑密码 */
  String fileEditPwd;
  /**
   * 合同编号
   *
   * <p>【注】根据e签宝SaaS官网上设置的规则生成（点击查看 如何设置合同编号），用户未设置则取默认生成的合同编号
   */
  String contractNum;
  /**
   * 合同类型ID
   *
   * <p>【注】通过e签宝SaaS官网进行设置的合同类型，如未设置则此字段返回空
   */
  String contractBizTypeId;
}
