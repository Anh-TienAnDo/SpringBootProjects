package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueTienLuongCong;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueTienLuongCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
@Controller
@RequestMapping("/api/v1/to-khai-thue-tien-luong-cong")

public class ThueTienLuongCongController {

    @Autowired
    private ThueTienLuongCongService thueTienLuongCongService;
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    private LoaiThueService loaiThueService;

    // xử lí lưu thông tin tờ khai lương công
    @PostMapping("/submit")
    public ResponseEntity<?> submitTKThueTienLuongCong( @RequestBody ThueTienLuongCong thueTienLuongCong, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Lấy danh sách lỗi từ BindingResult
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body("lỗi");
        }

        if (thueTienLuongCong.getLoaiThueId() != null) {
            LoaiThue loaiThue = loaiThueService.getById(thueTienLuongCong.getLoaiThueId())
                    .orElseThrow(() -> new RuntimeException("Loại thuế không tồn tại!"));
            thueTienLuongCong.setLoaiThue(loaiThue);
        }

        String mst = thueTienLuongCong.getMst();
        if(nguoiDongThueRepository.existsByMst(mst)) {
            if (thueTienLuongCong.getThuNhapChiuThue() < 0 || thueTienLuongCong.getThuNhapDuocMienGiam() < 0 || thueTienLuongCong.getKhauTruChoBanThan() < 0 || thueTienLuongCong.getKhauTruNguoiPhuThuoc() < 0 || thueTienLuongCong.getKhauTruChoTuThien() < 0 || thueTienLuongCong.getKhauTruChoBaoHiem() < 0) {
                return ResponseEntity.badRequest().body("Giá trị không hợp lệ");
            }
            String thuNhapChiuThue = String.valueOf(thueTienLuongCong.getThuNhapChiuThue());
            Long khauTruChoBanThan = thueTienLuongCong.getKhauTruChoBanThan();
            Long khauTruNguoiPhuThuoc = thueTienLuongCong.getKhauTruNguoiPhuThuoc();
            Long khauTruChoTuThien = thueTienLuongCong.getKhauTruChoTuThien();
            Long khauTruChoBaoHiem = thueTienLuongCong.getKhauTruChoBaoHiem();
            String tongKhauTru = String.valueOf(khauTruChoBanThan+khauTruNguoiPhuThuoc+khauTruChoTuThien+khauTruChoBaoHiem);
            String tongThuePhaiNop;
            if(thueTienLuongCong.isCuTru()){
                tongThuePhaiNop = thueTienLuongCongService.Tax_type1(new BigDecimal(thuNhapChiuThue), new BigDecimal(tongKhauTru));
            }else{
                tongThuePhaiNop = thueTienLuongCongService.Tax_type3(new BigDecimal(thuNhapChiuThue));
            }
            thueTienLuongCong.setTongThuePhaiNop(Long.parseLong(tongThuePhaiNop));
            System.out.println(thueTienLuongCong.getTongThuePhaiNop());
            ThueTienLuongCong a =thueTienLuongCongService.saveThueTienLuongCong(thueTienLuongCong);
            return ResponseEntity.ok(a);
        }
        else {
            Map<String, String> obj = new HashMap<>();
            obj.put("message", "MST không tồn tại");
            return ResponseEntity.badRequest().body(obj);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(thueTienLuongCongService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get all thue tien luong cong wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}