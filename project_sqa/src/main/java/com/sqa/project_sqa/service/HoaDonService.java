package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.District;
import com.sqa.project_sqa.entities.HoaDon;
import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HoaDonService {

    List<HoaDon> getHoaDonByIdHoaDon(int idHoaDon);
    ResponseEntity<?> getHoaDonByIdTaxPayer(int idNguoiDongThue);

    ResponseEntity<?> saveHoaDon(HoaDonDTO hoaDonDTO);
}
