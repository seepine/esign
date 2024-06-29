package com.seepine.esign.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seepine.http.Method;

import java.io.Serializable;

public interface ESignReq<T extends ESignRes> extends Serializable {
  long serialVersionUID = 1L;

  @JsonIgnore
  String url();

  @JsonIgnore
  Method method();

  @JsonIgnore
  Class<T> clazz();
}
