package com.ptit.product_search.service.base.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import com.ptit.product_search.service.base.MessageService;

import java.util.Locale;

@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
  private final MessageSource messageSource;

  public String getMessage(String code, String language) {
    try {
      return messageSource.getMessage(code, null, new Locale(language));
    } catch (Exception ex) {
      return code;
    }
  }
}

