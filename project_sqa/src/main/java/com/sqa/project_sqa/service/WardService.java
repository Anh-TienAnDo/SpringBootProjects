package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.Ward;

import java.util.List;

public interface WardService {
    List<Ward> getListWardByDistrictId(String id);
}
