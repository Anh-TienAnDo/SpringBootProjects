//package com.sqa.project_sqa.controller;
//
//import com.sqa.project_sqa.entities.ChiTietThue;
//import com.sqa.project_sqa.service.ChiTietThueService;
//import com.sqa.project_sqa.service.NguoiDongThueService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api/v1/chi-tiet-thue")
//public class ChiTietThueController {
//
//    @Autowired
//    private ChiTietThueService chiTietThueService;
//    @GetMapping("/getChiTietThueByNguoiDongThueId/{id}")
//    public ResponseEntity<?> getChiTietThueByNguoiDongThueId(@PathVariable int id){
//        try{
//            return new ResponseEntity<>(chiTietThueService.getChiTietThueByNguoiDongThueId(id), HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Something get All User wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAll(){
//        try{
//            return new ResponseEntity<>(chiTietThueService.getAll(), HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Something get All Chi Tiet Thue Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
