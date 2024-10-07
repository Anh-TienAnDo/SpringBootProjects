/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sqa.project_sqa.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
	
/**
 *
 * @author Admin
 */
public class ThueDauTuVonServiceImplTest {
	ThueDauTuVonServiceImpl instance = new ThueDauTuVonServiceImpl();
    BigDecimal taxable_income = null;
    String expResult = "";
    String result = "";
    
    public ThueDauTuVonServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testTax_capital_investments() {
        System.out.println("Tax_capital_investments");

        // Test case: thu nhập tính thuế <= 0
        taxable_income = new BigDecimal("0");
        expResult = "0";
        result = instance.Tax_capital_investments(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế = 1.000
        taxable_income = new BigDecimal("1000");
        expResult = "50";
        result = instance.Tax_capital_investments(taxable_income);
        assertEquals(expResult, result);
        
        // Test case: thu nhập tính thuế giá trị cao nhất có thể: 1.000.000.000.000
        taxable_income = new BigDecimal("1000000000000");
        expResult = "50000000000";
        result = instance.Tax_capital_investments(taxable_income);
        assertEquals(expResult, result);
    }

    
}
