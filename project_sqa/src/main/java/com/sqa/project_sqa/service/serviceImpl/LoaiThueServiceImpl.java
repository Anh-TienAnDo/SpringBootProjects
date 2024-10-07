package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.repositories.LoaiThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiThueServiceImpl implements LoaiThueService {

    @Autowired
    LoaiThueRepository loaiThueRepository;

    @Override
    public List<LoaiThue> getAll() {
        return loaiThueRepository.findAll();
    }

    @Override
    public Optional<LoaiThue> getById(int id) {
        return loaiThueRepository.findById(id);
    }


}
