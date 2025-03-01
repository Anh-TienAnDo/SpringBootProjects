package com.ptit.graduation.service.base.impl;

import com.ptit.graduation.repository.base.BaseMySqlRepository;
import com.ptit.graduation.service.base.BaseMySqlService;
import com.ptit.graduation.exception.base.NotFoundException;

import java.util.List;


public class BaseMySqlServiceImpl<T> implements BaseMySqlService<T> {
  private final BaseMySqlRepository<T> repository;

  public BaseMySqlServiceImpl(BaseMySqlRepository<T> repository) {
    this.repository = repository;
  }

  @Override
  public T create(T t) {
    return repository.save(t);
  }

  @Override
  public T update(T t) {
    return repository.save(t);
  }

  @Override
  public void delete(Long id) {
    repository.delete(get(id));
  }

  @Override
  public T get(Long id) {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public List<T> list() {
    return repository.findAll();
  }
    
}
