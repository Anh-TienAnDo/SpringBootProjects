package com.ptit.graduation.repository.base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseMySqlRepository<T> extends JpaRepository<T, Long> {
    
}
