package com.seepine.esign.module.identity.auth.individual.face;

import com.seepine.esign.common.ESignRes;
import com.seepine.esign.module.identity.auth.individual.face.enums.FaceStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class IdentityAuthIndividualFaceQueryRes extends ESignRes {
  /** 刷脸结果状态 */
  FaceStatus status;
  /** 刷脸结果描述 */
  String message;
  /** 刷脸照片相似度得分（目前腾讯云刷脸和快捷刷脸才会返回具体值） */
  String similarity;
  /** 刷脸活体检测得分（目前腾讯云刷脸和快捷刷脸才会返回具体值） */
  String livingScore;
}
