package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Ward;
import com.sqa.project_sqa.repositories.WardRepository;
import com.sqa.project_sqa.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;
    @Override
    public List<Ward> getListWardByDistrictId(String id) {
        return wardRepository.findByDistrictCode(id);
    }
}
