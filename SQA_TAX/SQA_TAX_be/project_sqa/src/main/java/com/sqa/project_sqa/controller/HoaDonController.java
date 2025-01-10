package com.sqa.project_sqa.controller;


import com.sqa.project_sqa.entities.HoaDon;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;
import com.sqa.project_sqa.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/hoa-don")
public class HoaDonController {

    @Autowired HoaDonService hoaDonService;
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllProvince(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(hoaDonService.getHoaDonByIdHoaDon(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
        return new ResponseEntity<>(hoaDonService.saveHoaDon(hoaDonDTO), HttpStatus.OK);
    }
}
