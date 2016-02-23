package model;

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
public class SimulationTest {

    private Project openProject;

    private Project p;

    public SimulationTest() {
        this.openProject = new Project();
        this.p = null;
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
     * Test of setAndGetName method, of class Simulation.
     */
    @Test
    public void testSetAndGetName() {
        System.out.println("setAndGetName ");

        Simulation instance;
        try {
            instance = new Simulation(p);
        } catch (IllegalArgumentException e) {
        }

        instance = new Simulation(openProject);
        String name = " ";

        try {
            instance.setName(name);
            assertTrue("An exception should have been thrown", false);

        } catch (IllegalArgumentException ex) {
        }

        name = "name";
        instance.setName(name);

        assertTrue("", (instance.getName()).equals(name));
    }

    /**
     * Test of setAndGetDescription method, of class Simulation.
     */
    @Test
    public void testSetAndGetDescription() {
        System.out.println("setAndGetDescription");
        Simulation instance = new Simulation(this.openProject);
        String description = " ";

        try {
            instance.setDescription(description);
            assertTrue("An exception should have been thrown", false);

        } catch (IllegalArgumentException ex) {
        }

        description = "description";
        instance.setDescription(description);

        assertTrue("", (instance.getDescription()).equals(description));
    }

    /**
     * Test of setAndGetProject method, of class Simulation.
     */
    @Test
    public void testSetAndGetOpenProject() {
        System.out.println("setAndGetProject");

        Simulation instance;
        try {

            instance = new Simulation(p);
        } catch (IllegalArgumentException e) {
        }

        instance = new Simulation(this.openProject);
        Project p1 = new Project();
        instance.setOpenProject(p1);

        assertTrue("", instance.getOpenProject() == p1);
    }

    /**
     * Test of validatesSimulation method, of class Simulation.
     */
    @Test
    public void testValidatesSimulation() {
        System.out.println("validatesSimulation");
        Simulation instance;

        try {
            instance = new Simulation(p);
        } catch (IllegalArgumentException ex) {
        }

        instance = new Simulation(openProject);

        boolean result = false;
        try {
            result = instance.validatesSimulation();
            assertFalse("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDescription("description");

        try {
            result = instance.validatesSimulation();
            assertFalse("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.setName("name");

        result = instance.validatesSimulation();

        assertTrue("", result);
    }

    /**
     * Test of toString method, of class Simulation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Simulation instance = new Simulation(this.openProject);
        instance.setName("name");
        instance.setDescription("description");
        Traffic traffic = new Traffic();
        Junction j1 = new Junction();
        j1.setName("junction1");
        Junction j2 = new Junction();
        j2.setName("junction2");
        Vehicle v = new VehicleCombustion();
        v.setName("vehicle");
        traffic.setBeginNode(j1);
        traffic.setEndNode(j2);
        traffic.setVehicle(v);
        traffic.setArrivalRate(2);
        SimulationTraffic st = instance.getSimulationTraffic();
        st.addTraffic(traffic);
        
        String expResult = "Name: name\n"
                + "Description: description\n"
                + "List of Traffic:\n"
                + "\tBegin node: junction1\n"
                + "\tEnd node: junction2\n"
                + "\tVehicle: vehicle\n"
                + "\tArrival Rate: 2\n\n";
        
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
        /**
     * Test of toStringHTML method, of class Simulation.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Simulation instance = new Simulation(this.openProject);
        instance.setName("name");
        instance.setDescription("description");
        Traffic traffic = new Traffic();
        Junction j1 = new Junction();
        j1.setName("junction1");
        Junction j2 = new Junction();
        j2.setName("junction2");
        Vehicle v = new VehicleCombustion();
        v.setName("vehicle");
        traffic.setBeginNode(j1);
        traffic.setEndNode(j2);
        traffic.setVehicle(v);
        traffic.setArrivalRate(2);
        SimulationTraffic st = instance.getSimulationTraffic();
        st.addTraffic(traffic);
        
        String expResult = "Name: name<br/>\n"
                + "Description: description<br/>\n"
                + "List of Traffic:<ul>\n"
                + "\t<li>Begin node: junction1</li>\n"
                + "\t<li>End node: junction2</li>\n"
                + "\t<li>Vehicle: vehicle</li>\n"
                + "\t<li>Arrival Rate: 2</li>\n"
                + "</ul>";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRunList method, of class Simulation.
     */
    @Test
    public void testGetRunList() {
        System.out.println("getRunList");
        Simulation instance = new Simulation(new Project());
        SimulationRunList expResult = new SimulationRunList();
        SimulationRunList result = instance.getRunList();
        assertEquals(expResult, result);
    }
}
