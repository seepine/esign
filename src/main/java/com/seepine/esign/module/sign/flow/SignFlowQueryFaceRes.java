package com.seepine.esign.module.sign.flow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.seepine.esign.common.ESignRes;
import com.seepine.esign.module.sign.flow.queryface.IdentityDetail;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import com.seepine.tool.util.Objects;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFlowQueryFaceRes extends ESignRes {
  /** 签署中的实名/意愿认证ID */
  String identityFlowId;
  /**
   * 实名/意愿认证方式，类型如下： 意愿认证方式： CODE_SMS 短信验证码 CODE_VOICE 语言短信验证码 FACE_ZHIMA_XY 支付宝刷脸
   * FACE_TECENT_CLOUD_H5 腾讯云刷脸FACE_FACE_LIVENESS_RECOGNITION 快捷刷脸 FACE_WE_CHAT_FACE 微信小程序刷脸
   * FACE_ALI_MINI_PROGRAM 支付宝小程序刷脸 FACE_AUDIO_VIDEO_DUAL 支付宝智能视频认证 VIDEO_WE_CHAT_VIDEO_DUAL
   * 微信智能视频认证 实名认证方式： INDIVIDUAL_TELECOM_3_FACTOR 个人运营商三要素 INDIVIDUAL_BANKCARD_4_FACTOR 个人银行卡四要素
   * FACEAUTH_ZMXY 支付宝刷脸 FACEAUTH_TECENT_CLOUD 腾讯云刷脸 FACEAUTH_ESIGN 快捷刷脸 FACEAUTH_WE_CHAT_FACE
   * 微信小程序刷脸 INDIVIDUAL_ALIPAY_ONECLICK 个人支付宝一键认证（支付宝小程序实名） INDIVIDUAL_ARTIFICIAL 个人人工实名
   */
  String identityType;
  /** 实名/意愿认证数据 其中包含的字符串数据需要开发者自行解析，字段内容如下： */
  IdentityDetail identityDetail;
  /** 认证类型： 1：意愿认证 2：实名认证 */
  String identityBizType;

  public void transfer() {
    if (!isSuccess()) {
      return;
    }
    JsonNode data = getData();
    if (data == null) {
      setCode(-1);
      setMessage("Not found");
      return;
    }
    ArrayNode arr = (ArrayNode) data;
    if (arr.size() <= 0) {
      setCode(-1);
      setMessage("Not found");
      return;
    }
    JsonObject obj = new JsonObject(arr.get(0));
    this.identityFlowId = obj.getStr("identityFlowId");
    this.identityType = obj.getStr("identityType");
    this.identityBizType = obj.getStr("identityBizType");
    this.identityDetail =
        Json.parse(Objects.require(obj.getStr("identityDetail"), "{}"), IdentityDetail.class);
  }
}
