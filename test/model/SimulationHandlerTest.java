package model;

import java.util.List;
import model.dao.DaoManager;
import model.dao.SimulationDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author G11
 */
public class SimulationHandlerTest {

    public SimulationHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getSimulationList method, of class SimulationHandler.
     */
    @Test
    public void testGetSimulationList() {
        System.out.println("getSimulationList");
        SimulationHandler instance = new SimulationHandler();
        Project openProj = new Project();
        openProj.setName("TestSet03");

        /**
        // fail("failed: the database is too unstable to test");
        assertTrue("should be not null!", instance.getSimulationList(openProj) != null);

        assertTrue("should be SimulationA.", instance.getSimulationList(openProj).get(0).equalsIgnoreCase("name"));
        assertTrue("should be SimulationB.", instance.getSimulationList(openProj).get(1).equalsIgnoreCase("name1"));
        assertTrue("should be SimulationC.", instance.getSimulationList(openProj).get(2).equalsIgnoreCase("name6"));*/
        List<String> result = instance.getSimulationList(openProj);
        assertTrue("The list of simulation should not be empty",!result.isEmpty());

    }

    /**
     * Test of deleteSimulation method, of class SimulationHandler.
     */
    @Test
    public void testDeleteSimulation() {
        System.out.println("deleteSimulation");
        SimulationHandler instance = new SimulationHandler();
        
        Project openProj = new Project();
        openProj.setName("TestSet03");
        
        String name = "testDeleteSimulation";
        
        Simulation sim = new Simulation(openProj);
        sim.setName(name);
        sim.setDescription("Random description.");
        
        DaoManager.getInstance().getSimulationDAO().insertSimulation(sim);
        
        assertFalse("No simulation exists with this name, therefore it shouldn't"
                + "have been able to delete it.", instance.deleteSimulation("SimulationD"));
        assertTrue("Should be true", instance.deleteSimulation(name) == true);
    }

    /**
     * Test of newSimulation method, of class SimulationHandler.
     */
    @Test
    public void testNewSimulation() {
        System.out.println("newSimulation");
        Project openProject = new Project();
        SimulationHandler instance = new SimulationHandler();
        Simulation expResult = new Simulation(openProject);
        Simulation result = instance.newSimulation(openProject);
        assertEquals(expResult.getClass().toString(), result.getClass().toString());
    }

    /**
     * Test of getSimulationDAO method, of class SimulationHandler.
     */
    @Test
    public void testGetSimulationDAO() {
        System.out.println("getSimulationDAO");
        SimulationHandler instance = new SimulationHandler();
        SimulationDAO simulationDAO = new SimulationDAO();
        String expResult = simulationDAO.getClass().toString();
        String result = instance.getSimulationDAO().getClass().toString();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of addSimulation method, of class SimulationHandler.
     */
    @Test
    public void testAddSimulation() {
        System.out.println("addSimulation");
        Project proj = new Project();
        proj.setName("TestSet03");
        Simulation simulation = new Simulation(proj);
        String simName = "testAddSimulation";
        simulation.setName(simName);
        simulation.setDescription("descriptionTest");
        SimulationHandler instance = new SimulationHandler();
        boolean expResult = true;
        boolean result = instance.addSimulation(simulation);
        assertEquals(expResult, result);
        
        DaoManager.getInstance().getSimulationDAO().deleteSimulation(simName);
    }

    /**
     * Test of registerChanges method, of class SimulationHandler.
     */
    @Test
    public void testRegisterChanges() {
        System.out.println("registerChanges");
        Project proj = new Project();
        proj.setName("TestSet03");
        Simulation simulation = new Simulation(proj);
        String simName = "testRegisterChanges";
        String originName = "testRegisterChangesOrigin";
        
        simulation.setName(originName);
        simulation.setDescription("description");
        
        DaoManager.getInstance().getSimulationDAO().insertSimulation(simulation);
        simulation.setName(simName);
        SimulationHandler instance = new SimulationHandler();
        boolean expResult = true;
        boolean result = instance.registerChanges(simulation, originName);
        assertEquals(expResult, result);
        
        assertTrue("Simulation should have been deleted",
                DaoManager.getInstance().getSimulationDAO().deleteSimulation(simName));

    }

}
