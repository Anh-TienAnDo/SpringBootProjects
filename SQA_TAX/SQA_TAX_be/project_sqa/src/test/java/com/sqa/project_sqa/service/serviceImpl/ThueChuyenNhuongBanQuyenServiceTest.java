package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBanQuyen;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBanQuyenRepo;
import com.sqa.project_sqa.service.serviceImpl.ThueChuyenNhuongBanQuyenServiceImpl;
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

public class ThueChuyenNhuongBanQuyenServiceTest {
    @InjectMocks
    ThueChuyenNhuongBanQuyenServiceImpl thueChuyenNhuongBanQuyenService;

    @Mock
    ThueChuyenNhuongBanQuyenRepo thueChuyenNhuongBanQuyenRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueChuyenNhuongBanQuyen thue1 = new ThueChuyenNhuongBanQuyen();
        ThueChuyenNhuongBanQuyen thue2 = new ThueChuyenNhuongBanQuyen();
        when(thueChuyenNhuongBanQuyenRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueChuyenNhuongBanQuyen> result = thueChuyenNhuongBanQuyenService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueChuyenNhuongBanQuyenTest() {
        ThueChuyenNhuongBanQuyen thue = new ThueChuyenNhuongBanQuyen();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueChuyenNhuongBanQuyenRepo.save(any(ThueChuyenNhuongBanQuyen.class))).thenReturn(thue);

        ThueChuyenNhuongBanQuyen result = thueChuyenNhuongBanQuyenService.saveThueChuyenNhuongBanQuyem(thue);

        assertEquals(thue, result);
    }
}
