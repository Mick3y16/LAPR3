/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class SegmentRunResultTest {
    
    public SegmentRunResultTest() {
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
     * Test of getInstantIn method, of class SegmentRunResult.
     */
    @Test
    public void testGetSetInstantIn() {
        System.out.println("getInstantIn");
        SegmentRunResult instance = new SegmentRunResult();
        instance.setInstantIn(31);
        int expResult = 31;
        int result = instance.getInstantIn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstantOut method, of class SegmentRunResult.
     */
    @Test
    public void testGetSetInstantOut() {
        System.out.println("getInstantOut");
        SegmentRunResult instance = new SegmentRunResult();
        instance.setInstantOut(80);
        int expResult = 80;
        int result = instance.getInstantOut();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergySpend method, of class SegmentRunResult.
     */
    @Test
    public void testGeSettEnergySpend() {
        System.out.println("getEnergySpend");
        SegmentRunResult instance = new SegmentRunResult();
        instance.setEnergySpend(80213);
        double expResult = 80213;
        double result = instance.getEnergySpend();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getElapsedTime method, of class SegmentRunResult.
     */
    @Test
    public void testGetElapsedTime() {
        System.out.println("getElapsedTime");
        SegmentRunResult instance = new SegmentRunResult();
        int expResult = 20;
        instance.setInstantIn(30);
        instance.setInstantOut(50);
        int result = instance.getElapsedTime();
        assertEquals(expResult, result);
    }
    
}
