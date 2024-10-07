package com.sqa.project_sqa.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {

    List<DanhSachThueMuonDongDTO> danhSachThueMuonDong;
    private int loaiThue;

    private int idThue;
    private double tongThuePhaiDong;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date thoiGianNopThue;

    private int userId;

    private int nguoiDongThueId;
}
