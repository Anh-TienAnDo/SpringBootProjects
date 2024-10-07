package com.sqa.project_sqa.entities;

import javax.persistence.Embeddable;

@Embeddable
public class KhauTru {
    private double tongSoTienKhauTru;
    private double baoHiemXaHoi;
    private double khuyenHoc;
    private double khauTruNguoiPhuThuoc;;
    private double khauTruBanTanCaNhan;

    public KhauTru() {
    }

    public KhauTru(double tongSoTienKhauTru, double baoHiemXaHoi, double khuyenHoc, double khauTruNguoiPhuThuoc, double khauTruBanTanCaNhan) {
        this.tongSoTienKhauTru = tongSoTienKhauTru;
        this.baoHiemXaHoi = baoHiemXaHoi;
        this.khuyenHoc = khuyenHoc;
        this.khauTruNguoiPhuThuoc = khauTruNguoiPhuThuoc;
        this.khauTruBanTanCaNhan = khauTruBanTanCaNhan;
    }

    public double getTongSoTienKhauTru() {
        return tongSoTienKhauTru;
    }

    public void setTongSoTienKhauTru(double tongSoTienKhauTru) {
        this.tongSoTienKhauTru = tongSoTienKhauTru;
    }

    public double getBaoHiemXaHoi() {
        return baoHiemXaHoi;
    }

    public void setBaoHiemXaHoi(double baoHiemXaHoi) {
        this.baoHiemXaHoi = baoHiemXaHoi;
    }

    public double getKhuyenHoc() {
        return khuyenHoc;
    }

    public void setKhuyenHoc(double khuyenHoc) {
        this.khuyenHoc = khuyenHoc;
    }

    public double getKhauTruNguoiPhuThuoc() {
        return khauTruNguoiPhuThuoc;
    }

    public void setKhauTruNguoiPhuThuoc(double khauTruNguoiPhuThuoc) {
        this.khauTruNguoiPhuThuoc = khauTruNguoiPhuThuoc;
    }

    public double getKhauTruBanTanCaNhan() {
        return khauTruBanTanCaNhan;
    }

    public void setKhauTruBanTanCaNhan(double khauTruBanTanCaNhan) {
        this.khauTruBanTanCaNhan = khauTruBanTanCaNhan;
    }
}