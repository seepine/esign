package com.seepine.esign.module.identity.auth.individual.face;

import com.seepine.esign.common.ESignReq;
import com.seepine.esign.enums.CertType;
import com.seepine.esign.module.identity.auth.individual.face.enums.FaceauthMode;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【人脸识别认证】个人核身
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/identity_service/wbsb6y">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityAuthIndividualFaceReq implements ESignReq<IdentityAuthIndividualFaceRes> {
  /** 姓名 */
  String name;
  /** 个人证件类型 */
  CertType certType;
  /** 个人证件号 */
  String idNo;
  /** 指定刷脸认证方式 */
  FaceauthMode faceauthMode;
  /** 认证完成后重定向地址 */
  String callbackUrl;
  /** 自定义业务标识 */
  String contextId;
  /** 认证结束后异步通知地址,具体见"异步通知"章节说明 */
  String notifyUrl;

  @Override
  public String url() {
    return "/v2/identity/auth/api/individual/face";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<IdentityAuthIndividualFaceRes> clazz() {
    return IdentityAuthIndividualFaceRes.class;
  }
}
