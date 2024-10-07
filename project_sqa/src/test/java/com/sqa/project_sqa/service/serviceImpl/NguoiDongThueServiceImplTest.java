package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.NguoiDongThueService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class NguoiDongThueServiceImplTest {
    @Mock
    private NguoiDongThueRepository nguoiDongThueRepository;

    @InjectMocks
    private NguoiDongThueService nguoiDongThueService = new NguoiDongThueServiceImpl();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidNguoiDongThueDTO() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();

        // Tạo một đối tượng DTO hợp lệ
        NguoiDongThueDTO validDTO = new NguoiDongThueDTO();
        validDTO.setHoVaTen("Nguyễn Văn A");
        validDTO.setGioiTinh("Nam");
        validDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        validDTO.setSdt("0987654321");
        validDTO.setEmail("example@example.com");
        validDTO.setSoGiayTo("123456789012");
        validDTO.setNgayCap(Date.valueOf("2022-01-01"));
        validDTO.setNoiCap("Hà Nội");
        validDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        validDTO.setDchk_tinhThanhPho("Hà Nội");
        validDTO.setDchk_QuanHuyen("Ba Đình");
        validDTO.setDchk_xaPhuong("Cống Vị");
        validDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        validDTO.setDcct_tinhThanhPho("Hà Nội");
        validDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        validDTO.setDcct_xaPhuong("Lý Thái Tổ");
        validDTO.setTaxAgency("Tax Agency");

        // Kiểm tra xem DTO có được coi là hợp lệ không
        assertTrue(service.isValidNguoiDongThueDTO(validDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_HasFieldNull() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen(null); // Set hoVaTen to null
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("example@example.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        // Set other fields
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testInValidNguoiDongThueDTO_HasFieldEmpty() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen(""); // Set hoVaTen to empty string
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("example@example.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        // Set other fields
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }

// Similarly, add test cases for other fields being null or empty

    @Test
    public void testInValidNguoiDongThueDTO_InvalidHoVaTen_HasNumericCharacters() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A 123"); // họ tên chứa số và kí tự đặc biệt
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidHoVaTen_HasSpecialCharacters() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A *"); // họ tên chứa kí tự đặc biệt
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSDT_Has11NumericCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("09876543222"); // sdt có 11 số .
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testInValidNguoiDongThueDTO_InvalidSDT_HasSpecialCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("098765432*"); // sdt có 11 số .
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSDT_HasLetterCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("098765432A"); // sdt có 11 số .
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSDT_Has9NumericCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("098765432"); // sdt có 11 số .
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testInValidNguoiDongThueDTO_InvalidSoGiayTo_Has11NumericCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654322");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("12345678901");// 11 số
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSoGiayTo_Has13NumericCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654322");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("1234567890122");// 13 số
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSoGiayTo_HasSpecialCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654322");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789*");// chua ki tu dac biet
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }
    @Test
    public void testInValidNguoiDongThueDTO_InvalidSoGiayTo_HasLetterCharacters() {
        boolean expectedResult = false;
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654322");
        invalidDTO.setEmail("test@gmail.com");
        invalidDTO.setSoGiayTo("123456789b");// chua ki tu chu
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertEquals(expectedResult,service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testInValidNguoiDongThueDTO_InvalidEmail() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("Nguyễn Văn A");
        invalidDTO.setGioiTinh("Nam");
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("jiji22dd@*vtc.vn");
        invalidDTO.setSoGiayTo("123456789012");
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testInvalidNguoiDongThueDTO() {
        NguoiDongThueServiceImpl service = new NguoiDongThueServiceImpl();

        // Tạo một đối tượng DTO không hợp lệ
        NguoiDongThueDTO invalidDTO = new NguoiDongThueDTO();
        invalidDTO.setHoVaTen("123 Nguyễn Văn A"); // Họ và tên chứa số
        invalidDTO.setGioiTinh(""); // Giới tính không được để trống
        invalidDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        invalidDTO.setSdt("0987654321");
        invalidDTO.setEmail("invalidemail"); // Email không hợp lệ
        invalidDTO.setSoGiayTo("1234567890123"); // Số CCCD có nhiều hơn 12 chữ số
        invalidDTO.setNgayCap(Date.valueOf("2022-01-01"));
        invalidDTO.setNoiCap("Hà Nội");
        invalidDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        invalidDTO.setDchk_tinhThanhPho("Hà Nội");
        invalidDTO.setDchk_QuanHuyen("Ba Đình");
        invalidDTO.setDchk_xaPhuong("Cống Vị");
        invalidDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        invalidDTO.setDcct_tinhThanhPho("Hà Nội");
        invalidDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        invalidDTO.setDcct_xaPhuong("Lý Thái Tổ");
        invalidDTO.setTaxAgency("Tax Agency");

        // Kiểm tra xem DTO có được coi là không hợp lệ không
        assertFalse(service.isValidNguoiDongThueDTO(invalidDTO));
    }

    @Test
    public void testRegisterTaxCode_WhenEmailExists() {
        // Arrange
        NguoiDongThueDTO nguoiDongThueDTO = new NguoiDongThueDTO();
        nguoiDongThueDTO.setHoVaTen("Nguyễn Văn A"); // họ tên chứa số và kí tự đặc biệt
        nguoiDongThueDTO.setGioiTinh("Nam");
        nguoiDongThueDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        nguoiDongThueDTO.setSdt("0987654321");
        nguoiDongThueDTO.setEmail("test@gmail.com");
        nguoiDongThueDTO.setSoGiayTo("123456789012");
        nguoiDongThueDTO.setNgayCap(Date.valueOf("2022-01-01"));
        nguoiDongThueDTO.setNoiCap("Hà Nội");
        nguoiDongThueDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        nguoiDongThueDTO.setDchk_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDchk_QuanHuyen("Ba Đình");
        nguoiDongThueDTO.setDchk_xaPhuong("Cống Vị");
        nguoiDongThueDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        nguoiDongThueDTO.setDcct_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        nguoiDongThueDTO.setDcct_xaPhuong("Lý Thái Tổ");
        nguoiDongThueDTO.setTaxAgency("Tax Agency");
        when(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<?> responseEntity = nguoiDongThueService.registerTaxCode(nguoiDongThueDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("{\"code\": \"01\", \"message\": \"Email đã được đăng kí.\"}", responseEntity.getBody());
    }
    @Test
    public void testRegisterTaxCode_WhenPhoneExists() {
        // Arrange
        NguoiDongThueDTO nguoiDongThueDTO = new NguoiDongThueDTO();
        nguoiDongThueDTO.setHoVaTen("Nguyễn Văn A"); // họ tên chứa số và kí tự đặc biệt
        nguoiDongThueDTO.setGioiTinh("Nam");
        nguoiDongThueDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        nguoiDongThueDTO.setSdt("0987654321");
        nguoiDongThueDTO.setEmail("test@gmail.com");
        nguoiDongThueDTO.setSoGiayTo("123456789012");
        nguoiDongThueDTO.setNgayCap(Date.valueOf("2022-01-01"));
        nguoiDongThueDTO.setNoiCap("Hà Nội");
        nguoiDongThueDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        nguoiDongThueDTO.setDchk_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDchk_QuanHuyen("Ba Đình");
        nguoiDongThueDTO.setDchk_xaPhuong("Cống Vị");
        nguoiDongThueDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        nguoiDongThueDTO.setDcct_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        nguoiDongThueDTO.setDcct_xaPhuong("Lý Thái Tổ");
        nguoiDongThueDTO.setTaxAgency("Tax Agency");
        when(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())).thenReturn(false);
        when(nguoiDongThueRepository.existsBySdt(nguoiDongThueDTO.getSdt())).thenReturn(true);

        // Act
        ResponseEntity<?> responseEntity = nguoiDongThueService.registerTaxCode(nguoiDongThueDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("{\"code\": \"02\", \"message\": \"Số điện thoại đã được đăng kí.\"}", responseEntity.getBody());
    }

    @Test
    public void testRegisterTaxCode_WhenCCCDExists() {
        // Arrange
        NguoiDongThueDTO nguoiDongThueDTO = new NguoiDongThueDTO();
        nguoiDongThueDTO.setHoVaTen("Nguyễn Văn A"); // họ tên chứa số và kí tự đặc biệt
        nguoiDongThueDTO.setGioiTinh("Nam");
        nguoiDongThueDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        nguoiDongThueDTO.setSdt("0987654321");
        nguoiDongThueDTO.setEmail("test@gmail.com");
        nguoiDongThueDTO.setSoGiayTo("123456789012");
        nguoiDongThueDTO.setNgayCap(Date.valueOf("2022-01-01"));
        nguoiDongThueDTO.setNoiCap("Hà Nội");
        nguoiDongThueDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        nguoiDongThueDTO.setDchk_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDchk_QuanHuyen("Ba Đình");
        nguoiDongThueDTO.setDchk_xaPhuong("Cống Vị");
        nguoiDongThueDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        nguoiDongThueDTO.setDcct_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        nguoiDongThueDTO.setDcct_xaPhuong("Lý Thái Tổ");
        nguoiDongThueDTO.setTaxAgency("Tax Agency");
        when(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())).thenReturn(false);
        when(nguoiDongThueRepository.existsBySdt(nguoiDongThueDTO.getSdt())).thenReturn(false);
        when(nguoiDongThueRepository.existsByCCCD(nguoiDongThueDTO.getSoGiayTo())).thenReturn(true);

        // Act
        ResponseEntity<?> responseEntity = nguoiDongThueService.registerTaxCode(nguoiDongThueDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("{\"code\": \"03\", \"message\": \"Số CCCD đã được đăng kí MST.\"}", responseEntity.getBody());
    }

    @Test
    public void testRegisterTaxCode_WhenInvalidData() {
        // Arrange
        NguoiDongThueServiceImpl serviceImpl = new NguoiDongThueServiceImpl();

        NguoiDongThueDTO nguoiDongThueDTO = new NguoiDongThueDTO();
        nguoiDongThueDTO.setHoVaTen("Nguyễn Văn A 123 !!!!"); // họ tên chứa số và kí tự đặc biệt
        nguoiDongThueDTO.setGioiTinh("Nam");
        nguoiDongThueDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        nguoiDongThueDTO.setSdt("0987654321");
        nguoiDongThueDTO.setEmail("test@gmail.com");
        nguoiDongThueDTO.setSoGiayTo("123456789012");
        nguoiDongThueDTO.setNgayCap(Date.valueOf("2022-01-01"));
        nguoiDongThueDTO.setNoiCap("Hà Nội");
        nguoiDongThueDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        nguoiDongThueDTO.setDchk_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDchk_QuanHuyen("Ba Đình");
        nguoiDongThueDTO.setDchk_xaPhuong("Cống Vị");
        nguoiDongThueDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        nguoiDongThueDTO.setDcct_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        nguoiDongThueDTO.setDcct_xaPhuong("Lý Thái Tổ");
        nguoiDongThueDTO.setTaxAgency("Tax Agency");
        // Set invalid data here
        when(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())).thenReturn(false);
        when(nguoiDongThueRepository.existsBySdt(nguoiDongThueDTO.getSdt())).thenReturn(false);
        when(nguoiDongThueRepository.existsByCCCD(nguoiDongThueDTO.getSoGiayTo())).thenReturn(false);

        // Act
        ResponseEntity<?> responseEntity = nguoiDongThueService.registerTaxCode(nguoiDongThueDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("{\"code\": \"04\", \"message\": \"Dữ liệu không hợp lệ.\"}", responseEntity.getBody());
    }
    @Test
    public void testRegisterTaxCode_WhenValidData() {
        // Arrange
        NguoiDongThueDTO nguoiDongThueDTO = new NguoiDongThueDTO();
        nguoiDongThueDTO.setHoVaTen("Nguyễn Văn A");
        nguoiDongThueDTO.setGioiTinh("Nam");
        nguoiDongThueDTO.setNgaySinh(Date.valueOf("1990-01-01"));
        nguoiDongThueDTO.setSdt("0987654321");
        nguoiDongThueDTO.setEmail("example@example.com");
        nguoiDongThueDTO.setSoGiayTo("123456789012");
        nguoiDongThueDTO.setNgayCap(Date.valueOf("2022-01-01"));
        nguoiDongThueDTO.setNoiCap("Hà Nội");
        nguoiDongThueDTO.setDchk_soNhaDuongXom("123 Đường ABC");
        nguoiDongThueDTO.setDchk_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDchk_QuanHuyen("Ba Đình");
        nguoiDongThueDTO.setDchk_xaPhuong("Cống Vị");
        nguoiDongThueDTO.setDcct_soNhaDuongXom("456 Đường XYZ");
        nguoiDongThueDTO.setDcct_tinhThanhPho("Hà Nội");
        nguoiDongThueDTO.setDcct_QuanHuyen("Hoàn Kiếm");
        nguoiDongThueDTO.setDcct_xaPhuong("Lý Thái Tổ");
        nguoiDongThueDTO.setTaxAgency("Tax Agency");

        // Set valid data here
        when(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())).thenReturn(false);
        when(nguoiDongThueRepository.existsBySdt(nguoiDongThueDTO.getSdt())).thenReturn(false);
        when(nguoiDongThueRepository.existsByCCCD(nguoiDongThueDTO.getSoGiayTo())).thenReturn(false);

        // Act
        ResponseEntity<?> responseEntity = nguoiDongThueService.registerTaxCode(nguoiDongThueDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Add more assertions here if needed
    }

}