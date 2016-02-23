/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbraga
 */
public class SQLDataBundleTest {
    
    public SQLDataBundleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addData method, of class SQLDataBundle.
     */
    @Test
    public void testAddGetData() {
        System.out.println("addData");
        SQLData data = new SQLData(50,Integer.class);
        SQLDataBundle instance = new SQLDataBundle();
        boolean expResult = true;
        boolean result = instance.addData(data);
        assertEquals(expResult, result);
        
        int value = Integer.class.cast(instance.getData(0).getValue());
        assertEquals(50,value);
    }

    /**
     * Test of size method, of class SQLDataBundle.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SQLDataBundle instance = new SQLDataBundle();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
}
