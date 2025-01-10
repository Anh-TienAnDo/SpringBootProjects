package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueTienLuongCong;

import com.sqa.project_sqa.repositories.ThueTienLuongCongRepository;
import com.sqa.project_sqa.service.serviceImpl.ThueTienLuongCongServiceImpl;
import com.sqa.project_sqa.service.serviceImpl.ThueTienLuongCongServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ThueTienLuongCongServiceTest {
    @InjectMocks
    ThueTienLuongCongServiceImpl thueTienLuongCongService;

    @Mock
    ThueTienLuongCongRepository thueTienLuongCongRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueTienLuongCong thue1 = new ThueTienLuongCong();
        ThueTienLuongCong thue2 = new ThueTienLuongCong();
        when(thueTienLuongCongRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueTienLuongCong> result = thueTienLuongCongService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueTienLuongCongTest() {
        ThueTienLuongCong thue = new ThueTienLuongCong();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000);
        thue.setTongThuePhaiNop(500000);
        // giả sử thành công
        when(thueTienLuongCongRepo.save(any(ThueTienLuongCong.class))).thenReturn(thue);

        ThueTienLuongCong savedThueTienLuongCong = thueTienLuongCongService.saveThueTienLuongCong(thue);

        assertEquals(1, savedThueTienLuongCong.getId());
        assertEquals(1000000, savedThueTienLuongCong.getThuNhapChiuThue());
    }
}