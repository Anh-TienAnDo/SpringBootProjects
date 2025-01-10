//package com.sqa.project_sqa.service.serviceImpl;
//
//import com.sqa.project_sqa.entities.ChiTietThue;
//import com.sqa.project_sqa.entities.NguoiDongThue;
//import com.sqa.project_sqa.repositories.ChiTietThueRepository;
//import com.sqa.project_sqa.service.ChiTietThueService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ChiTietThueServiceImpl implements ChiTietThueService {
//
//    @Autowired
//    private ChiTietThueRepository chiTietThueRepository;
//
//    public List<ChiTietThue> getChiTietThueByNguoiDongThueId(int id) {
//        return chiTietThueRepository.findByNguoiDongThue_Id(id);
//    }
//
//    public List<ChiTietThue> getAll() {
//        return chiTietThueRepository.findAll();
//    }
//}
