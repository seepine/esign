package com.seepine.esign.common;

import java.io.Serializable;

public abstract class ESignRes implements Serializable {
  private static final Integer ZERO = 0;

  Integer code;
  String message;

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
