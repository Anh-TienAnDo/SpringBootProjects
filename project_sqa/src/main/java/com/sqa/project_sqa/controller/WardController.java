package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ward")
public class WardController {
    @Autowired
    private WardService wardService;
    @GetMapping("/getByDistrictId")
    public ResponseEntity<?> getListWardByDistrictId(@RequestParam("districtId") String id){
        return new ResponseEntity<>(wardService.getListWardByDistrictId(id), HttpStatus.OK);
    }
}
