package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.LoaiThue;

import java.util.List;
import java.util.Optional;

public interface LoaiThueService {
    List<LoaiThue> getAll();

    Optional<LoaiThue> getById(int id);
}
