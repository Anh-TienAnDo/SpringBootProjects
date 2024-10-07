package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {

    @Query("SELECT w FROM Ward w WHERE w.district.code = :districtCode")
    List<Ward> findByDistrictCode(String districtCode);
}
