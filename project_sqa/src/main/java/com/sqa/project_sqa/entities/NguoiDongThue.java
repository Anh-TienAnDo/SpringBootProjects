package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDongThue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mst;

    private String hoVaTen;

    private String gioiTinh;

    private Date ngaySinh;

    private String sdt;

    private String email;

    private String CMND;

    private Date CMND_ngayCap;

    private String CMND_noiCap;

    private String CCCD;

    private Date CCCD_ngayCap;

    private String CCCD_noiCap;

    private String dchk_soNhaDuongXom;

    private String dchk_tinhThanhPho;

    private String dchk_QuanHuyen;

    private String dchk_xaPhuong;

    private String dcct_soNhaDuongXom;

    private String dcct_tinhThanhPho;

    private String dcct_QuanHuyen;

    private String dcct_xaPhuong;

    private String taxAgency;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<HoaDon> hoaDonList;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueChuyenNhuongBDS> thueChuyenNhuongBDSList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueDauTuVon> thueDauTuVonList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueQuaTang> thueQuaTangList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueTienLuongCong> thueTienLuongCongList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "nguoiDongThue")
    @JsonIgnore
    private List<ThueTrungThuong> thueTrungThuongList;
}

