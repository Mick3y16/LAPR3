package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author G11
 */
public class TrafficTest {

    public TrafficTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getBeginNode method and setBeginNode method, of class Traffic.
     */
    @Test
    public void testGetSetBeginNode() {
        System.out.println("getSetBeginNode");
        Traffic instance = new Traffic();

        Junction result = instance.getBeginNode();
        Junction expResult = null;

        assertTrue("", instance.getBeginNode() == expResult);

        try {
            instance.setBeginNode(expResult);

        } catch (IllegalArgumentException ex) {
        }

        result = new Junction();

        instance.setBeginNode(result);
        assertTrue("", instance.getBeginNode() == result);
    }

    /**
     * Test of getEndNode method and setEndNode method, of class Traffic.
     */
    @Test
    public void testGetSetEndNode() {
        System.out.println("getSetEndNode");
        Traffic instance = new Traffic();

        Junction result = instance.getEndNode();
        Junction expResult = null;

        assertTrue("", instance.getEndNode() == expResult);

        try {
            instance.setEndNode(expResult);

        } catch (IllegalArgumentException ex) {
        }

        result = new Junction();

        instance.setEndNode(result);
        assertTrue("", instance.getEndNode() == result);
    }

    /**
     * Test of getVehicle method and setVehicle method, of class Traffic.
     */
    @Test
    public void testGetSetVehicle() {
        System.out.println("getSetVehicle");
        Traffic instance = new Traffic();
        Vehicle result = instance.getVehicle();
        Vehicle expResult = null;

        assertTrue("", instance.getVehicle() == expResult);

        try {
            instance.setVehicle(expResult);

        } catch (IllegalArgumentException ex) {
        }

        result = new VehicleCombustion();

        instance.setVehicle(result);
        assertTrue("", instance.getVehicle() == result);
    }

    /**
     * Test of getArrivalRate method and SetArrival_rate method, of class
     * Traffic.
     */
    @Test
    public void testGetSetArrival_rate() {
        System.out.println("getSetArrival_rate");
        Traffic instance = new Traffic();

        int arrivalRate = instance.getArrivalRate();

        assertTrue("", arrivalRate == 0);
        try {
            instance.setArrivalRate(-1);
            assertTrue("An exception should have been thrown", false);

        } catch (IllegalArgumentException ex) {
        }

        arrivalRate = 20;
        instance.setArrivalRate(arrivalRate);

        assertTrue("", instance.getArrivalRate() == arrivalRate);
    }

    /**
     * Test of validate method, of class Traffic.
     */
    @Test
    public void testValidateTraffic() {
        System.out.println("validateTraffic");
        Traffic instance = new Traffic();

        try {
            instance.validate();
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        Vehicle v = new VehicleCombustion();
        instance.setVehicle(v);

        try {
            instance.validate();
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        Junction j = new Junction();
        j = new Junction();
        j.setName("junction");
        instance.setBeginNode(j);

        try {
            instance.validate();
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setEndNode(j);

        try {
            instance.validate();
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setEndNode(new Junction());

        try {
            instance.validate();
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }
        
        instance.setArrivalRate(2);

        assertTrue("", instance.validate() == true);
    }

    /**
     * Test of toString method, of class Traffic.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Traffic instance = new Traffic();

        Junction j1 = new Junction();
        j1.setName("junction1");
        Junction j2 = new Junction();
        j2.setName("junction2");

        Vehicle v = new VehicleCombustion();
        v.setName("vehicle");

        instance.setBeginNode(j1);
        instance.setEndNode(j2);
        instance.setVehicle(v);
        instance.setArrivalRate(2);

        String expResult = "\tBegin node: junction1\n"
                + "\tEnd node: junction2\n"
                + "\tVehicle: vehicle\n"
                + "\tArrival Rate: 2\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class Traffic.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Traffic instance = new Traffic();

        Junction j1 = new Junction();
        j1.setName("junction1");
        Junction j2 = new Junction();
        j2.setName("junction2");

        Vehicle v = new VehicleCombustion();
        v.setName("vehicle");

        instance.setBeginNode(j1);
        instance.setEndNode(j2);
        instance.setVehicle(v);
        instance.setArrivalRate(2);

        String expResult = "\t<li>Begin node: junction1</li>\n"
                + "\t<li>End node: junction2</li>\n"
                + "\t<li>Vehicle: vehicle</li>\n"
                + "\t<li>Arrival Rate: 2</li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

}
