package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueNhuongQuyenThuongMaiController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;
import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueNhuongQuyenThuongMaiService;
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

public class ThueNhuongQuyenThuongMaiControllerTest {
    @InjectMocks
    ThueNhuongQuyenThuongMaiController thueNhuongQuyenThuongMaiController;

    @Mock
    ThueNhuongQuyenThuongMaiService thueNhuongQuyenThuongMaiService;

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
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setMst("123456789");
        ThueNhuongQuyenThuongMai.setThuNhapChiuThue(1000);
//        ThueNhuongQuyenThuongMai.setId(1);
//        ThueNhuongQuyenThuongMai.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueNhuongQuyenThuongMaiService.Tax_ecommerce(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueNhuongQuyenThuongMaiService.saveThueNhuongQuyenThuongMaiService(any(ThueNhuongQuyenThuongMai.class))).thenReturn(ThueNhuongQuyenThuongMai);

        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueNhuongQuyenThuongMai
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setMst("1234567899");
        ThueNhuongQuyenThuongMai.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueNhuongQuyenThuongMaiService.Tax_ecommerce(new BigDecimal("1000"))).thenReturn("50");
        when(thueNhuongQuyenThuongMaiService.saveThueNhuongQuyenThuongMaiService(any(ThueNhuongQuyenThuongMai.class))).thenReturn(ThueNhuongQuyenThuongMai);

        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setMst("123456789");
        ThueNhuongQuyenThuongMai.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setMst("123456789");
        ThueNhuongQuyenThuongMai.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueNhuongQuyenThuongMaiService.saveThueNhuongQuyenThuongMaiService(any(ThueNhuongQuyenThuongMai.class))).thenReturn(ThueNhuongQuyenThuongMai);
        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setId(1);
        ThueNhuongQuyenThuongMai.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai.setMst("123456789");
        ThueNhuongQuyenThuongMai.setThuNhapChiuThue(1000);
        ThueNhuongQuyenThuongMai.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueNhuongQuyenThuongMaiService.Tax_ecommerce(new BigDecimal("1000"))).thenReturn("50");
        when(thueNhuongQuyenThuongMaiService.saveThueNhuongQuyenThuongMaiService(any(ThueNhuongQuyenThuongMai.class))).thenReturn(ThueNhuongQuyenThuongMai);

        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.submit(ThueNhuongQuyenThuongMai, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueNhuongQuyenThuongMai objects
        List<ThueNhuongQuyenThuongMai> ThueNhuongQuyenThuongMaiList = new ArrayList<>();
        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai1 = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai1.setMst("123456789");
        ThueNhuongQuyenThuongMaiList.add(ThueNhuongQuyenThuongMai1);

        ThueNhuongQuyenThuongMai ThueNhuongQuyenThuongMai2 = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai2.setMst("987654321");
        ThueNhuongQuyenThuongMaiList.add(ThueNhuongQuyenThuongMai2);

        // Create a LoaiThue object and set it to the ThueNhuongQuyenThuongMai objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueNhuongQuyenThuongMai1.setLoaiThue(loaiThue);
        ThueNhuongQuyenThuongMai2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueNhuongQuyenThuongMaiService.getAll()).thenReturn(ThueNhuongQuyenThuongMaiList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueNhuongQuyenThuongMaiController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueNhuongQuyenThuongMaiList, responseEntity.getBody());
    }
}
