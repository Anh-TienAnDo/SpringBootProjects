package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.*;
import com.sqa.project_sqa.mapper.HoaDonMapper;
import com.sqa.project_sqa.payload.dto.DanhSachThueMuonDongDTO;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.*;
import com.sqa.project_sqa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    ThueChuyenNhuongBatDongSanRepo thueChuyenNhuongBatDongSanRepo;
    @Autowired
    ThueTienLuongCongRepository thueTienLuongCongRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ThueChuyenNhuongBanQuyenServiceImpl thueChuyenNhuongBanQuyenServiceImpl;

    @Autowired
    private ThueChuyenNhuongBDSServiceImpl thueChuyenNhuongBDSServiceImpl;

    @Autowired
    private ThueDauTuVonServiceImpl thueDauTuVonServiceImpl;

    @Autowired
    private ThueNhuongQuyenThuongMaiServiceIml thueNhuongQuyenThuongMaiServiceImpl;

    @Autowired
    private ThueQuaTangServiceImpl thueQuaTangServiceImpl;

    @Autowired
    private ThueTienLuongCongServiceImpl thueTienLuongCongServiceImpl;

    @Autowired
    private ThueTrungThuongServiceImpl thueTrungThuongServiceImpl;

    @Autowired
    LoaiThueRepository loaiThueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    NguoiDongThueRepository nguoiDongThueRepository;

    @Autowired
    LoaiThueServiceImpl loaiThueServiceImpl;

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
            int userId = hoaDonDTO.getUserId();
            User user = userRepository.findById(userId);

            hoaDon.setUser(user);
            hoaDon.setNguoiDongThue(nguoiDongThue);

            if (hoaDon.getChiTietHoaDonList() == null) {
                hoaDon.setChiTietHoaDonList(new ArrayList<>());
            }

            List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList = thueChuyenNhuongBanQuyenServiceImpl.getAll();
            List<ThueChuyenNhuongBDS> thueChuyenNhuongBDSList = thueChuyenNhuongBDSServiceImpl.getAll();
            List<ThueDauTuVon> thueDauTuVonList = thueDauTuVonServiceImpl.getAll();
            List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList = thueNhuongQuyenThuongMaiServiceImpl.getAll();
            List<ThueQuaTang> thueQuaTangList = thueQuaTangServiceImpl.getAll();
            List<ThueTienLuongCong> thueTienLuongCongList = thueTienLuongCongServiceImpl.getAll();
            List<ThueTrungThuong> thueTrungThuongList = thueTrungThuongServiceImpl.getAll();

            List<DanhSachThueMuonDongDTO> danhSachThueMuonDongDTOList = hoaDonDTO.getDanhSachThueMuonDong();

            for (int i = 0; i < danhSachThueMuonDongDTOList.size(); i++) {
                int loaiThueId = danhSachThueMuonDongDTOList.get(i).getLoaiThueId();
                int idThue = danhSachThueMuonDongDTOList.get(i).getIdThue();
                for (ThueChuyenNhuongBanQuyen tmp : thueChuyenNhuongBanQuyenList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueChuyenNhuongBanQuyenServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueChuyenNhuongBDS tmp : thueChuyenNhuongBDSList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueChuyenNhuongBDSServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueDauTuVon tmp : thueDauTuVonList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueDauTuVonServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueNhuongQuyenThuongMai tmp : thueNhuongQuyenThuongMaiList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueNhuongQuyenThuongMaiServiceImpl.CapNhatTrangThaiDaDong(tmp);

                        }
                    } else break;
                }

                for (ThueNhuongQuyenThuongMai tmp : thueNhuongQuyenThuongMaiList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueNhuongQuyenThuongMaiServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueQuaTang tmp : thueQuaTangList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueQuaTangServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueTienLuongCong tmp : thueTienLuongCongList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueTienLuongCongServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }

                for (ThueTrungThuong tmp : thueTrungThuongList) {
                    if (tmp.getLoaiThue().getId() == loaiThueId) {
                        if (tmp.getId() == idThue) {
                            thueTrungThuongServiceImpl.CapNhatTrangThaiDaDong(tmp);
                        }
                    } else break;
                }
            }


            for (DanhSachThueMuonDongDTO tmp : danhSachThueMuonDongDTOList) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                Optional<LoaiThue> loaiThue = loaiThueServiceImpl.getById(tmp.getLoaiThueId());
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
