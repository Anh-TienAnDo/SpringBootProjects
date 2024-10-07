package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueChuyenNhuongBatDongSanRepo extends JpaRepository<ThueChuyenNhuongBDS,Integer> {
}
