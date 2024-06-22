package com.seepine.esign.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.http.Method;

public interface ESignReq<T extends ESignRes> {
  @JsonIgnore
  String url();

  @JsonIgnore
  Method method();

  @JsonIgnore
  Class<T> clazz();
}
