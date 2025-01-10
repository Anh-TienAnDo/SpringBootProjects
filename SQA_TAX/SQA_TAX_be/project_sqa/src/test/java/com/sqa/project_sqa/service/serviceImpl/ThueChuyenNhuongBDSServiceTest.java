package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueChuyenNhuongBDS;
import com.sqa.project_sqa.repositories.ThueChuyenNhuongBatDongSanRepo;
import com.sqa.project_sqa.service.serviceImpl.ThueChuyenNhuongBDSServiceImpl;
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

public class ThueChuyenNhuongBDSServiceTest {
    @InjectMocks
    ThueChuyenNhuongBDSServiceImpl thueChuyenNhuongBDSService;

    @Mock
    ThueChuyenNhuongBatDongSanRepo thueChuyenNhuongBatDongSanRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueChuyenNhuongBDS thue1 = new ThueChuyenNhuongBDS();
        ThueChuyenNhuongBDS thue2 = new ThueChuyenNhuongBDS();
        when(thueChuyenNhuongBatDongSanRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueChuyenNhuongBDS> result = thueChuyenNhuongBDSService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueChuyenNhuongBDSTest() {
        ThueChuyenNhuongBDS thue = new ThueChuyenNhuongBDS();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setGiaTriChuyenNhuong(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueChuyenNhuongBatDongSanRepo.save(any(ThueChuyenNhuongBDS.class))).thenReturn(thue);

        ThueChuyenNhuongBDS result = thueChuyenNhuongBatDongSanRepo.save(thue);

        assertEquals(thue, result);
    }
}
