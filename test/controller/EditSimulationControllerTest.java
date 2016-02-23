package controller;

import model.Project;
import model.Simulation;
import model.Simulator;
import model.dao.DaoManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class EditSimulationControllerTest {

    private Simulator simulator;
    private Simulation s;
    private Project p;

    public EditSimulationControllerTest() {
        this.simulator = new Simulator();
        p = new Project();
        p.setName("projA");
        
        s = new Simulation(p);
        s.setName("lapr3");
        s.setDescription("lapr3");
        this.simulator.setOpenSimulation(s);
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
     * Test of getSimulationName method, of class EditSimulationController.
     */
    @Test
    public void testFullController() {
        System.out.println("fullControllerEditSimulation");

        /*EditSimulationController instance = new EditSimulationController(this.simulator);
        String expResult = "lapr3";
        String result = instance.getSimulationName();
        assertEquals(expResult, result);*/
        fail("TO-DO");
    }
}
