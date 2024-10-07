package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.NguoiDongThueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/tax-payer")
public class NguoiDongThueController {

    @Autowired
    private NguoiDongThueService nguoiDongThueService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(nguoiDongThueService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get All User wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByMaSoThue")
    public ResponseEntity<?> getByMaSoThue(@RequestParam("mst") String mst){
        try{
            return new ResponseEntity<>(nguoiDongThueService.getNguoiDongThueByMaSoThue(mst), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something get All User wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
