package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueDauTuVon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueDauTuVonRepo extends JpaRepository<ThueDauTuVon,Integer> {
}
