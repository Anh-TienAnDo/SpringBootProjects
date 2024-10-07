//package com.sqa.project_sqa.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Setter
//@Builder
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class ChiTietThue implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "trang_thai")
//    private boolean trangThai;
//
//    @Column(name = "ngay_bat_Dau_dong")
//    private Date ngayBatDauDong;
//
//    @Column(name = "ngay_qua_han_dong")
//    private Date ngayQuaHanDong;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
//    @JoinColumn(name = "nguoi_dong_thue_id", referencedColumnName = "id")
//    private NguoiDongThue nguoiDongThue;
//
//
//    private Integer  thue_chuyen_nhuong_bds_id;
//    private Integer  thue_dau_tu_von_id;
//    private Integer  thue_chuyen_nhuong_ban_quyen_id;
//
//    private Integer  thue_nhuong_quyen_thuong_mai_id;
//    private Integer  thue_qua_tang_id;
//
//    private Integer  thue_tien_luong_cong_id;
//
//    private Integer  thue_trung_thuong_id;
//
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "chiTietThue", orphanRemoval = true)
//    @JsonIgnore
//    private HoaDonQuaHan hoaDonQuaHan;
//
//    @OneToOne(mappedBy = "chiTietThue", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private ChiTietHoaDon chiTietHoaDon;
//}
