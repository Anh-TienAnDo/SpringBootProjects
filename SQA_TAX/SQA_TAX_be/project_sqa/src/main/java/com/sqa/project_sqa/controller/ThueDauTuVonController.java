package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueDauTuVonService;
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
@RequestMapping("/api/v1/to-khai-thue-dau-tu-von")
public class ThueDauTuVonController {
    @Autowired
    private ThueDauTuVonService thueDauTuVonService;
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    private LoaiThueService loaiThueService;
    @PostMapping("/submit")
    public ResponseEntity<?> submit( @RequestBody ThueDauTuVon thueDauTuVon, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Lấy danh sách lỗi từ BindingResult
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body("lỗi");
        }

        if (thueDauTuVon.getLoaiThueId() != null) {
            LoaiThue loaiThue = loaiThueService.getById(thueDauTuVon.getLoaiThueId())
                    .orElseThrow(() -> new RuntimeException("Loại thuế không tồn tại!"));
            thueDauTuVon.setLoaiThue(loaiThue);
        }

        String mst = thueDauTuVon.getMst();
        if (thueDauTuVon.getThuNhapChiuThue() < 0) {
            return ResponseEntity.badRequest().body("Giá trị không hợp lệ");
        }
        if(nguoiDongThueRepository.existsByMst(mst)) {
            String thuNhapChiuThue = String.valueOf(thueDauTuVon.getThuNhapChiuThue());
            String tongThuePhaiNop = thueDauTuVonService.Tax_capital_investments(new BigDecimal(thuNhapChiuThue));
            thueDauTuVon.setTongThuePhaiNop(Long.parseLong(tongThuePhaiNop));
            System.out.println(thueDauTuVon.getTongThuePhaiNop());
            ThueDauTuVon a = thueDauTuVonService.saveThueDauTuVon(thueDauTuVon);
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
            List<ThueDauTuVon> thueDauTuVonList = thueDauTuVonService.getAll();
            thueDauTuVonList.forEach(item -> {
                item.setLoaiThueId(item.getLoaiThue().getId());
            });
            return new ResponseEntity<>(thueDauTuVonList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get all thue dau tu von wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
