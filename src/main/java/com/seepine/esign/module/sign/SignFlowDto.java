package com.seepine.esign.module.sign;

import com.seepine.esign.module.sign.file.dto.Positions;
import com.seepine.esign.module.sign.flow.create.enums.WillingnessAuthMode;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowDto implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 合同名，带上扩展名，例如 合同.docx */
  String contractName;
  /** 合同文件 */
  ByteArrayOutputStream contractFile;

  /** 组织盖章位置关键词，例如：甲方盖章： */
  String orgSignKeyword;

  List<Positions> orgSignPositions;
  /** 个人盖章位置关键词，例如：乙方盖章/签字： */
  String psnSignKeyword;

  List<Positions> psnSignPositions;

  /**
   * 组织印章id，若外部企业，需要先授权
   *
   * <p>测试环境需要从 <a href="https://smlfront.esign.cn:8880/templates-manage/manage">沙箱后台</a> 获取
   */
  String orgSealId;
  /** 组织是否开启所有页面骑缝章 */
  Boolean orgSealPagingAll;

  /** 个人手机号 */
  String psnPhone;
  /** 个人姓名 */
  String psnFullName;
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
  /** 个人意愿认证方式 */
  List<WillingnessAuthMode> psnWillingnessAuthModes;

  /** 重定向url，可空 */
  String redirectUrl;
  /** 通知url，可空 */
  String notifyUrl;
}
