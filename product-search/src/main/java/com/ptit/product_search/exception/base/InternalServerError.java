package com.ptit.product_search.exception.base;


import static com.ptit.product_search.exception.base.StatusConstants.INTERNAL_SERVER_ERROR;

public class InternalServerError extends BaseException {
  public InternalServerError() {
    setCode("com.ptit.product_search.exception.base.InternalServerError");
    setStatus(INTERNAL_SERVER_ERROR);
  }
}
