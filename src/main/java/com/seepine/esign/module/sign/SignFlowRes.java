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
public class SignFlowRes implements Serializable {
  private static final long serialVersionUID = 1L;
  /** 签署流程ID（建议开发者保存此流程ID） */
  String signFlowId;
  /** 签署短链接（有效期180天） */
  String shortUrl;
  /**
   * 签署长链接（永久有效）
   *
   * <p>【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
   */
  String url;
}
