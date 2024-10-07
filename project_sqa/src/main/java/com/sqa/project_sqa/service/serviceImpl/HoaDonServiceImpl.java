package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.*;
import com.sqa.project_sqa.mapper.HoaDonMapper;
import com.sqa.project_sqa.payload.dto.DanhSachThueMuonDongDTO;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;
import com.sqa.project_sqa.repositories.ChiTietHoaDonRepository;
import com.sqa.project_sqa.repositories.HoaDonRepository;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.repositories.UserRepository;
import com.sqa.project_sqa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ThueChuyenNhuongBanQuyenService thueChuyenNhuongBanQuyenService;

    @Autowired
    private ThueChuyenNhuongBatDongSanService thueChuyenNhuongBatDongSanService;

    @Autowired
    private ThueDauTuVonService thueDauTuVonService;

    @Autowired
    private ThueNhuongQuyenThuongMaiService thueNhuongQuyenThuongMaiService;

    @Autowired
    private ThueQuaTangService thueQuaTangService;

    @Autowired
    private ThueTienLuongCongService thueTienLuongCongService;

    @Autowired
    private ThueTrungThuongService thueTrungThuongService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    LoaiThueService loaiThueService;

    @Override
    public List<HoaDon> getHoaDonByIdHoaDon(int idHoaDon) {
        return null;
    }

    @Override
    public ResponseEntity<?> getHoaDonByIdTaxPayer(int idNguoiDongThue) {
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<?> saveHoaDon(HoaDonDTO hoaDonDTO) {
        try {
            HoaDon hoaDon = HoaDonMapper.toEntity(hoaDonDTO);

            NguoiDongThue nguoiDongThue = nguoiDongThueRepository.findById(hoaDonDTO.getNguoiDongThueId());
//
            User user = userRepository.findById(hoaDonDTO.getUserId());

            hoaDon.setUser(user);
            hoaDon.setNguoiDongThue(nguoiDongThue);

            if (hoaDon.getChiTietHoaDonList() == null) {
                hoaDon.setChiTietHoaDonList(new ArrayList<>());
            }

            List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList = thueChuyenNhuongBanQuyenService.getAll();
            List<ThueChuyenNhuongBDS> thueChuyenNhuongBDSList = thueChuyenNhuongBatDongSanService.getAll();
            List<ThueDauTuVon> thueDauTuVonList = thueDauTuVonService.getAll();
            List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList = thueNhuongQuyenThuongMaiService.getAll();
            List<ThueQuaTang> thueQuaTangList = thueQuaTangService.getAll();
            List<ThueTienLuongCong> thueTienLuongCongList = thueTienLuongCongService.getAll();
            List<ThueTrungThuong> thueTrungThuongList = thueTrungThuongService.getAll();

            List<DanhSachThueMuonDongDTO> danhSachThueMuonDongDTOList = hoaDonDTO.getDanhSachThueMuonDong();

            for(int i=0;i<danhSachThueMuonDongDTOList.size();i++){
                int loaiThueId = danhSachThueMuonDongDTOList.get(i).getLoaiThueId();
                for(ThueChuyenNhuongBanQuyen tmp: thueChuyenNhuongBanQuyenList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueChuyenNhuongBanQuyenService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueChuyenNhuongBDS tmp: thueChuyenNhuongBDSList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueChuyenNhuongBatDongSanService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueDauTuVon tmp: thueDauTuVonList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueDauTuVonService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueNhuongQuyenThuongMai tmp: thueNhuongQuyenThuongMaiList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueNhuongQuyenThuongMaiService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueNhuongQuyenThuongMai tmp: thueNhuongQuyenThuongMaiList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueNhuongQuyenThuongMaiService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueQuaTang tmp: thueQuaTangList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueQuaTangService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }

                for(ThueTienLuongCong tmp: thueTienLuongCongList){
                    if(tmp.getLoaiThue().getId()==loaiThueId){
                        thueTienLuongCongService.CapNhatTrangThaiDaDong(tmp);
                    }
                    else break;
                }
            }


            for(DanhSachThueMuonDongDTO tmp : danhSachThueMuonDongDTOList) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                Optional<LoaiThue> loaiThue = loaiThueService.getById(tmp.getLoaiThueId());
                chiTietHoaDon.setHoaDon(hoaDon);
                chiTietHoaDon.setThueId(tmp.getIdThue());
                chiTietHoaDonRepository.save(chiTietHoaDon);
            }

            HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
            return ResponseEntity.ok(savedHoaDon);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving invoice: +" + e.getMessage());
        }
    }
}
