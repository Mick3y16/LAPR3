/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Simulator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class OpenSimulationControllerTest {
    
    public OpenSimulationControllerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getSimulationList method, of class OpenSimulationController.
     */
    @Test
    public void testGetSimulationList() {
        System.out.println("getSimulationList");
        Project openProject = new Project();
        OpenSimulationController instance = new OpenSimulationController(new Simulator());
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.getSimulationList();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectSimulation method, of class OpenSimulationController.
     */
    @Test
    public void testSelectSimulation() {
        System.out.println("selectSimulation");
        String simulationName = "";
        OpenSimulationController instance = new OpenSimulationController(new Simulator());
        instance.selectSimulation(simulationName);
    }
    
}
