package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueNhuongQuyenThuongMai;
import com.sqa.project_sqa.repositories.ThueNhuongQuyenThuongMaiRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ThueNhuongQuyenThuongMaiServiceTest {
    @InjectMocks
    ThueNhuongQuyenThuongMaiServiceIml thueNhuongQuyenThuongMaiServiceiMPL;

    @Mock
    ThueNhuongQuyenThuongMaiRepo thueNhuongQuyenThuongMaiRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueNhuongQuyenThuongMai thue1 = new ThueNhuongQuyenThuongMai();
        ThueNhuongQuyenThuongMai thue2 = new ThueNhuongQuyenThuongMai();
        when(thueNhuongQuyenThuongMaiRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueNhuongQuyenThuongMai> result = thueNhuongQuyenThuongMaiServiceiMPL.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueNhuongQuyenThuongMaiTest() {
        ThueNhuongQuyenThuongMai thue = new ThueNhuongQuyenThuongMai();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueNhuongQuyenThuongMaiRepo.save(any(ThueNhuongQuyenThuongMai.class))).thenReturn(thue);

        ThueNhuongQuyenThuongMai result = thueNhuongQuyenThuongMaiServiceiMPL.saveThueNhuongQuyenThuongMaiService(thue);

        assertEquals(thue, result);
    }
}
