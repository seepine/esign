package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFlowSignUrlRes extends ESignRes {
  /** 签署短链接（有效期180天） */
  String shortUrl;
  /**
   * 签署长链接（永久有效）
   *
   * <p>【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
   */
  String url;
}
