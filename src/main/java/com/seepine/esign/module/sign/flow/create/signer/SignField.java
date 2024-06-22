package com.seepine.esign.module.sign.flow.create.signer;

import com.seepine.esign.module.sign.flow.create.signer.field.NormalSignFieldConfig;
import com.seepine.esign.module.sign.flow.create.signer.field.DateSignFieldConfig;
import com.seepine.esign.module.sign.flow.create.signer.field.RemarkSignFieldConfig;
import com.seepine.esign.module.sign.flow.create.signer.field.SignDateConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 签署区信息（设置签署方 盖章/签名/文字输入的区域） 【注】指定了签署方signers的情况下，签署区必传
 *
 * <p>（单个签署方若对应多个签署区，可传多个数组，整个流程中，签署区不能超过300个）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignField {
  /** 必填，签署区所在待签署文件ID */
  String fileId;
  /** 开发者自定义业务编号 */
  String customBizNum;

  /**
   * 签署区类型，默认值为 0
   *
   * <p>0 - 签章区 （添加印章、签名等）
   *
   * <p>1 - 备注区（添加备注文字信息等）
   *
   * <p>2 - 独立签署日期（添加单独的签署日期）
   */
  Integer signFieldType;
  /**
   * 该签署区是否必须签署，默认值为 true（必须签）
   *
   * <p>true - 是
   *
   * <p>false - 否
   *
   * <p>场景对接说明详见：【选签（非必须签）】（该参数设置：false时）
   *
   * <p>补充说明：
   *
   * <p>常规场景都是必须签署，不需要额外指定该参数为 false，如果需要选签（非必须签）功能，则不允许设置自动落章。
   */
  Boolean mustSign;
  /** 签章区配置项（指定signFieldType为 0 - 签章区时，该参数为必传项） */
  NormalSignFieldConfig normalSignFieldConfig;
  /**
   * 备注区配置项（指定signFieldType为 1 - 备注区时，该参数为必传项）
   *
   * <p>【注】备注区只支持个人签署方
   */
  RemarkSignFieldConfig remarkSignFieldConfig;
  /**
   * 签署区/备注区的签署日期配置项
   *
   * <p>补充说明：
   *
   * <p>该日期是跟签署区/备注区关联的，即一个签署区/备注区需要一个签署日期匹配，且必须和签署区/备注区在同一页码 当signFieldType（签署区类型）= 0（签章区）时，指定该参数
   */
  SignDateConfig signDateConfig;
  /**
   * 独立签署日期配置项
   *
   * <p>补充说明：
   *
   * <p>该日期是跟签署区/备注区独立的，只要保证一个用户下存在至少一个签署区/备注区即可，可以配置多个日期位置且支持和签署区/备注区不在同一页码 当signFieldType（签署区类型）=
   * 2（独立签署日期）时，指定该参数
   */
  DateSignFieldConfig dateSignFieldConfig;
}
