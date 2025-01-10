package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueTienLuongCongController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueTienLuongCong;
import com.sqa.project_sqa.entities.ThueTienLuongCong;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueTienLuongCongService;
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

public class ThueTienLuongCongControllerTest {
    @InjectMocks
    ThueTienLuongCongController thueTienLuongCongController;

    @Mock
    ThueTienLuongCongService thueTienLuongCongService;

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
        ThueTienLuongCong thueTienLuongCong = new ThueTienLuongCong();
        thueTienLuongCong.setMst("123456789");
        thueTienLuongCong.setThuNhapChiuThue(1000);
        thueTienLuongCong.setCuTru(false);



        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTienLuongCongService.Tax_type3(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueTienLuongCongService.saveThueTienLuongCong(any(ThueTienLuongCong.class))).thenReturn(thueTienLuongCong);

        ResponseEntity<?> responseEntity = thueTienLuongCongController.submitTKThueTienLuongCong(thueTienLuongCong, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueTienLuongCong
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueTienLuongCong ThueTienLuongCong = new ThueTienLuongCong();
        ThueTienLuongCong.setMst("1234567899");
        ThueTienLuongCong.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueTienLuongCongService.Tax_type3(new BigDecimal("1000"))).thenReturn("50");
        when(thueTienLuongCongService.saveThueTienLuongCong(any(ThueTienLuongCong.class))).thenReturn(ThueTienLuongCong);

        ResponseEntity<?> responseEntity = thueTienLuongCongController.submitTKThueTienLuongCong(ThueTienLuongCong, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueTienLuongCong ThueTienLuongCong = new ThueTienLuongCong();
        ThueTienLuongCong.setMst("123456789");
        ThueTienLuongCong.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueTienLuongCongController.submitTKThueTienLuongCong(ThueTienLuongCong, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueTienLuongCong ThueTienLuongCong = new ThueTienLuongCong();
        ThueTienLuongCong.setMst("123456789");
        ThueTienLuongCong.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTienLuongCongService.saveThueTienLuongCong(any(ThueTienLuongCong.class))).thenReturn(ThueTienLuongCong);
        ResponseEntity<?> responseEntity = thueTienLuongCongController.submitTKThueTienLuongCong(ThueTienLuongCong, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueTienLuongCong ThueTienLuongCong = new ThueTienLuongCong();
        ThueTienLuongCong.setId(1);
        ThueTienLuongCong.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueTienLuongCongController.submitTKThueTienLuongCong(ThueTienLuongCong,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueTienLuongCong thueTienLuongCong = new ThueTienLuongCong();
        thueTienLuongCong.setMst("123456789");
        thueTienLuongCong.setThuNhapChiuThue(1000);
        thueTienLuongCong.setLoaiThueId(1);
        thueTienLuongCong.setTongThuePhaiNop(1000);
//        đối với trường hợp không cư trú thì sử dụng Tax_type3
        thueTienLuongCong.setCuTru(false);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTienLuongCongService.Tax_type3(new BigDecimal("1000"))).thenReturn("50");
        when(thueTienLuongCongService.saveThueTienLuongCong(any(ThueTienLuongCong.class))).thenReturn(thueTienLuongCong);

        ResponseEntity<?> responseEntity = thueTienLuongCongController.submitTKThueTienLuongCong(thueTienLuongCong, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueTienLuongCong objects
        List<ThueTienLuongCong> ThueTienLuongCongList = new ArrayList<>();
        ThueTienLuongCong ThueTienLuongCong1 = new ThueTienLuongCong();
        ThueTienLuongCong1.setMst("123456789");
        ThueTienLuongCongList.add(ThueTienLuongCong1);

        ThueTienLuongCong ThueTienLuongCong2 = new ThueTienLuongCong();
        ThueTienLuongCong2.setMst("987654321");
        ThueTienLuongCongList.add(ThueTienLuongCong2);

        // Create a LoaiThue object and set it to the ThueTienLuongCong objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueTienLuongCong1.setLoaiThue(loaiThue);
        ThueTienLuongCong2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueTienLuongCongService.getAll()).thenReturn(ThueTienLuongCongList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueTienLuongCongController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueTienLuongCongList, responseEntity.getBody());
    }
}
