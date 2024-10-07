package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoaiThue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueChuyenNhuongBDS> thueChuyenNhuongBDSList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueDauTuVon> thueDauTuVonList;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueQuaTang> thueQuaTangList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueTienLuongCong> thueTienLuongCongList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiThue", cascade = CascadeType.ALL,orphanRemoval=true)
    @JsonManagedReference
    private List<ThueTrungThuong> thueTrungThuongList;

    @OneToMany(mappedBy = "loaiThue",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HoaDonQuaHan> hoaDonQuaHanList;

    @OneToMany(mappedBy = "loaiThue",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ChiTietHoaDon> chiTietHoaDonList;

}
