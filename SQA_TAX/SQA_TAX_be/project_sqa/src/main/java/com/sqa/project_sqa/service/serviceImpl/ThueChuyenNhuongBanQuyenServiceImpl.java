package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBanQuyenRepo;
import com.sqa.project_sqa.repositories.ThueTienLuongCongRepository;
import com.sqa.project_sqa.service.ThueChuyenNhuongBanQuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ThueChuyenNhuongBanQuyenServiceImpl implements ThueChuyenNhuongBanQuyenService {
    @Autowired
    private ThueChuyenNhuongBanQuyenRepo thueChuyenNhuongBanQuyenRepo;
    @Override
    public ThueChuyenNhuongBanQuyen saveThueChuyenNhuongBanQuyem(ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen) {
        return thueChuyenNhuongBanQuyenRepo.save(thueChuyenNhuongBanQuyen);
    }

    @Override
    public String Tax_license(BigDecimal assessable_income){
        BigDecimal tax;
        BigDecimal ratio = new BigDecimal("0.05");
        if (assessable_income.compareTo(new BigDecimal("10000000")) > 0) {
            tax = assessable_income.multiply(ratio);
        } else {
            tax = BigDecimal.ZERO;
        }
        return tax.setScale(0, RoundingMode.HALF_UP).toString();
    }

    @Override
    public List<ThueChuyenNhuongBanQuyen> getAll() {
        return thueChuyenNhuongBanQuyenRepo.findAll();
    }

    @Override
    public String CapNhatTrangThaiDaDong(ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen) {
        thueChuyenNhuongBanQuyen.setTrangThaiDaDong(true);
        thueChuyenNhuongBanQuyenRepo.save(thueChuyenNhuongBanQuyen);
        return "Cap nhật thành công";
    }
}
