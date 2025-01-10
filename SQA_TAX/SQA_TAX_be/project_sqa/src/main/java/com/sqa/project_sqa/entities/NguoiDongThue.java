package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @Getter
    @Setter
    private String mst;



    private String hoVaTen;

    private String gioiTinh;

    private Date ngaySinh;

    private String sdt;

    private String email;



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

    //    private String CMND;
//
//    private Date CMND_ngayCap;
//
//    private String CMND_noiCap;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NguoiDongThue that = (NguoiDongThue) obj;
        return Objects.equals(mst, that.getMst()) &&
                Objects.equals(hoVaTen, that.getHoVaTen()) &&
                Objects.equals(gioiTinh, that.getGioiTinh()) &&
                Objects.equals(ngaySinh, that.getNgaySinh()) &&
                Objects.equals(sdt, that.getSdt()) &&
                Objects.equals(email, that.getEmail()) &&
                Objects.equals(CCCD, that.getCCCD()) &&
                Objects.equals(CCCD_ngayCap, that.getCCCD_ngayCap()) &&
                Objects.equals(CCCD_noiCap, that.getCCCD_noiCap()) &&
                Objects.equals(dchk_soNhaDuongXom, that.getDchk_soNhaDuongXom()) &&
                Objects.equals(dchk_tinhThanhPho, that.getDchk_tinhThanhPho()) &&
                Objects.equals(dchk_QuanHuyen, that.getDchk_QuanHuyen()) &&
                Objects.equals(dchk_xaPhuong, that.getDchk_xaPhuong()) &&
                Objects.equals(dcct_soNhaDuongXom, that.getDcct_soNhaDuongXom()) &&
                Objects.equals(dcct_tinhThanhPho, that.getDcct_tinhThanhPho()) &&
                Objects.equals(dcct_QuanHuyen, that.getDcct_QuanHuyen()) &&
                Objects.equals(dcct_xaPhuong, that.getDcct_xaPhuong()) &&
                Objects.equals(taxAgency, that.getTaxAgency());
    }
}

