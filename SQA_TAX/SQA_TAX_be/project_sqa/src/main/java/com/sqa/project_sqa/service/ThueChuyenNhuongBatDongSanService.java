package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;

import java.math.BigDecimal;
import java.util.List;

public interface ThueChuyenNhuongBatDongSanService {
    ThueChuyenNhuongBDS saveThueChuyenNhuongBDS(ThueChuyenNhuongBDS thueChuyenNhuongBDS);
    String Tax_land_transfer(BigDecimal transfer_price);

    List<ThueChuyenNhuongBDS> getAll();

    String CapNhatTrangThaiDaDong(ThueChuyenNhuongBDS thueChuyenNhuongBDS);
}
