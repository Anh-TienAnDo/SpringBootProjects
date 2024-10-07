package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Province;
import com.sqa.project_sqa.repositories.ProvinceRepository;
import com.sqa.project_sqa.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }
}
