package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.service.ProvinceService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProvince(){
        return new ResponseEntity<>(provinceService.getAllProvince(), HttpStatus.OK);
    }
}
