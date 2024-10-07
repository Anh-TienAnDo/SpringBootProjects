package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueNhuongQuyenThuongMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Controller
@RequestMapping("/api/v1/to-khai-thue-nhuong-quyen-thuong-mai")
public class ThueNhuongQuyenThuongMaiController {
    @Autowired
    private ThueNhuongQuyenThuongMaiService thueNhuongQuyenThuongMaiService;
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    private LoaiThueService loaiThueService;
    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody ThueNhuongQuyenThuongMai thueNhuongQuyenThuongMai, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Lấy danh sách lỗi từ BindingResult
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body("lỗi");
        }

        if (thueNhuongQuyenThuongMai.getLoaiThueId() != null) {
            LoaiThue loaiThue = loaiThueService.getById(thueNhuongQuyenThuongMai.getLoaiThueId())
                    .orElseThrow(() -> new RuntimeException("Loại thuế không tồn tại!"));
            thueNhuongQuyenThuongMai.setLoaiThue(loaiThue);
        }

        String mst = thueNhuongQuyenThuongMai.getMst();
        if (thueNhuongQuyenThuongMai.getThuNhapChiuThue() < 0) {
            return ResponseEntity.badRequest().body("Giá trị không hợp lệ");
        }
        if(nguoiDongThueRepository.existsByMst(mst)) {
            String thuNhapChiuThue = String.valueOf(thueNhuongQuyenThuongMai.getThuNhapChiuThue());
            String tongThuePhaiNop = thueNhuongQuyenThuongMaiService.Tax_ecommerce(new BigDecimal(thuNhapChiuThue));
            thueNhuongQuyenThuongMai.setTongThuePhaiNop(Long.parseLong(tongThuePhaiNop));
            System.out.println(thueNhuongQuyenThuongMai.getTongThuePhaiNop());
            ThueNhuongQuyenThuongMai a = thueNhuongQuyenThuongMaiService.saveThueNhuongQuyenThuongMaiService(thueNhuongQuyenThuongMai);
            return ResponseEntity.ok(a);
        } else {
            Map<String, String> obj = new HashMap<>();
            obj.put("message", "MST không tồn tại");
            return ResponseEntity.badRequest().body(obj);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList = thueNhuongQuyenThuongMaiService.getAll();
            thueNhuongQuyenThuongMaiList.forEach(item -> {
                item.setLoaiThueId(item.getLoaiThue().getId());
            });
            return new ResponseEntity<>(thueNhuongQuyenThuongMaiList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get all thue nhuong quyen thuong mai wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
