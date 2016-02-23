package controller;

import model.Simulator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class CopySimulationControllerTest {

    Simulator simulator;
    CopySimulationController instance;

    public CopySimulationControllerTest() {
    }

    @Before
    public void setUp() {
        this.simulator = new Simulator();
        this.instance = new CopySimulationController(this.simulator);
    }

    /**
     * Test of registerCopy method, of class CopySimulationController.
     */
    @Test
    public void testRegisterCopy() {
        System.out.println("registerCopy");
        boolean expResult = true;
        boolean result = instance.registerCopy();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class CopySimulationController.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name";
        
        try {
            instance.setName(" ");
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }
        instance.setName(name);
    }

    /**
     * Test of setDescription method, of class CopySimulationController.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "description";
         
        try {
            instance.setDescription(" ");
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }
        instance.setDescription(description);
    }

}
