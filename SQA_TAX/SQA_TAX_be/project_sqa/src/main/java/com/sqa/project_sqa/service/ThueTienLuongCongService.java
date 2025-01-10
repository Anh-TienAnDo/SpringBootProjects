package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.entities.ThueTienLuongCong;

import java.math.BigDecimal;
import java.util.List;

public interface ThueTienLuongCongService {
    List<ThueTienLuongCong> getAll();
    ThueTienLuongCong saveThueTienLuongCong(ThueTienLuongCong thueTienLuongCong);
    ThueTienLuongCong getThueTienLuongCongById(int id);
    void deleteThueTienLuongCong(int id);
    // thuế thu nhập cá nhân đối với cá nhân cư trú, ký hợp đồng lao động > 03 tháng
    String Tax_type1(BigDecimal taxable_income, BigDecimal deductible);
    // Thuế thu nhập đối với cá nhân không ký hợp đồng lao động hoặc ký hợp đồng lao động dưới 03 tháng
    String Tax_type2(BigDecimal taxable_income);
    // thuế thu nhập đối với cá nhân không cư trú
    String Tax_type3(BigDecimal taxable_income);
    // Khoản giảm trừ
    String Deductible(long ban_than, long nguoi_phu_thuoc, long tu_thien, long bao_hiem);

    String CapNhatTrangThaiDaDong(ThueTienLuongCong thueTienLuongCong);
	
}
