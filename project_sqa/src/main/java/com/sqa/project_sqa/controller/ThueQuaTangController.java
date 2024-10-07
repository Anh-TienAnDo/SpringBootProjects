package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueQuaTangService;
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
import java.util.Map;

@Service
@Controller
@RequestMapping("/api/v1/to-khai-thue-qua-tang")
public class ThueQuaTangController {
    @Autowired
    private ThueQuaTangService thueQuaTangService;
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    private LoaiThueService loaiThueService;
    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody ThueQuaTang thueQuaTang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Lấy danh sách lỗi từ BindingResult
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body("lỗi");
        }

        if (thueQuaTang.getLoaiThueId() != null) {
            LoaiThue loaiThue = loaiThueService.getById(thueQuaTang.getLoaiThueId())
                    .orElseThrow(() -> new RuntimeException("Loại thuế không tồn tại!"));
            thueQuaTang.setLoaiThue(loaiThue);
        }

        if (thueQuaTang.getLoaiThueId() != null) {
            LoaiThue loaiThue = loaiThueService.getById(thueQuaTang.getLoaiThueId())
                    .orElseThrow(() -> new RuntimeException("Loại thuế không tồn tại!"));
            thueQuaTang.setLoaiThue(loaiThue);
        }

        String mst = thueQuaTang.getMst();
        if (thueQuaTang.getThuNhapChiuThue() < 0) {
            return ResponseEntity.badRequest().body("Giá trị không hợp lệ");
        }
        if(nguoiDongThueRepository.existsByMst(mst)) {
            String thuNhapChiuThue = String.valueOf(thueQuaTang.getThuNhapChiuThue());
            String tongThuePhaiNop = thueQuaTangService.Tax_present(new BigDecimal(thuNhapChiuThue));
            thueQuaTang.setTongThuePhaiNop(Long.parseLong(tongThuePhaiNop));
            ThueQuaTang a = thueQuaTangService.saveThueQuaTang(thueQuaTang);
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
            return new ResponseEntity<>(thueQuaTangService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get all thue qua tang wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
