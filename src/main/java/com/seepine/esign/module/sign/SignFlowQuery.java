package com.seepine.esign.module.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 当前流程的状态
   *
   * <p>0 - 草稿
   *
   * <p>1 - 签署中
   *
   * <p>2 - 完成
   *
   * <p>3 - 撤销
   *
   * <p>5 - 过期（签署截至日期到期后触发）
   *
   * <p>7 - 拒签
   */
  Integer signFlowStatus;

  /**
   * 是否完成签署
   *
   * @return true/false
   */
  public boolean isFinish() {
    return Integer.valueOf(2).equals(signFlowStatus);
  }
  /** 已签署文件下载链接（有效期为60分钟，过期后可以重新调用接口获取新的下载地址） */
  String downloadUrl;
  /** 人脸url，签署完成后返回 */
  String facePhotoUrl;

  String orgId;
  String orgName;

  String psnId;
  String psnName;
  String psnPhone;
}
