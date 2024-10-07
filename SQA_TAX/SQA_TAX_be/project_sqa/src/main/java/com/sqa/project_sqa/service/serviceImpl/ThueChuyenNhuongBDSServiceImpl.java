package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBanQuyenRepo;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBatDongSanRepo;
import com.sqa.project_sqa.service.ThueChuyenNhuongBatDongSanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ThueChuyenNhuongBDSServiceImpl implements ThueChuyenNhuongBatDongSanService {
    @Autowired
    private ThueChuyenNhuongBatDongSanRepo thueChuyenNhuongBatDongSanRepo;
    @Override
    public ThueChuyenNhuongBDS saveThueChuyenNhuongBDS(ThueChuyenNhuongBDS thueChuyenNhuongBDS) {
        return thueChuyenNhuongBatDongSanRepo.save(thueChuyenNhuongBDS);
    }
    //    chuyển nhượng bất động sản
    @Override
    public String Tax_land_transfer(BigDecimal transfer_price){ //transfer_price: giá chuyển nhượng
        BigDecimal tax;
        BigDecimal ratio = new BigDecimal("0.02");
        if (transfer_price.compareTo(BigDecimal.ZERO) > 0) {
            tax = transfer_price.multiply(ratio);
        } else {
            tax = BigDecimal.ZERO;
        }
        return tax.setScale(0, RoundingMode.HALF_UP).toString();
    }

    @Override
    public List<ThueChuyenNhuongBDS> getAll() {
        return thueChuyenNhuongBatDongSanRepo.findAll();
    }
    @Override
    public String CapNhatTrangThaiDaDong(ThueChuyenNhuongBDS thueChuyenNhuongBDS) {
        thueChuyenNhuongBDS.setTrangThaiDaDong(true);
        thueChuyenNhuongBatDongSanRepo.save(thueChuyenNhuongBDS);
        return "Cap nhật thành công";
    }
}
