package com.seepine.esign.module.sign.flow.queryface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityDetail {
  /**
   * 刷脸认证时刷脸照片（base64编码的照片图片数据）
   *
   * <p>补充说明：
   *
   * <p>● 此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>● 只有认证方式选择腾讯云人脸识别和快捷人脸识别时，才可以返回照片
   *
   * <p>● 地址有效期默认1个小时，过期后可以重新调用接口获取新的地址
   */
  String facePhotoUrl;
  /**
   * 刷脸认证时刷脸视频（base64编码的mp4视频数据）
   *
   * <p>补充说明：
   *
   * <p>● 此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>● 只有认证方式选择腾讯云刷脸、快捷刷脸和智能视频认证时，才可以返回视频
   *
   * <p>● 地址有效期默认1个小时，过期后可以重新调用接口获取新的地址
   */
  String faceVideoUrl;
  /** 刷脸活体率分值 */
  String livingScore;
  /** 刷脸相似度分值 */
  String similarity;
}
