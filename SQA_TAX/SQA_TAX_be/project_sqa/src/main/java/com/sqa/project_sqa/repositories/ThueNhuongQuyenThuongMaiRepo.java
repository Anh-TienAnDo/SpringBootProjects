package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueNhuongQuyenThuongMaiRepo extends JpaRepository<ThueNhuongQuyenThuongMai,Integer> {
}
