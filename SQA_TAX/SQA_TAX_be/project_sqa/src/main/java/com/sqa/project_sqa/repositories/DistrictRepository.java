package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, String> {
    @Query("SELECT d FROM District d WHERE d.province.code = :provinceCode")
    List<District> findByProvinceCode(String provinceCode);
}
