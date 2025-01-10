package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @GetMapping("/getByProvinceId")
    public ResponseEntity<?> getListDistrictByProvinceId(@RequestParam("provinceId") String id){
        return new ResponseEntity<>(districtService.getDistrictByIdProvince(id), HttpStatus.OK);
    }
}
