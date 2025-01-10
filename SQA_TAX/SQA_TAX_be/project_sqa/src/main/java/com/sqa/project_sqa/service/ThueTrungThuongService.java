package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.entities.ThueTienLuongCong;
import com.sqa.project_sqa.entities.ThueTrungThuong;
import java.math.BigDecimal;
import java.util.List;

public interface ThueTrungThuongService {
    ThueTrungThuong saveThueTrungThuong(ThueTrungThuong thueTrungThuong);
    String Tax_win_prize(BigDecimal assessable_income);

    List<ThueTrungThuong> getAll();

    String CapNhatTrangThaiDaDong(ThueTrungThuong thueTrungThuong);

}
