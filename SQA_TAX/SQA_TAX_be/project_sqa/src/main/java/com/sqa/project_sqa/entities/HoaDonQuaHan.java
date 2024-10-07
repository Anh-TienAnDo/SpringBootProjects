package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonQuaHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tien_phat")
    private double tienPhat;

    @Column(name = "so_ngay_qua_han")
    private int soNgayQuaHan;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "loai_thue_id")
    private LoaiThue loaiThue;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "chi_tiet_hoa_don_id")
    private ChiTietHoaDon chiTietHoaDon;
}
