package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueTienLuongCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueTienLuongCongRepository extends JpaRepository<ThueTienLuongCong,Integer> {
}
