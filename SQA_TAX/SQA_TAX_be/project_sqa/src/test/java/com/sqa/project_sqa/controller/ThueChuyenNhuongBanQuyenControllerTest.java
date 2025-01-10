package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueChuyenNhuongBanQuyenController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueChuyenNhuongBanQuyenService;
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

public class ThueChuyenNhuongBanQuyenControllerTest {
    @InjectMocks
    ThueChuyenNhuongBanQuyenController thueChuyenNhuongBanQuyenController;

    @Mock
    ThueChuyenNhuongBanQuyenService thueChuyenNhuongBanQuyenService;

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
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setMst("123456789");
        ThueChuyenNhuongBanQuyen.setThuNhapChiuThue(1000);
//        ThueChuyenNhuongBanQuyen.setId(1);
//        ThueChuyenNhuongBanQuyen.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBanQuyenService.Tax_license(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueChuyenNhuongBanQuyenService.saveThueChuyenNhuongBanQuyem(any(ThueChuyenNhuongBanQuyen.class))).thenReturn(ThueChuyenNhuongBanQuyen);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueChuyenNhuongBanQuyen
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setMst("1234567899");
        ThueChuyenNhuongBanQuyen.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueChuyenNhuongBanQuyenService.Tax_license(new BigDecimal("1000"))).thenReturn("50");
        when(thueChuyenNhuongBanQuyenService.saveThueChuyenNhuongBanQuyem(any(ThueChuyenNhuongBanQuyen.class))).thenReturn(ThueChuyenNhuongBanQuyen);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setMst("123456789");
        ThueChuyenNhuongBanQuyen.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setMst("123456789");
        ThueChuyenNhuongBanQuyen.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBanQuyenService.saveThueChuyenNhuongBanQuyem(any(ThueChuyenNhuongBanQuyen.class))).thenReturn(ThueChuyenNhuongBanQuyen);
        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setId(1);
        ThueChuyenNhuongBanQuyen.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueChuyenNhuongBanQuyen ThueChuyenNhuongBanQuyen = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen.setMst("123456789");
        ThueChuyenNhuongBanQuyen.setThuNhapChiuThue(1000);
        ThueChuyenNhuongBanQuyen.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueChuyenNhuongBanQuyenService.Tax_license(new BigDecimal("1000"))).thenReturn("50");
        when(thueChuyenNhuongBanQuyenService.saveThueChuyenNhuongBanQuyem(any(ThueChuyenNhuongBanQuyen.class))).thenReturn(ThueChuyenNhuongBanQuyen);

        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.submit(ThueChuyenNhuongBanQuyen, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueChuyenNhuongBanQuyen objects
        List<ThueChuyenNhuongBanQuyen> thueChuyenNhuongBanQuyenList = new ArrayList<>();
        ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen1 = new ThueChuyenNhuongBanQuyen();
        thueChuyenNhuongBanQuyen1.setMst("123456789");
        thueChuyenNhuongBanQuyenList.add(thueChuyenNhuongBanQuyen1);

        ThueChuyenNhuongBanQuyen thueChuyenNhuongBanQuyen2 = new ThueChuyenNhuongBanQuyen();
        thueChuyenNhuongBanQuyen2.setMst("987654321");
        thueChuyenNhuongBanQuyenList.add(thueChuyenNhuongBanQuyen2);

        // Create a LoaiThue object and set it to the ThueChuyenNhuongBanQuyen objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        thueChuyenNhuongBanQuyen1.setLoaiThue(loaiThue);
        thueChuyenNhuongBanQuyen2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueChuyenNhuongBanQuyenService.getAll()).thenReturn(thueChuyenNhuongBanQuyenList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueChuyenNhuongBanQuyenController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(thueChuyenNhuongBanQuyenList, responseEntity.getBody());
    }
}
