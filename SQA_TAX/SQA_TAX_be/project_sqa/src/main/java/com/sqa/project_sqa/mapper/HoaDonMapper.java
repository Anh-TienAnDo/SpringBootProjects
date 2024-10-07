package com.sqa.project_sqa.mapper;

import com.sqa.project_sqa.entities.HoaDon;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;

public class HoaDonMapper {
    public static HoaDon toEntity(HoaDonDTO dto) {
        HoaDon entity = new HoaDon();
        entity.setTongThuePhaiDong(dto.getTongThuePhaiDong());
        entity.setThoiGianNopThue(dto.getThoiGianNopThue());
        entity.setUserId(dto.getUserId());
        entity.setNguoiDongThueId(dto.getNguoiDongThueId());
        return entity;
    }
}
