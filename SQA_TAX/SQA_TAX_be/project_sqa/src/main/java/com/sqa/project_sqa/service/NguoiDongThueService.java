package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NguoiDongThueService {

    List<NguoiDongThue> getAll();

    NguoiDongThue getNguoiDongThueByMaSoThue(String mst);
}
