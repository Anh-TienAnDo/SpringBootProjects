package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.ThueQuaTang;
import com.sqa.project_sqa.repositories.ThueQuaTangRepo;
import com.sqa.project_sqa.service.serviceImpl.ThueQuaTangServiceImpl;
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

public class ThueQuaTangServiceTest {
    @InjectMocks    
    ThueQuaTangServiceImpl thueQuaTangService;

    @Mock
    ThueQuaTangRepo thueQuaTangRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTest() {
        ThueQuaTang thue1 = new ThueQuaTang();
        ThueQuaTang thue2 = new ThueQuaTang();
        when(thueQuaTangRepo.findAll()).thenReturn(Arrays.asList(thue1, thue2));

        List<ThueQuaTang> result = thueQuaTangService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    public void saveThueQuaTangTest() {
        ThueQuaTang thue = new ThueQuaTang();
        thue.setId(1);
        thue.setNoiDung("abcdef");
        thue.setMst("1234567891");
        thue.setThuNhapChiuThue(1000000L);
        thue.setTongThuePhaiNop(500000L);
        // giả sử thành công
        when(thueQuaTangRepo.save(any(ThueQuaTang.class))).thenReturn(thue);

        ThueQuaTang result = thueQuaTangService.saveThueQuaTang(thue);

        assertEquals(thue, result);
    }
}
