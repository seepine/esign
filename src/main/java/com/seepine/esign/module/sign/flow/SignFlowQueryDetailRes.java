package com.seepine.esign.module.sign.flow;

import com.seepine.esign.common.ESignRes;
import com.seepine.esign.module.sign.flow.create.SignFlowConfig;
import com.seepine.esign.module.sign.flow.querydetail.*;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFlowQueryDetailRes extends ESignRes {
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
  /**
   * 签署流程描述
   *
   * <p>如果流程已拒签或已撤销，并且存在拒签或撤回原因，流程描述显示为原因,，否则默认为流程状态描述
   */
  String signFlowDescription;
  /**
   * 签署流程的解约状态
   *
   * <p>0 - 未解约
   *
   * <p>1 - 解约中
   *
   * <p>2 - 部分解约
   *
   * <p>3 - 已解约
   */
  Integer rescissionStatus;
  /** 对应的解约协议签署流程ID */
  List<String> rescissionSignFlowIds;
  /** 签署流程创建时间（毫秒级时间戳格式） */
  Long signFlowCreateTime;
  /** 签署流程开启时间（毫秒级时间戳格式） */
  Long signFlowStartTime;
  /** 签署流程结束时间（毫秒级时间戳格式） */
  Long signFlowFinishTime;
  /** 签署流程发起方 */
  SignFlowInitiator signFlowInitiator;
  /** 签署流程配置项 */
  SignFlowConfig signFlowConfig;
  /** 签署文件信息 */
  List<QueryDocs> docs;
  /** 附件信息 */
  List<Attachment> attachments;
  /** 签署方信息 */
  List<Signer> signers;
  /** 抄送方 */
  List<Copier> copiers;
}
