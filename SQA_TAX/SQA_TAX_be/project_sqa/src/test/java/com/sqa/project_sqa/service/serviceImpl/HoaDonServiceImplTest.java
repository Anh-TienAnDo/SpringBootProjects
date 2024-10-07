package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.*;
import com.sqa.project_sqa.payload.dto.DanhSachThueMuonDongDTO;
import com.sqa.project_sqa.payload.dto.HoaDonDTO;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.*;
import com.sqa.project_sqa.service.DangKiMSTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
public class HoaDonServiceImplTest {

    @Mock
    ThueTienLuongCongRepository thueTienLuongCongRepository;
    @Mock
    private HoaDonRepository hoaDonRepository;

    @Mock
    private ThueChuyenNhuongBanQuyenServiceImpl thueChuyenNhuongBanQuyenServiceImpl;

    @Mock
    private ThueChuyenNhuongBDSServiceImpl thueChuyenNhuongBDSServiceImpl;

    @Mock
    private ThueDauTuVonServiceImpl thueDauTuVonServiceImpl;

    @Mock
    private ThueNhuongQuyenThuongMaiServiceIml thueNhuongQuyenThuongMaiServiceImpl;

    @Mock
    private ThueQuaTangServiceImpl thueQuaTangServiceImpl;

    @Mock
    private ThueTienLuongCongServiceImpl thueTienLuongCongServiceImpl;

    @Mock
    private ThueTrungThuongServiceImpl thueTrungThuongServiceImpl;

    @Mock
    LoaiThueRepository loaiThueRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Mock
    NguoiDongThueRepository nguoiDongThueRepository;

    @Mock
    LoaiThueServiceImpl loaiThueServiceImpl;

    @Mock
    ThueChuyenNhuongBatDongSanRepo thueChuyenNhuongBatDongSanRepo;

    @Mock
    ThueDauTuVonRepo thueDauTuVonRepo;

    @Mock
    ThueQuaTangRepo thueQuaTangRepo;

    @Mock
    ThueNhuongQuyenThuongMaiRepo thueNhuongQuyenThuongMaiRepo;

    @Mock
    ThueTrungThuongRepo thueTrungThuongRepo;

    @Mock
    ThueChuyenNhuongBanQuyenRepo thueChuyenNhuongBanQuyenRepo;
    @InjectMocks
    private HoaDonServiceImpl hoaDonServiceImpl = new HoaDonServiceImpl();



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidHoaDonDTO() {
        List<DanhSachThueMuonDongDTO> danhSachThueMuonDongDTOList = new ArrayList<>();
        danhSachThueMuonDongDTOList.add(new DanhSachThueMuonDongDTO(2,1));
        danhSachThueMuonDongDTOList.add(new DanhSachThueMuonDongDTO(2,2));
        // Tạo một đối tượng DTO hợp lệ
        HoaDonDTO hoaDonDTOValid = new HoaDonDTO();
        hoaDonDTOValid.setIdThue(1);
        hoaDonDTOValid.setLoaiThue(2);
        hoaDonDTOValid.setUserId(1);
        hoaDonDTOValid.setThoiGianNopThue(new Date());
        hoaDonDTOValid.setTongThuePhaiDong(100000000);
        hoaDonDTOValid.setNguoiDongThueId(1);
        hoaDonDTOValid.setDanhSachThueMuonDong(danhSachThueMuonDongDTOList);

        if (hoaDonDTOValid.getUserId() == null ||
                hoaDonDTOValid.getNguoiDongThueId()==null||
                hoaDonDTOValid.getThoiGianNopThue() == null ||
                hoaDonDTOValid.getDanhSachThueMuonDong() == null || hoaDonDTOValid.getDanhSachThueMuonDong().isEmpty() ) {
            assertTrue(false);
        }

        List<ThueTienLuongCong> thueTienLuongCongList = new ArrayList<>();
        List<ThueChuyenNhuongBDS> thueChuyenNhuongBDSList = new ArrayList<>();
        List<ThueDauTuVon> thueDauTuVonList = new ArrayList<>();
        List<ThueQuaTang> thueQuaTangList = new ArrayList<>();
        List<ThueNhuongQuyenThuongMai> thueNhuongQuyenThuongMaiList = new ArrayList<>();
        List<ThueTrungThuong> thueTrungThuongList = new ArrayList<>();
        List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList = new ArrayList<>();

        ThueTienLuongCong thueTienLuongCong = new ThueTienLuongCong();
        thueTienLuongCong.setId(1);
        thueTienLuongCong.setLoaiThueId(1);

        thueTienLuongCongList.add(thueTienLuongCong);

        ThueChuyenNhuongBDS thueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        thueChuyenNhuongBDS.setId(1);
        thueChuyenNhuongBDS.setLoaiThueId(2);

        thueChuyenNhuongBDSList.add(thueChuyenNhuongBDS);

        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setId(1);
        thueDauTuVon.setLoaiThueId(3);

        ThueQuaTang thueQuaTang = new ThueQuaTang();
        thueQuaTang.setId(1);
        thueQuaTang.setLoaiThueId(4);

        ThueNhuongQuyenThuongMai thueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        thueNhuongQuyenThuongMai.setId(1);
        thueNhuongQuyenThuongMai.setLoaiThueId(5);

        ThueTrungThuong thueTrungThuong = new ThueTrungThuong();
        thueTrungThuong.setId(1);
        thueTrungThuong.setLoaiThueId(6);

        ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        thueChuyenNhuongBanQuyen.setId(1);
        thueChuyenNhuongBanQuyen.setLoaiThueId(7);

        when(thueTienLuongCongRepository.findAll()).thenReturn(thueTienLuongCongList);
        when(thueChuyenNhuongBatDongSanRepo.findAll()).thenReturn(thueChuyenNhuongBDSList);
        when(thueDauTuVonRepo.findAll()).thenReturn(thueDauTuVonList);
        when(thueQuaTangRepo.findAll()).thenReturn(thueQuaTangList);
        when(thueNhuongQuyenThuongMaiRepo.findAll()).thenReturn(thueNhuongQuyenThuongMaiList);
        when(thueTrungThuongRepo.findAll()).thenReturn(thueTrungThuongList);
        when(thueChuyenNhuongBanQuyenRepo.findAll()).thenReturn(thueChuyenNhuongBanQuyenList);

        for(DanhSachThueMuonDongDTO danhSachThueMuonDongDTO : hoaDonDTOValid.getDanhSachThueMuonDong()){
            boolean check = false;
            for(ThueTienLuongCong tmp : thueTienLuongCongList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueChuyenNhuongBDS tmp : thueChuyenNhuongBDSList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueDauTuVon tmp : thueDauTuVonList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueQuaTang tmp : thueQuaTangList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueNhuongQuyenThuongMai tmp : thueNhuongQuyenThuongMaiList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueTrungThuong tmp : thueTrungThuongList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            for(ThueChuyenNhuongBanQuyen tmp : thueChuyenNhuongBanQuyenList){
                if(tmp.getLoaiThueId()==danhSachThueMuonDongDTO.getIdThue()&&tmp.getId()==danhSachThueMuonDongDTO.getIdThue()){
                    check = true;
                }
            }

            if(!check){
                assertFalse(false);
            }
        }
        assertTrue(true);
    }
}
