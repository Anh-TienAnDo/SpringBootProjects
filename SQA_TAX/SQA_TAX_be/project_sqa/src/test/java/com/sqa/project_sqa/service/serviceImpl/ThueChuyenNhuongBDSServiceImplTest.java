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
public class ThueChuyenNhuongBDSServiceImplTest {
	BigDecimal transfer_price = null;
    ThueChuyenNhuongBDSServiceImpl instance = new ThueChuyenNhuongBDSServiceImpl();
    BigDecimal taxable_income = null;
    String expResult = "";
    String result = "";
    
    public ThueChuyenNhuongBDSServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * Test of saveThueChuyenNhuongBDS method, of class ThueChuyenNhuongBDSServiceImpl.
     */
    
    @Test
    public void testTax_land_transfer() {
        System.out.println("Tax_land_transfer");
        
        // Test case: thu nhập tính thuế <= 0
        taxable_income = new BigDecimal("0");
        expResult = "0";
        result = instance.Tax_land_transfer(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế = 1000
        taxable_income = new BigDecimal("1000");
        expResult = "20";
        result = instance.Tax_land_transfer(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế giá trị cao nhất có thể: 1.000.000.000.000.000
        taxable_income = new BigDecimal("1000000000000000");
        expResult = "20000000000000";
        result = instance.Tax_land_transfer(taxable_income);
        assertEquals(expResult, result);
    }


    
}
