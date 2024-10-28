package com.ptit.product_search.exception.base;


import static com.ptit.product_search.exception.base.StatusConstants.BAD_REQUEST;

public class BadRequestException extends BaseException {
  public BadRequestException() {
    setCode("com.ptit.product_search.exception.base.BadRequestException");
    setStatus(BAD_REQUEST);
  }

  public BadRequestException(String message) {
    setCode(message);
    setStatus(BAD_REQUEST);
  }

}
