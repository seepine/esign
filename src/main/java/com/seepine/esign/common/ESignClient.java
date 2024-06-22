package com.seepine.esign.common;

import com.seepine.esign.util.ESignUtil;

public class ESignClient {
  private final ESignProperties properties;

  public ESignClient(ESignProperties properties) {
    this.properties = properties;
  }

  public <T extends ESignRes> T execute(ESignReq<T> req) throws ESignException {
    return ESignUtil.request(properties, req);
  }
}
