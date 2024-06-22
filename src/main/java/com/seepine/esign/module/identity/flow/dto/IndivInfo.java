package com.seepine.esign.module.identity.flow.dto;

import com.seepine.esign.enums.CertType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndivInfo {
  /** 用户id */
  String accountId;
  /** 姓名 */
  String name;
  /** 证件号 */
  String certNo;
  /** 证件类型 */
  CertType certType;
  /** 国籍/地区 */
  String nationality;
  /** 手机号 */
  String mobileNo;
  /** 银行卡号 */
  String bankCardNo;
  /**
   * 刷脸认证时的刷脸照片（base64编码照片图片数据），有效期默认1个小时（过期可重新获取）
   *
   * <p>注：
   *
   * <p>1.此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>2.认证方式选择腾讯云人脸识别和快捷人脸识别时，才会返回该字段
   */
  String facePhotoUrl;
  /**
   * 腾讯云人脸识别时的多张刷脸照片（最多返回3张照片，base64编码照片图片数据），有效期默认1个小时（过期可重新获取）
   *
   * <p>注：此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   */
  List<String> facePhotoAllUrl;
  /**
   * 刷脸照片相似度得分
   *
   * <p>注：认证方式选择腾讯云人脸识别和快捷人脸识别时，才会返回该字段
   */
  String similarity;
  /**
   * 刷脸活体检测得分
   *
   * <p>注：认证方式选择腾讯云人脸识别和快捷人脸识别时，才会返回该字段
   */
  String livingScore;
  /**
   * 刷脸认证时刷脸视频（base64编码的mp4视频数据），有效期默认1个小时（过期可重新获取）
   *
   * <p>注：
   *
   * <p>1.此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>2.认证方式选择腾讯云人脸识别和快捷人脸识别时，才会返回该字段
   */
  String faceVideoUrl;
  /**
   * 腾讯云刷脸时的刷脸模式（仅使用腾讯云刷脸时返回）：
   *
   * <p>Action_Liveness_Detection 实时动作活体检测
   *
   * <p>Video_Liveness_Detection 录制视频活体检测（默认是实时检测，因为机型兼容性等问题降级到录制视频后返回该方式）
   */
  String faceType;
  /**
   * 微信小程序刷脸认证时上传的身份证正面照片（base64编码照片图片数据），有效期默认1个小时（过期可重新获取）
   *
   * <p>注：
   *
   * <p>1.此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>2.认证方式仅限微信小程序使用e签宝微信小程序刷脸时，才会返回该字段
   */
  String idCardFrontPicUrl;
  /**
   * 微信小程序刷脸认证时上传的身份证反面照片（base64编码照片图片数据），有效期默认1个小时（过期可重新获取）
   *
   * <p>注：
   *
   * <p>1.此字段默认不返回任何信息，需联系e签宝沟通业务场景通过后才可开通
   *
   * <p>2.认证方式仅限微信小程序使用e签宝微信小程序刷脸时，才会返回该字段
   */
  String idCardBackPicUrl;
}
