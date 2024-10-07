package com.sqa.project_sqa.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDongThueDTO {
    private String hoVaTen;

    private String gioiTinh;

    private Date ngaySinh;

    private String sdt;

    private String email;

    private String soGiayTo;

    private Date ngayCap;

    private String noiCap;

    private String dchk_soNhaDuongXom;

    private String dchk_tinhThanhPho;

    private String dchk_QuanHuyen;

    private String dchk_xaPhuong;

    private String dcct_soNhaDuongXom;

    private String dcct_tinhThanhPho;

    private String dcct_QuanHuyen;

    private String dcct_xaPhuong;

    private String taxAgency;
}
