package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class HoaDon {

    @Transient
    private Integer nguoiDongThueId;

    @Transient
    private Integer userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "tong_thue_phai_dong")
    private double tongThuePhaiDong;

    @Column(name = "thoi_gian_nop_thue")
    private Date thoiGianNopThue;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hoaDon")
    private List<ChiTietHoaDon> chiTietHoaDonList;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "nguoi_dong_thue_id")
    private NguoiDongThue nguoiDongThue;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}