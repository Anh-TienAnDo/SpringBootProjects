package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueQuaTangController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueQuaTangService;
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

public class ThueQuaTangControllerTest {
    @InjectMocks
    ThueQuaTangController thueQuaTangController;

    @Mock
    ThueQuaTangService thueQuaTangService;

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
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setMst("123456789");
        ThueQuaTang.setThuNhapChiuThue(1000);
//        ThueQuaTang.setId(1);
//        ThueQuaTang.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueQuaTangService.Tax_present(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueQuaTangService.saveThueQuaTang(any(ThueQuaTang.class))).thenReturn(ThueQuaTang);

        ResponseEntity<?> responseEntity = thueQuaTangController.submit(ThueQuaTang, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueQuaTang
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setMst("1234567899");
        ThueQuaTang.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueQuaTangService.Tax_present(new BigDecimal("1000"))).thenReturn("50");
        when(thueQuaTangService.saveThueQuaTang(any(ThueQuaTang.class))).thenReturn(ThueQuaTang);

        ResponseEntity<?> responseEntity = thueQuaTangController.submit(ThueQuaTang, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setMst("123456789");
        ThueQuaTang.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueQuaTangController.submit(ThueQuaTang, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setMst("123456789");
        ThueQuaTang.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueQuaTangService.saveThueQuaTang(any(ThueQuaTang.class))).thenReturn(ThueQuaTang);
        ResponseEntity<?> responseEntity = thueQuaTangController.submit(ThueQuaTang, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setId(1);
        ThueQuaTang.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueQuaTangController.submit(ThueQuaTang,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueQuaTang ThueQuaTang = new ThueQuaTang();
        ThueQuaTang.setMst("123456789");
        ThueQuaTang.setThuNhapChiuThue(1000);
        ThueQuaTang.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueQuaTangService.Tax_present(new BigDecimal("1000"))).thenReturn("50");
        when(thueQuaTangService.saveThueQuaTang(any(ThueQuaTang.class))).thenReturn(ThueQuaTang);

        ResponseEntity<?> responseEntity = thueQuaTangController.submit(ThueQuaTang, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueQuaTang objects
        List<ThueQuaTang> ThueQuaTangList = new ArrayList<>();
        ThueQuaTang ThueQuaTang1 = new ThueQuaTang();
        ThueQuaTang1.setMst("123456789");
        ThueQuaTangList.add(ThueQuaTang1);

        ThueQuaTang ThueQuaTang2 = new ThueQuaTang();
        ThueQuaTang2.setMst("987654321");
        ThueQuaTangList.add(ThueQuaTang2);

        // Create a LoaiThue object and set it to the ThueQuaTang objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueQuaTang1.setLoaiThue(loaiThue);
        ThueQuaTang2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueQuaTangService.getAll()).thenReturn(ThueQuaTangList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueQuaTangController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueQuaTangList, responseEntity.getBody());
    }
}
