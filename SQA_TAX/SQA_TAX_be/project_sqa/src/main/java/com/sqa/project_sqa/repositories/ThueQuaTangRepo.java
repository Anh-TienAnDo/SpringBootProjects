package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.ThueQuaTang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThueQuaTangRepo extends JpaRepository<ThueQuaTang,Integer> {
}
