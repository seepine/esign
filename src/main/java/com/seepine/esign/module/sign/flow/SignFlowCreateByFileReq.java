package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignReq;
import com.seepine.esign.module.sign.flow.create.Docs;
import com.seepine.esign.module.sign.flow.create.SignFlowConfig;
import com.seepine.esign.module.sign.flow.create.Signer;
import com.seepine.http.Method;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基于文件发起签署（分为精简版和完整版，是同一个接口）
 *
 * <p><a href="https://open.esign.cn/doc/opendoc/pdf-sign3/nxhgcl3bfgqz8qlz">文档</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowCreateByFileReq implements ESignReq<SignFlowCreateByFileRes> {
  /** 设置待签署文件信息 */
  List<Docs> docs;
  /** 签署流程配置项 */
  SignFlowConfig signFlowConfig;
  /** 签署方信息（指参与签署的个人或者机构） */
  List<Signer> signers;

  @Override
  public String url() {
    return "/v3/sign-flow/create-by-file";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFlowCreateByFileRes> clazz() {
    return SignFlowCreateByFileRes.class;
  }
}
