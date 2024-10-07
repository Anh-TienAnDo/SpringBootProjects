package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.repositories.ThueQuaTangRepo;
import com.sqa.project_sqa.repositories.ThueTienLuongCongRepository;
import com.sqa.project_sqa.service.ThueQuaTangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service

public class ThueQuaTangServiceImpl implements ThueQuaTangService {
    @Autowired
    private ThueQuaTangRepo thueQuaTangRepo;
    @Override
    public ThueQuaTang saveThueQuaTang(ThueQuaTang thueQuaTang) {
        return thueQuaTangRepo.save(thueQuaTang);
    }

    @Override
    public String Tax_present(BigDecimal assessable_income){
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
    public List<ThueQuaTang> getAll() {
        return thueQuaTangRepo.findAll();
    }

    @Override
    public String CapNhatTrangThaiDaDong(ThueQuaTang thueQuaTang) {
        thueQuaTang.setTrangThaiDaDong(true);
        return "Cap nhật thành công";
    }

}
