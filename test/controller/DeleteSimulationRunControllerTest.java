/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
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
public class DeleteSimulationRunControllerTest {
    
    public DeleteSimulationRunControllerTest() {
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
     * Test of getRunList method, of class DeleteSimulationRunController.
     */
    @Test
    public void testFullController() {
        System.out.println("fullControllerDeleteSimulationRunController");
        DeleteSimulationRunController instance = null;
        List<String> expResult = null;
        List<String> result = instance.getRunList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
