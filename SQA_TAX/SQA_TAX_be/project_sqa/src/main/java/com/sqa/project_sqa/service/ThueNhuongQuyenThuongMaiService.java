package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;

import java.math.BigDecimal;
import java.util.List;

public interface ThueNhuongQuyenThuongMaiService {
    ThueNhuongQuyenThuongMai saveThueNhuongQuyenThuongMaiService(ThueNhuongQuyenThuongMai thueNhuongQuyenThuongMai);
    String Tax_ecommerce(BigDecimal assessable_income);

    List<ThueNhuongQuyenThuongMai> getAll();

    String CapNhatTrangThaiDaDong(ThueNhuongQuyenThuongMai thueNhuongQuyenThuongMai);
}
