package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.serviceImpl.LoaiThueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/loai-thue")
public class LoaiThueController {

    @Autowired
    private LoaiThueService loaiThueService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllLoaiThue(){
        try{
            return new ResponseEntity<>(loaiThueService.getAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something getAll Loai Thue wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
