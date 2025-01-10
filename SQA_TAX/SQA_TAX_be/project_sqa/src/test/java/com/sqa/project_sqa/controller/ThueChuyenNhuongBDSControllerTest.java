package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueChuyenNhuongBDSController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueChuyenNhuongBatDongSanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ThueChuyenNhuongBDSControllerTest {
    @InjectMocks
    ThueChuyenNhuongBDSController thueChuyenNhuongBDSController;

    @Mock
    ThueChuyenNhuongBatDongSanService thueChuyenNhuongBDSService;

    @Mock
    NguoiDongThueRepository nguoiDongThueRepository;

    @Mock
    LoaiThueService loaiThueService;

    @Mock
    BindingResult bindingResult;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    // test submit khi có mst tồn tại
    @Test
    public void testSubmit() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setMst("123456789");
        ThueChuyenNhuongBDS.setGiaTriChuyenNhuong(1000);
//        ThueChuyenNhuongBDS.setId(1);
//        ThueChuyenNhuongBDS.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBDSService.Tax_land_transfer(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueChuyenNhuongBDSService.saveThueChuyenNhuongBDS(any(ThueChuyenNhuongBDS.class))).thenReturn(ThueChuyenNhuongBDS);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueChuyenNhuongBDS
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setMst("1234567899");
        ThueChuyenNhuongBDS.setGiaTriChuyenNhuong(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueChuyenNhuongBDSService.Tax_land_transfer(new BigDecimal("1000"))).thenReturn("50");
        when(thueChuyenNhuongBDSService.saveThueChuyenNhuongBDS(any(ThueChuyenNhuongBDS.class))).thenReturn(ThueChuyenNhuongBDS);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setMst("123456789");
        ThueChuyenNhuongBDS.setGiaTriChuyenNhuong(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setMst("123456789");
        ThueChuyenNhuongBDS.setGiaTriChuyenNhuong(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBDSService.saveThueChuyenNhuongBDS(any(ThueChuyenNhuongBDS.class))).thenReturn(ThueChuyenNhuongBDS);
        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setId(1);
        ThueChuyenNhuongBDS.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS.setMst("123456789");
        ThueChuyenNhuongBDS.setGiaTriChuyenNhuong(1000);
        ThueChuyenNhuongBDS.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBDSService.Tax_land_transfer(new BigDecimal("1000"))).thenReturn("50");
        when(thueChuyenNhuongBDSService.saveThueChuyenNhuongBDS(any(ThueChuyenNhuongBDS.class))).thenReturn(ThueChuyenNhuongBDS);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.submit(ThueChuyenNhuongBDS, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueChuyenNhuongBDS objects
        List<ThueChuyenNhuongBDS> ThueChuyenNhuongBDSList = new ArrayList<>();
        ThueChuyenNhuongBDS ThueChuyenNhuongBDS1 = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS1.setMst("123456789");
        ThueChuyenNhuongBDSList.add(ThueChuyenNhuongBDS1);

        ThueChuyenNhuongBDS ThueChuyenNhuongBDS2 = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS2.setMst("987654321");
        ThueChuyenNhuongBDSList.add(ThueChuyenNhuongBDS2);

        // Create a LoaiThue object and set it to the ThueChuyenNhuongBDS objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueChuyenNhuongBDS1.setLoaiThue(loaiThue);
        ThueChuyenNhuongBDS2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueChuyenNhuongBDSService.getAll()).thenReturn(ThueChuyenNhuongBDSList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueChuyenNhuongBDSController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueChuyenNhuongBDSList, responseEntity.getBody());
    }
}
