package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.entities.ThueTienLuongCong;

import java.math.BigDecimal;
import java.util.List;

public interface ThueChuyenNhuongBanQuyenService {
    ThueChuyenNhuongBanQuyen saveThueChuyenNhuongBanQuyem(ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen);
    String Tax_license(BigDecimal assessable_income);

    List<ThueChuyenNhuongBanQuyen> getAll();

    String CapNhatTrangThaiDaDong(ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen);
}
