package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.District;
import com.sqa.project_sqa.repositories.DistrictRepository;
import com.sqa.project_sqa.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public List<District> getDistrictByIdProvince(String id) {
        return districtRepository.findByProvinceCode(id);
    }
}
