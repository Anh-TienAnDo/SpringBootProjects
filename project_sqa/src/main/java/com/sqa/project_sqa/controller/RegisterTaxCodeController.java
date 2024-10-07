package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.service.NguoiDongThueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RegisterTaxCodeController {
    @Autowired
    private NguoiDongThueService nguoiDongThueServicel;
    @PostMapping("/dangKy")
    public ResponseEntity<?> registerTaxCode(@RequestBody NguoiDongThueDTO nguoiDongThueDTO){
        return nguoiDongThueServicel.registerTaxCode(nguoiDongThueDTO);
    }
}
