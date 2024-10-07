package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

}
