package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueTienLuongCong;
import com.sqa.project_sqa.entities.ThueTrungThuong;
import com.sqa.project_sqa.repositories.ThueTienLuongCongRepository;
import com.sqa.project_sqa.repositories.ThueTrungThuongRepo;
import com.sqa.project_sqa.service.serviceImpl.ThueTienLuongCongServiceImpl;
import com.sqa.project_sqa.service.serviceImpl.ThueTrungThuongServiceImpl;
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

public class ThueTrungThuongServiceTest {
    @InjectMocks
    ThueTrungThuongServiceImpl thueTrungThuongService;

    @Mock
    ThueTrungThuongRepo thueTrungThuongRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueTrungThuong thue1 = new ThueTrungThuong();
        ThueTrungThuong thue2 = new ThueTrungThuong();
        when(thueTrungThuongRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueTrungThuong> result = thueTrungThuongService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueTrungThuongTest() {
        ThueTrungThuong thue = new ThueTrungThuong();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueTrungThuongRepo.save(any(ThueTrungThuong.class))).thenReturn(thue);

        ThueTrungThuong result = thueTrungThuongService.saveThueTrungThuong(thue);

        assertEquals(thue, result);
    }
}