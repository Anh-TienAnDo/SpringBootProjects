package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.LoaiThue;
import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.LoaiThueRepository;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class NguoiDongThueImplTest {

    @Mock
    NguoiDongThueRepository nguoiDongThueRepository;

    @InjectMocks
    NguoiDongThueServiceImpl nguoiDongThueServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidNotAcceptMst() {
        List<String> mstList = new ArrayList<>();
        mstList.add("-1");
        mstList.add("aadsdscx");
        mstList.add("aadsdscx1232");
        mstList.add("123232");
        mstList.add("123456789");
        mstList.add("12345678901");
        mstList.add("123456789012");
        mstList.add("12345678901234");
        assertTrue(nguoiDongThueServiceImpl.testNotAcceptMst(mstList));
    }

    @Test
    public void testValidAcceptMst() {
        List<String> mstList = new ArrayList<>();
        mstList.add("1234567890");
        mstList.add("123456789012");
        assertTrue(nguoiDongThueServiceImpl.testAcceptMst(mstList));
    }

}