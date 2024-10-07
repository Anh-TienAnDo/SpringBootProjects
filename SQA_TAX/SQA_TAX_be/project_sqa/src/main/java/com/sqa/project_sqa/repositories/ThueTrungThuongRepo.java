package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueTrungThuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueTrungThuongRepo extends JpaRepository<ThueTrungThuong,Integer> {
}
