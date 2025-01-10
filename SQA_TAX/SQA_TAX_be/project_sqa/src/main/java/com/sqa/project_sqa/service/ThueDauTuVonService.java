package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.entities.ThueDauTuVon;

import java.math.BigDecimal;
import java.util.List;

public interface ThueDauTuVonService {
    ThueDauTuVon saveThueDauTuVon(ThueDauTuVon thueDauTuVon);
    String Tax_capital_investments(BigDecimal assessable_income);

    List<ThueDauTuVon> getAll();

    String CapNhatTrangThaiDaDong(ThueDauTuVon thueDauTuVon);
}
