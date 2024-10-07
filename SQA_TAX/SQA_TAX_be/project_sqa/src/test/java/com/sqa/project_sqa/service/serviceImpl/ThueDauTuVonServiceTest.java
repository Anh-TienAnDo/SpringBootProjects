package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueDauTuVon;
import com.sqa.project_sqa.repositories.ThueDauTuVonRepo;
import com.sqa.project_sqa.service.serviceImpl.ThueDauTuVonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ThueDauTuVonServiceTest {
    @InjectMocks
    ThueDauTuVonServiceImpl thueDauTuVonService;

    @Mock
    ThueDauTuVonRepo thueDauTuVonRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueDauTuVon thue1 = new ThueDauTuVon();
        ThueDauTuVon thue2 = new ThueDauTuVon();
        when(thueDauTuVonRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueDauTuVon> result = thueDauTuVonService.getAll();

        assertEquals(2, result.size());
        verify(thueDauTuVonRepo, times(1)).findAll();
    }

    @Test
    public void saveThueDauTuVonTest() {
        ThueDauTuVon thue = new ThueDauTuVon();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueDauTuVonRepo.save(any(ThueDauTuVon.class))).thenReturn(thue);

        ThueDauTuVon result = thueDauTuVonService.saveThueDauTuVon(thue);

        assertEquals(thue, result);
        verify(thueDauTuVonRepo, times(1)).save(thue);
    }
}