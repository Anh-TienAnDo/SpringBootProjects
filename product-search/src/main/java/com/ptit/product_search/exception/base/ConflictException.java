package com.ptit.product_search.exception.base;


import static com.ptit.product_search.exception.base.StatusConstants.CONFLICT;

public class ConflictException extends BaseException {
  public ConflictException(String id, String objectName) {
    setStatus(CONFLICT);
    setCode("com.ptit.product_search.exception.base.ConflictException");
    addParam("id", id);
    addParam("objectName", objectName);
  }

  public ConflictException(){
    setStatus(CONFLICT);
    setCode("com.ptit.product_search.exception.base.ConflictException");
  }
}
