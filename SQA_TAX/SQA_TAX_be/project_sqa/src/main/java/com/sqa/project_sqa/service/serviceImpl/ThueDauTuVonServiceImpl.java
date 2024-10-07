package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBatDongSanRepo;
import com.sqa.project_sqa.repositories.ThueDauTuVonRepo;
import com.sqa.project_sqa.service.ThueDauTuVonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service

public class ThueDauTuVonServiceImpl implements ThueDauTuVonService {
    @Autowired
    private ThueDauTuVonRepo thueDauTuVonRepo;
    @Override
    public ThueDauTuVon saveThueDauTuVon(ThueDauTuVon thueDauTuVon) {
        return thueDauTuVonRepo.save(thueDauTuVon);
    }

    @Override
    public String Tax_capital_investments(BigDecimal assessable_income){
        BigDecimal tax;
        BigDecimal ratio = new BigDecimal("0.05");
        if (assessable_income.compareTo(BigDecimal.ZERO) > 0) {
            tax = assessable_income.multiply(ratio);
        } else {
            tax = BigDecimal.ZERO;
        }
        return tax.setScale(0, RoundingMode.HALF_UP).toString();
    }

    @Override
    public List<ThueDauTuVon> getAll() {
        return thueDauTuVonRepo.findAll();
    }

    @Override
    public String CapNhatTrangThaiDaDong(ThueDauTuVon thueDauTuVon) {
        thueDauTuVon.setTrangThaiDaDong(true);
        thueDauTuVonRepo.save(thueDauTuVon);
        return "Cap nhật thành công";
    }

}
