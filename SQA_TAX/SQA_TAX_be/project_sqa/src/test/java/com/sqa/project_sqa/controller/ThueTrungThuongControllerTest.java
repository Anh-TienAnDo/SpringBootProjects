package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueTrungThuongController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueTrungThuong;
import com.sqa.project_sqa.entities.ThueTrungThuong;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueTrungThuongService;
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

public class ThueTrungThuongControllerTest {
    @InjectMocks
    ThueTrungThuongController thueTrungThuongController;

    @Mock
    ThueTrungThuongService thueTrungThuongService;

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
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setMst("123456789");
        ThueTrungThuong.setThuNhapChiuThue(1000);
//        ThueTrungThuong.setId(1);
//        ThueTrungThuong.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTrungThuongService.Tax_win_prize(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueTrungThuongService.saveThueTrungThuong(any(ThueTrungThuong.class))).thenReturn(ThueTrungThuong);

        ResponseEntity<?> responseEntity = thueTrungThuongController.submit(ThueTrungThuong, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp ThueTrungThuong
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setMst("1234567899");
        ThueTrungThuong.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueTrungThuongService.Tax_win_prize(new BigDecimal("1000"))).thenReturn("50");
        when(thueTrungThuongService.saveThueTrungThuong(any(ThueTrungThuong.class))).thenReturn(ThueTrungThuong);

        ResponseEntity<?> responseEntity = thueTrungThuongController.submit(ThueTrungThuong, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
    // test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setMst("123456789");
        ThueTrungThuong.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueTrungThuongController.submit(ThueTrungThuong, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setMst("123456789");
        ThueTrungThuong.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTrungThuongService.saveThueTrungThuong(any(ThueTrungThuong.class))).thenReturn(ThueTrungThuong);
        ResponseEntity<?> responseEntity = thueTrungThuongController.submit(ThueTrungThuong, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setId(1);
        ThueTrungThuong.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueTrungThuongController.submit(ThueTrungThuong,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueTrungThuong ThueTrungThuong = new ThueTrungThuong();
        ThueTrungThuong.setMst("123456789");
        ThueTrungThuong.setThuNhapChiuThue(1000);
        ThueTrungThuong.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueTrungThuongService.Tax_win_prize(new BigDecimal("1000"))).thenReturn("50");
        when(thueTrungThuongService.saveThueTrungThuong(any(ThueTrungThuong.class))).thenReturn(ThueTrungThuong);

        ResponseEntity<?> responseEntity = thueTrungThuongController.submit(ThueTrungThuong, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueTrungThuong objects
        List<ThueTrungThuong> ThueTrungThuongList = new ArrayList<>();
        ThueTrungThuong ThueTrungThuong1 = new ThueTrungThuong();
        ThueTrungThuong1.setMst("123456789");
        ThueTrungThuongList.add(ThueTrungThuong1);

        ThueTrungThuong ThueTrungThuong2 = new ThueTrungThuong();
        ThueTrungThuong2.setMst("987654321");
        ThueTrungThuongList.add(ThueTrungThuong2);

        // Create a LoaiThue object and set it to the ThueTrungThuong objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueTrungThuong1.setLoaiThue(loaiThue);
        ThueTrungThuong2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueTrungThuongService.getAll()).thenReturn(ThueTrungThuongList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueTrungThuongController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueTrungThuongList, responseEntity.getBody());
    }

}
