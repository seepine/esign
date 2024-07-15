package com.seepine.esign.common;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

public abstract class ESignRes implements Serializable {
  private static final Integer ZERO = 0;
  private static final long serialVersionUID = 1L;

  Integer code;
  String message;
  JsonNode data;

  public boolean isSuccess() {
    return ZERO.equals(code);
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public JsonNode getData() {
    return data;
  }

  public void setData(JsonNode data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "ESignRes{"
        + "success="
        + isSuccess()
        + ", code="
        + getCode()
        + ", message='"
        + message
        + '\''
        + '}'
        + "\n";
  }
}
