package com.seepine.esign.module.sign.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignFileKeywordPositionsReq implements ESignReq<SignFileKeywordPositionsRes> {
  @JsonIgnore private String fileId;
  private List<String> keywords;

  @Override
  public String url() {
    return "/v3/files/" + this.fileId + "/keyword-positions";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFileKeywordPositionsRes> clazz() {
    return SignFileKeywordPositionsRes.class;
  }
}
