package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.controller.ThueDauTuVonController;
import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.LoaiThueService;
import com.sqa.project_sqa.service.ThueDauTuVonService;
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
import static org.mockito.Mockito.*;

public class ThueDauTuVonControllerTest {
    @InjectMocks
    ThueDauTuVonController thueDauTuVonController;

    @Mock
    ThueDauTuVonService thueDauTuVonService;

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
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setMst("123456789");
        thueDauTuVon.setThuNhapChiuThue(1000);
//        thueDauTuVon.setId(1);
//        thueDauTuVon.setLoaiThueId(1);
//        when(loaiThueService.getById(1)).thenReturn();
//        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueDauTuVonService.Tax_capital_investments(new BigDecimal("1000"))).thenReturn("50");
        // giả sử đã lưu thành công
        when(thueDauTuVonService.saveThueDauTuVon(any(ThueDauTuVon.class))).thenReturn(thueDauTuVon);

        ResponseEntity<?> responseEntity = thueDauTuVonController.submit(thueDauTuVon, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());
        // với bất kỳ đối số nào của lớp thueDauTuVon
        verify(thueDauTuVonService, times(1)).saveThueDauTuVon(any(ThueDauTuVon.class));
    }
    // test mst chưa tôn tại
    @Test
    public void testSubmitWithNonExistentMst() {
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setMst("1234567899");
        thueDauTuVon.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(nguoiDongThueRepository.existsByMst("1234567899")).thenReturn(false);
        when(thueDauTuVonService.Tax_capital_investments(new BigDecimal("1000"))).thenReturn("50");
        when(thueDauTuVonService.saveThueDauTuVon(any(ThueDauTuVon.class))).thenReturn(thueDauTuVon);

        ResponseEntity<?> responseEntity = thueDauTuVonController.submit(thueDauTuVon, bindingResult);
        // Giả sử actual là một HashMap chứa thông điệp thực tế
        HashMap<String, String> actual = new HashMap<>();
        actual.put("message", "MST không tồn tại");

        assertEquals(actual, responseEntity.getBody());
    }
// test BindingResult có lỗi
    @Test
    public void testSubmitWithBindingResultErrors() {
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setMst("123456789");
        thueDauTuVon.setThuNhapChiuThue(1000);

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = thueDauTuVonController.submit(thueDauTuVon, bindingResult);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
    // test trường âm
    @Test
    public void testSubmitWithThuNhapChiuThue() {
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setMst("123456789");
        thueDauTuVon.setThuNhapChiuThue(-1000);
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueDauTuVonService.saveThueDauTuVon(any(ThueDauTuVon.class))).thenReturn(thueDauTuVon);
        ResponseEntity<?> responseEntity = thueDauTuVonController.submit(thueDauTuVon, bindingResult);

        assertEquals("Giá trị không hợp lệ", responseEntity.getBody());
    }
    // test chưa có loại thuế
    @Test
    public void testSubmitWithInvalidLoaiThueId() {
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setId(1);
        thueDauTuVon.setLoaiThueId(1);
        // giả sử chưa có loại thuế này
        when(loaiThueService.getById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            thueDauTuVonController.submit(thueDauTuVon,bindingResult);
        });

        String expectedMessage = "Loại thuế không tồn tại!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // test đã có loại thuế
    @Test
    public void testSubmitWithExistingLoaiThue() {
        ThueDauTuVon thueDauTuVon = new ThueDauTuVon();
        thueDauTuVon.setMst("123456789");
        thueDauTuVon.setThuNhapChiuThue(1000);
        thueDauTuVon.setLoaiThueId(1);

        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);

        when(loaiThueService.getById(1)).thenReturn(Optional.of(loaiThue));
        when(nguoiDongThueRepository.existsByMst("123456789")).thenReturn(true);
        when(thueDauTuVonService.Tax_capital_investments(new BigDecimal("1000"))).thenReturn("50");
        when(thueDauTuVonService.saveThueDauTuVon(any(ThueDauTuVon.class))).thenReturn(thueDauTuVon);

        ResponseEntity<?> responseEntity = thueDauTuVonController.submit(thueDauTuVon, bindingResult);

        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    public void testGetAll() {
        // Create a list of ThueDauTuVon objects
        List<ThueDauTuVon> ThueDauTuVonList = new ArrayList<>();
        ThueDauTuVon ThueDauTuVon1 = new ThueDauTuVon();
        ThueDauTuVon1.setMst("123456789");
        ThueDauTuVonList.add(ThueDauTuVon1);

        ThueDauTuVon ThueDauTuVon2 = new ThueDauTuVon();
        ThueDauTuVon2.setMst("987654321");
        ThueDauTuVonList.add(ThueDauTuVon2);

        // Create a LoaiThue object and set it to the ThueDauTuVon objects
        LoaiThue loaiThue = new LoaiThue();
        loaiThue.setId(1);
        ThueDauTuVon1.setLoaiThue(loaiThue);
        ThueDauTuVon2.setLoaiThue(loaiThue);

        // Mock the getAll() method of the service to return the predefined list
        when(thueDauTuVonService.getAll()).thenReturn(ThueDauTuVonList);

        // Call the getAll() method of the controller
        ResponseEntity<?> responseEntity = thueDauTuVonController.getAll();

        // Assert that the response has the HTTP status code OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert that the body of the response matches the predefined list
        assertEquals(ThueDauTuVonList, responseEntity.getBody());
    }

}
