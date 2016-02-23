/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.SectionResults;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Tuple;

/**
 *
 * @author Diogo
 */
public class PerformNetworkStaticAnalysisControllerTest
{
    
    public PerformNetworkStaticAnalysisControllerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getVehicleList method, of class PerformNetworkStaticAnalysisController.
     */
    @Test
    public void testFullController()
    {
        System.out.println("fullControllerPerformNetworkStaticAnalysis");
        PerformNetworkStaticAnalysisController instance = new PerformNetworkStaticAnalysisController(null);
        List<String> expResult = null;
        List<String> result = instance.getVehicleList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype. Awaiting network analysis test cases to be written.");
    }
    
}
