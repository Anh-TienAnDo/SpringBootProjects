package com.ptit.product_search.service.base.impl;


import java.util.List;

import com.ptit.product_search.exception.base.NotFoundException;
import com.ptit.product_search.repository.base.BaseRepository;
import com.ptit.product_search.service.base.BaseService;


public class BaseServiceImpl<T> implements BaseService<T> {
  private final BaseRepository<T> repository;

  public BaseServiceImpl(BaseRepository<T> repository) {
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
  public void delete(String id) {
    repository.delete(get(id));
  }

  @Override
  public T get(String id) {
    return repository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public List<T> list() {
    return repository.findAll();
  }
}
