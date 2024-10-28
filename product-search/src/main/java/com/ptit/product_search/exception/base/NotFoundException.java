package com.ptit.product_search.exception.base;


import static com.ptit.product_search.exception.base.StatusConstants.NOT_FOUND;

public class NotFoundException extends BaseException {
  public NotFoundException(String id, String objectName) {
    setCode("com.ptit.product_search.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
    addParam("id", id);
    addParam("objectName", objectName);
  }

  public NotFoundException() {
    setCode("com.ptit.product_search.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
  }
}
