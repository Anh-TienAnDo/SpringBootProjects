package com.sqa.project_sqa.service.serviceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThueChuyenNhuongBanQuyenServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void test_tax_license_not_greater_than_ten_milion() {
        BigDecimal gift_value = null;
        ThueChuyenNhuongBanQuyenServiceImpl service = new ThueChuyenNhuongBanQuyenServiceImpl();
        BigDecimal taxable_income = null;
        String expResult = "";
        String result = "";

        // Test case: thu nhập tính thuế <= 10.000.000
        taxable_income = new BigDecimal("10000000");
        expResult = "0";
        result = service.Tax_license(taxable_income);
        assertEquals(expResult, result);

    }
    @Test
    void test_tax_license_greater_than_ten_milion() {
        BigDecimal gift_value = null;
        ThueChuyenNhuongBanQuyenServiceImpl service = new ThueChuyenNhuongBanQuyenServiceImpl();
        BigDecimal taxable_income = null;
        String expResult = "";
        String result = "";


        // Test case: thu nhập tính thuế > 10.000.000
        taxable_income = new BigDecimal("10000001");
        expResult = "500000";
        result = service.Tax_license(taxable_income);
        assertEquals(expResult, result);
    }

    @Test
    void test_tax_license_is_greatest() {
        BigDecimal gift_value = null;
        ThueChuyenNhuongBanQuyenServiceImpl service = new ThueChuyenNhuongBanQuyenServiceImpl();
        BigDecimal taxable_income = null;
        String expResult = "";
        String result = "";


        // Test case: thu nhập tính thuế lớn nhất có thể
        taxable_income = new BigDecimal("1000000000000");
        expResult = "50000000000";
        result = service.Tax_license(taxable_income);
        assertEquals(expResult, result);
    }
}