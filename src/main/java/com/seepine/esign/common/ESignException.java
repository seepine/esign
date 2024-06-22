package com.seepine.esign.common;

import com.seepine.tool.exception.RunException;

public class ESignException extends RunException {
  public ESignException(Exception e) {
    super(e);
  }

  public ESignException(String msg) {
    super(msg);
  }
}
