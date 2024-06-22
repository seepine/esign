package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFlowCreateByFileRes extends ESignRes {
  /** 签署流程ID（建议开发者保存此流程ID） */
  String signFlowId;
}
