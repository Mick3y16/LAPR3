package model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author G11
 */
public class VehicleTest {

    public VehicleTest() {
    }

    /**
     * Inner class used to test the methods of the abstract class.
     */
    public class VehicleExtender extends Vehicle {

        /**
         * Returns the maximum velocity of a vehicle.
         *
         * @return The maximum velocity of a vehicle.
         */
        @Override
        public double getMaximumVelocity() {
            return 20.0;
        }

        @Override
        public double getMaximumVelocityInGear(int gearIndex) {
            return 10;
        }

        @Override
        public double getFinalDrive() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Gearbox getGearbox() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public AcceleratorPedal getAcceleratorPedal() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getRpmMinimum() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
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
     * Test of setAndGetName methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetName() {
        System.out.println("setAndGetName");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setName(null);
            assertTrue("An exception should have been thrown, the name cannot be null", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setName("");
            assertTrue("An exception should have been thrown, the name cannot be empty", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setName("Nissan Skyline");
        assertTrue("The name should be Nissan Skyline", instance.getName().equals("Nissan Skyline"));
    }

    /**
     * Test of setAndGetDescription methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetDescription() {
        System.out.println("setAndGetDescription");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setDescription(null);
            assertTrue("An exception should have been thrown, the description cannot be null", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setDescription("");
            assertTrue("An exception should have been thrown, the description cannot be empty", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDescription("Sports Car");
        assertTrue("The description should be Sports Car", instance.getDescription().equals("Sports Car"));
    }

    /**
     * Test of setAndGetType methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetType() {
        System.out.println("setAndGetType");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setType(null);
            assertTrue("An exception should have been thrown, the type cannot be null", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setType("");
            assertTrue("An exception should have been thrown, the type cannot be empty", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setType("Car");
        assertTrue("The type should be Car", instance.getType().equals("Car"));
    }

    /**
     * Test of setAndGetFuel methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetFuel() {
        System.out.println("setAndGetFuel");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setFuel(null);
            assertTrue("An exception should have been thrown, the fuel cannot be null", false);
        } catch (IllegalArgumentException ex) {
        }

        try {
            instance.setFuel("");
            assertTrue("An exception should have been thrown, the fuel cannot be empty", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setFuel("Gasoline");
        assertTrue("The fuel should be Gasoline", instance.getFuel().equals("Gasoline"));
    }

    /**
     * Test of setAndGetMass methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetMass() {
        System.out.println("setAndGetMass");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setMass(0);
            assertTrue("An exception should have been thrown, the mass cannot be 0 or lower", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setMass(1300);
        assertTrue("The mass should be 1300 kg", instance.getMass() == 1300);
    }

    /**
     * Test of setAndGetLoad method, of class VehicleCombustion.
     */
    @Test
    public void testSetAndGetLoad() {
        System.out.println("setAndGetLoad");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setLoad(-1);
            assertTrue("An exception should have been thrown, the load cannot be negative", false);

            instance.setLoad(0);
            assertFalse("The load should be 0 kg", instance.getLoad() == 0);
        } catch (IllegalArgumentException ex) {
        }
        
        instance.setLoad(1);
         assertTrue("The load should be 1 kg", instance.getLoad() == 1);
    }

    /**
     * Test of setAndGetDragCoefficient methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetDragCoefficient() {
        System.out.println("setAndGetDragCoefficient");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setDragCoefficient(-1);
            assertTrue("An exception should have been thrown, the drag coefficient cannot be negative", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDragCoefficient(0.35);
        assertTrue("The drag coefficient should be 0,35", instance.getDragCoefficient() == 0.35);
    }

    /**
     * Test of setAndGetFrontalArea methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetFrontalArea() {
        System.out.println("setAndGetFrontalArea");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setFrontalArea(-1);
            assertTrue("An exception should have been thrown, the frontal area cannot be negative", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setDragCoefficient(2.4);
        assertTrue("The drag coefficient should be 2.4m", instance.getDragCoefficient() == 2.4);
    }

    /**
     * Test of setAndGetRollingResistanceCoefficient methods, of class Vehicle.
     */
    @Test
    public void testSetAndGetRollingResistanceCoefficient() {
        System.out.println("setAndGetRollingResistanceCoefficient");
        Vehicle instance = new VehicleExtender();

        try {
            instance.setRollingResistanceCoefficient(-1);
            assertTrue("An exception should have been thrown, the rolling resistance coefficient cannot be negative", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setRollingResistanceCoefficient(0.01);
        assertTrue("The rolling resistance coefficient should be 0,01", instance.getRollingResistanceCoefficient() == 0.01);
    }

    /**
     * Test of getEnergyEfficiency method, of class Vehicle.
     */
    @Test
    public void testSetAndGetEnergyEfficiency() {
        System.out.println("getEnergyEfficiency");
        VehicleCombustion instance = new VehicleCombustion();
        Function expResult = new Function();
        instance.setEnergyEfficiency(expResult);
        Function result = instance.getEnergyEfficiency();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vehicle instance = new VehicleExtender();
        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(1.0);
        instance.setDragCoefficient(0.35);
        instance.setFrontalArea(2.4);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);

        String expResult = ""
                + "\tName: Nissan Skyline\n"
                + "\tDescription: Sports Car\n"
                + "\tType: Car\n"
                + "\tFuel: Gasoline\n"
                + "\tMass: 1300.0 Kg\n"
                + "\tLoad: 1.0 Kg\n"
                + "\tDrag Coefficient: 0.35\n"
                + "\tFrontal Area: 2.4 m\n"
                + "\tRRC: 0.01\n"
                + "\tWheel Size: 0.6 m\n"
                + "\tVelocity Limits:\n"
                + "\t\tHighway: 33.3 m/s\n"
                + "\t\tRegular Road: 25.0 m/s\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Vehicle otherObject = new VehicleExtender();
        otherObject.setName("Nissan Skyline");
        otherObject.setDescription("Sports Car");
        otherObject.setType("Car");
        otherObject.setFuel("Gasoline");
        otherObject.setMass(1300);
        otherObject.setLoad(1);
        otherObject.setDragCoefficient(0.35);
        otherObject.setRollingResistanceCoefficient(0.01);
        otherObject.setWheelSize(0.6);

        Vehicle instance = new VehicleExtender();

        assertFalse("The vehicles should be different", instance.equals(otherObject));

        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(1);
        instance.setDragCoefficient(0.35);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);

        assertTrue("The vehicles should be equal", instance.equals(otherObject));
    }

    /**
     * Test of toStringHTML method, of class Vehicle.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Vehicle instance = new VehicleExtender();
        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(1.0);
        instance.setDragCoefficient(0.35);
        instance.setFrontalArea(2.4);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);

        String expResult = ""
                + "\t<li>Name: Nissan Skyline</li>\n"
                + "\t<li>Description: Sports Car</li>\n"
                + "\t<li>Type: Car</li>\n"
                + "\t<li>Fuel: Gasoline</li>\n"
                + "\t<li>Mass: 1300.0 Kg</li>\n"
                + "\t<li>Load: 1.0 Kg</li>\n"
                + "\t<li>Drag Coefficient: 0.35</li>\n"
                + "\t<li>Frontal Area: 2.4 m</li>\n"
                + "\t<li>RRC: 0.01</li>\n"
                + "\t<li>Wheel Size: 0.6 m</li>\n"
                + "\t<li>Velocity Limits:<ul>\n"
                + "\t\t<li>Highway: 33.3 m/s</li>\n"
                + "\t\t<li>Regular Road: 25.0 m/s</li>\n"
                + "\t</ul></li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaximumVelocity method, of class Vehicle.
     */
    @Test
    public void testGetMaximumVelocity() {
        System.out.println("getMaximumVelocity");
        Vehicle instance = new VehicleExtender();

        assertTrue("The maximum velocity should be 20 m/s", instance.getMaximumVelocity() == 20.0);
    }

    /**
     * Test of insertVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testInsertVelocityLimit() {
        System.out.println("insertVelocityLimit");
        Vehicle instance = new VehicleExtender();

        instance.insertVelocityLimit("Highway", 33.3);

        assertTrue("The velocity limit should be 33.3", instance.getVelocityLimitOfRoad("Highway") == 33.3);
    }

    /**
     * Test of getVelocityLimitOfRoad method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimitOfRoad() {
        System.out.println("getVelocityLimitOfRoad");
        Vehicle instance = new VehicleExtender();

        instance.insertVelocityLimit("Highway", 33.3);

        assertTrue("The velocity limit should be 33.3", instance.getVelocityLimitOfRoad("Highway") == 33.3);

        assertTrue("The velocity limit should be 20.0", instance.getVelocityLimitOfRoad("Regular Road") == 20.0);
    }
    /**
     * Test of getMaximumVelocityInGear method, of class Vehicle.
     */
    @Test
    public void testGetMaximumVelocityInGear() {
        System.out.println("getMaximumVelocityInGear");
        int gearIndex = 0;
        Vehicle instance = new VehicleExtender();
        double expResult = 10.0;
        double result = instance.getMaximumVelocityInGear(gearIndex);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getvelocityLimitPerRoad method, of class Vehicle.
     */
    @Test
    public void testGetvelocityLimitPerRoad() {
        System.out.println("getvelocityLimitPerRoad");
        Vehicle instance = new VehicleExtender();
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);
        Set<Map.Entry<String, Double>> result = instance.getvelocityLimitPerRoad();
        
        Iterator<Map.Entry<String, Double>> iterator = result.iterator();
        
        Map.Entry<String, Double> velocity = iterator.next();
        assertTrue("Should be Highway 33.3", velocity.getKey().equals("Highway") && velocity.getValue() == 33.3);
        
        velocity = iterator.next();
        assertTrue("Should be Regular Road 25.0", velocity.getKey().equals("Regular Road") && velocity.getValue() == 25.0);       
    }

}
