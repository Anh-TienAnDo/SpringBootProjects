package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueTrungThuong;
import com.sqa.project_sqa.repositories.ThueQuaTangRepo;
import com.sqa.project_sqa.repositories.ThueTrungThuongRepo;
import com.sqa.project_sqa.service.ThueTrungThuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service

public class ThueTrungThuongServiceImpl implements ThueTrungThuongService {
    @Autowired
    private ThueTrungThuongRepo thueTrungThuongRepo;
    @Override
    public ThueTrungThuong saveThueTrungThuong(ThueTrungThuong thueTrungThuong) {
        return thueTrungThuongRepo.save(thueTrungThuong);
    }

    @Override
    public String Tax_win_prize(BigDecimal assessable_income){
        BigDecimal tax;
        BigDecimal ratio = new BigDecimal("0.1");
        if (assessable_income.compareTo(new BigDecimal("10000000")) > 0) {
            tax = assessable_income.multiply(ratio);
        } else {
            tax = BigDecimal.ZERO;
        }
        return tax.setScale(0, RoundingMode.HALF_UP).toString();
    }

    @Override
    public List<ThueTrungThuong> getAll() {
        return thueTrungThuongRepo.findAll();
    }

    @Override
    public String CapNhatTrangThaiDaDong(ThueTrungThuong thueTrungThuong ) {
        thueTrungThuong.setTrangThaiDaDong(true);
        return "Cap nhật thành công";
    }
}
