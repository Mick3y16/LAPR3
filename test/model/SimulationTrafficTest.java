package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class SimulationTrafficTest {

    public SimulationTrafficTest() {
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
     * Test of addTraffic method, of class SimulationTraffic.
     */
    @Test
    public void testAddTraffic() {
        System.out.println("addTraffic");
        Traffic traffic = new Traffic();
        Junction beginNode = new Junction();
        beginNode.setName("beginNode");
        Junction endNode = new Junction();
        endNode.setName("endNode");
        traffic.setBeginNode(beginNode);
        traffic.setEndNode(endNode);
        Vehicle vehicle = new VehicleCombustion();
        vehicle.setName("name");
        traffic.setVehicle(vehicle);
        traffic.setArrivalRate(20);
        SimulationTraffic instance = new SimulationTraffic();

        boolean result = instance.addTraffic(traffic);
        assertTrue("", result == true);

        result = instance.addTraffic(traffic);
        assertFalse("", result == false);
    }

    /**
     * Test of removeTraffic method, of class SimulationTraffic.
     */
    @Test
    public void testRemoveTraffic() {
        System.out.println("removeTraffic");
        Traffic traffic = new Traffic();
        Junction beginNode = new Junction();
        beginNode.setName("beginNode");
        Junction endNode = new Junction();
        endNode.setName("endNode");
        traffic.setBeginNode(beginNode);
        traffic.setEndNode(endNode);
        Vehicle vehicle = new VehicleCombustion();
        vehicle.setName("name");
        traffic.setVehicle(vehicle);
        traffic.setArrivalRate(20);
        SimulationTraffic instance = new SimulationTraffic();

        instance.addTraffic(traffic);
        boolean expResult = instance.removeTraffic(traffic);

        assertTrue("", expResult);

        expResult = instance.removeTraffic(traffic);
        assertFalse("", expResult);
    }

    /**
     * Test of iterator method, of class SimulationTraffic.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        SimulationTraffic instance = new SimulationTraffic();
        Traffic traffic = new Traffic();
        Junction beginNode = new Junction();
        beginNode.setName("beginNode");
        Junction endNode = new Junction();
        endNode.setName("endNode");
        traffic.setBeginNode(beginNode);
        traffic.setEndNode(endNode);
        Vehicle vehicle = new VehicleCombustion();
        vehicle.setName("name");
        traffic.setVehicle(vehicle);
        traffic.setArrivalRate(20);
        boolean add = instance.addTraffic(traffic);
        Iterator<Traffic> iterator = instance.iterator();
        assertTrue("The objects should be the same", iterator.next().equals(traffic));

        assertFalse("There should be no more segments", iterator.hasNext());
    }

    /**
     * Test of toString method, of class SimulationTraffic.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SimulationTraffic instance = new SimulationTraffic();

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

        instance.addTraffic(traffic);

        String expResult = "List of Traffic:\n"
                + "\tBegin node: junction1\n"
                + "\tEnd node: junction2\n"
                + "\tVehicle: vehicle\n"
                + "\tArrival Rate: 2\n"
                + "\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class SimulationTraffic.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        SimulationTraffic instance = new SimulationTraffic();
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

        instance.addTraffic(traffic);

        String expResult = "List of Traffic:<ul>\n"
                + "\t<li>Begin node: junction1</li>\n"
                + "\t<li>End node: junction2</li>\n"
                + "\t<li>Vehicle: vehicle</li>\n"
                + "\t<li>Arrival Rate: 2</li>\n"
                + "</ul>";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of newTraffic method, of class SimulationTraffic.
     */
    @Test
    public void testNewTraffic() {
        System.out.println("newTraffic");
        SimulationTraffic instance = new SimulationTraffic();

        Traffic result = instance.newTraffic();
        assertEquals(result.getClass(),Traffic.class);
    }

    /**
     * Test of validate method, of class SimulationTraffic.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        SimulationTraffic instance = new SimulationTraffic();
        boolean result = false;
        try {
            result = instance.validate();
            assertFalse("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.addTraffic(new Traffic());
        result = instance.validate();
        assertTrue("", result);
    }

    /**
     * Test of getIndexOf method, of class SimulationTraffic.
     */
    @Test
    public void testGetIndexOf() {
        System.out.println("getIndexOf");
        SimulationTraffic instance = new SimulationTraffic();
        Traffic t = instance.newTraffic();
        instance.addTraffic(t);
        instance.addTraffic(instance.newTraffic());
        int expResult = 0;
        int result = instance.getIndexOf(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class SimulationTraffic.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        SimulationTraffic instance = new SimulationTraffic();
        Traffic expResult = instance.newTraffic();
        instance.addTraffic(expResult);
        Traffic result = instance.get(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class SimulationTraffic.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SimulationTraffic instance = new SimulationTraffic();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        instance.addTraffic(instance.newTraffic());
        expResult = 1;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllVehicles method, of class SimulationTraffic.
     */
    @Test
    public void testGetAllVehicles() {
        System.out.println("getAllVehicles");
        SimulationTraffic instance = new SimulationTraffic();
        Vehicle a = new VehicleCombustion();
        Vehicle b = new VehicleElectric();
        a.setName("Vehicle A");
        b.setName("Vehicle B");
        Traffic t = instance.newTraffic();
        t.setVehicle(a);
        instance.addTraffic(t);
        
        t = instance.newTraffic();
        t.setVehicle(a);
        instance.addTraffic(t);
        
        t = instance.newTraffic();
        t.setVehicle(b);
        instance.addTraffic(t);
        
        List<String> expResult = new ArrayList();
        expResult.add("Vehicle A");
        expResult.add("Vehicle B");
        List<String> result = instance.getAllVehicles();
        assertEquals(expResult, result);
    }

}
