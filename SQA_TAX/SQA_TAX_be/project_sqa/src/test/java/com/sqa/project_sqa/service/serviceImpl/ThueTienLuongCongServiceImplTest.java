/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sqa.project_sqa.service.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;


import java.math.BigDecimal;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class ThueTienLuongCongServiceImplTest {
	ThueTienLuongCongServiceImpl instance = new ThueTienLuongCongServiceImpl();
    BigDecimal taxable_income = null;
    BigDecimal deductible = new BigDecimal("11000000");
    String expResult = "";
    String result = "";
    
    public ThueTienLuongCongServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    


    @Test
    public void testTax_type1() {
        System.out.println("Tax_type1");
        
        // Test case: thu nhập tính thuế <= 0( thu nhập chịu thuế là 10.999.000
        taxable_income = new BigDecimal("10999000");
        expResult = "0";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế > 0( thu nhập chịu thuế là 11.001.000
        taxable_income = new BigDecimal("11001000");
        expResult = "50";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế = 12.000.000, khấu trừ là 11.000.000
        taxable_income = new BigDecimal("12000000");
        expResult = "50000";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);

        // Test case: thu nhập tính thuế nằm trong bậc 2: thu nhập tính thuế > 5.000.000
        taxable_income = new BigDecimal("16001000");
        expResult = "250100";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);

        // Test case: thu nhập tính thuế nằm bậc 4 =32.000.000
        taxable_income = new BigDecimal("43000000");
        expResult = "4750000";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);

        // Test case: thu nhập tính thuế nằm trong khung cao nhất 200.000.000
        taxable_income = new BigDecimal("200000000");
        expResult = "56300000";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế có 30 số
        taxable_income = new BigDecimal("200000000000000000000011000000");
        expResult = "69999999999999999999990150000";
        result = instance.Tax_type1(taxable_income, deductible);
        assertEquals(expResult, result);

    }

 // Thuế thu nhập đối với cá nhân không ký hợp đồng lao động hoặc ký hợp đồng lao động dưới 03 tháng
    @Test
    public void testTax_type2() {
        System.out.println("Tax_type2");
        
        // Test case: thu nhập tính thuế < 2.000.000
        taxable_income = new BigDecimal("1999000");
        expResult = "0";
        result = instance.Tax_type2(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế >= 2.000.000
        taxable_income = new BigDecimal("2000000");
        expResult = "200000";
        result = instance.Tax_type2(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế giá trị cao: 1.000.000.000.000
        taxable_income = new BigDecimal("1000000000000");
        expResult = "100000000000";
        result = instance.Tax_type2(taxable_income);
        assertEquals(expResult, result);
    }

    // thuế thu nhập đối với cá nhân không cư trú
    @Test
    public void testTax_type3() {
        System.out.println("Tax_type3");
        
        // Test case: thu nhập tính thuế <= 0
        taxable_income = new BigDecimal("0");
        expResult = "0";
        result = instance.Tax_type3(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế giá trị là 1.000
        taxable_income = new BigDecimal("1000");
        expResult = "100";
        result = instance.Tax_type3(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế giá trị cao nhất có thể
        taxable_income = new BigDecimal("10000000000000000000000000000");
        expResult = "1000000000000000000000000000";
        result = instance.Tax_type3(taxable_income);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeductible_cac_tham_so_bang_0() {
    	long ban_than = 0;
    	long nguoi_phu_thuoc = 0;
    	long tu_thien = 0;
    	long bao_hiem = 0;
        expResult = "0";
        result = instance.Deductible(ban_than, nguoi_phu_thuoc, tu_thien, bao_hiem);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDeductible_co_1_tham_so_bang_0() {
    	long ban_than = 11000000;
    	long nguoi_phu_thuoc = 4400000;
    	long tu_thien = 0;
    	long bao_hiem = 1000000;
        expResult = "16400000";
        result = instance.Deductible(ban_than, nguoi_phu_thuoc, tu_thien, bao_hiem);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDeductible_so_cuc_lon() {
    	long ban_than = 11000000;
    	long nguoi_phu_thuoc = 0;
    	long tu_thien = Long.valueOf("1000000000000000000"); // 1.000.000.000.000.000.000
    	long bao_hiem = 0;
        expResult = "1000000000011000000";
        result = instance.Deductible(ban_than, nguoi_phu_thuoc, tu_thien, bao_hiem);
        assertEquals(expResult, result);
    }
    
}
