/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
public class SimulationRunListTest {
    
    public SimulationRunListTest() {
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
     * Test of newRun method, of class SimulationRunList.
     */
    @Test
    public void testNewRun() {
        System.out.println("newRun");
        SimulationRunList instance = new SimulationRunList();
        SimulationRun result = instance.newRun();
        assertTrue("Should not be null", result!=null);
    }

    /**
     * Test of getAllRuns method, of class SimulationRunList.
     */
    @Test
    public void testGetAllRuns() {
        System.out.println("getAllRuns");
        SimulationRunList instance = new SimulationRunList();
        instance.addRun(new SimulationRun("Green Hill Zone - Act 1",null));
        instance.addRun(new SimulationRun("Green Hill Zone - Act 2",null));
        List<String> expResult = new ArrayList();
        expResult.add("Green Hill Zone - Act 1");
        expResult.add("Green Hill Zone - Act 2");
        List<String> result = instance.getAllRuns();
        assertEquals(expResult, result);
    }

    /**
     * Test of addRun method, of class SimulationRunList.
     */
    @Test
    public void testAddRun() {
        System.out.println("addRun");
        SimulationRun sR = new SimulationRun("Green Hill Zone - Act 1",null);
        SimulationRunList instance = new SimulationRunList();
        boolean expResult = true;
        boolean result = instance.addRun(sR);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteRun method, of class SimulationRunList.
     */
    @Test
    public void testDeleteRun_SimulationRun() {
        System.out.println("deleteRun");
        SimulationRun sR = new SimulationRun("ABC",null);
        SimulationRunList instance = new SimulationRunList();
        instance.addRun(sR);
        sR = new SimulationRun("ABD",null);
        instance.addRun(sR);
        boolean expResult = true;
        boolean result = instance.deleteRun(sR);
        assertEquals(expResult, result);
        assertEquals(1,instance.size());
    }

    /**
     * Test of deleteRun method, of class SimulationRunList.
     */
    @Test
    public void testDeleteRun_String() {
        System.out.println("deleteRun");
        String srName = "ABD";
        SimulationRun sR = new SimulationRun("ABC",null);
        SimulationRunList instance = new SimulationRunList();
        instance.addRun(sR);
        sR = new SimulationRun("ABD",null);
        instance.addRun(sR);
        boolean expResult = true;
        boolean result = instance.deleteRun(srName);
        assertEquals(expResult, result);
        assertEquals(1,instance.size());
    }

    /**
     * Test of get method, of class SimulationRunList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        SimulationRunList instance = new SimulationRunList();
        
        SimulationRun sR = new SimulationRun("ABC",null);
        instance.addRun(sR);
        sR = new SimulationRun("ABD",null);
        instance.addRun(sR);
        
        SimulationRun expResult = sR;
        SimulationRun result = instance.get(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class SimulationRunList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SimulationRunList instance = new SimulationRunList();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class SimulationRunList.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other = null;
        SimulationRunList instance = new SimulationRunList();
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class SimulationRunList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SimulationRunList instance = new SimulationRunList();
        String expResult = "[]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
