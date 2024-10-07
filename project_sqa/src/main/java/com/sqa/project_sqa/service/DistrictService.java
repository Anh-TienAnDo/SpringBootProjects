package com.sqa.project_sqa.service;


import com.sqa.project_sqa.entities.District;

import java.util.List;

public interface DistrictService {
    List<District> getDistrictByIdProvince(String id);
}
