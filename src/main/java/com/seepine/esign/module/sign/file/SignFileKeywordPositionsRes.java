package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignRes;
import com.seepine.esign.module.sign.file.dto.KeywordPosition;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFileKeywordPositionsRes extends ESignRes {
  /** 关键字信息 */
  List<KeywordPosition> keywordPositions;
}
