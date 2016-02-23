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
public class VehicleCombustionTest {

    public VehicleCombustionTest() {
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
     * Test of setAndGetRpmMinimum method, of class VehicleCombustion.
     */
    @Test
    public void testSetAndGetRpmMinimum() {
        System.out.println("setAndGetRpmMinimum");
        VehicleCombustion instance = new VehicleCombustion();

        try {
            instance.setRpmMinimum(-1);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setRpmMinimum(1000);

        assertTrue("Expected 1000", instance.getRpmMinimum() == 1000);
    }

    /**
     * Test of setAndGetRpmMaximum method, of class VehicleCombustion.
     */
    @Test
    public void testSetAndGetRpmMaximum() {
        System.out.println("setAndGetRpmMaximum");
        VehicleCombustion instance = new VehicleCombustion();

        try {
            instance.setRpmMaximum(-1);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setRpmMaximum(5500);

        assertTrue("Expected 5500", instance.getRpmMaximum() == 5500);
    }

    /**
     * Test of setAndGetFinalDrive method, of class VehicleCombustion.
     */
    @Test
    public void testSetAndGetFinalDrive() {
        System.out.println("setAndGetFinalDrive");
        VehicleCombustion instance = new VehicleCombustion();

        try {
            instance.setFinalDrive(-1);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setFinalDrive(2.6);

        assertTrue("Expected 2.6", instance.getFinalDrive() == 2.6);
    }

    /**
     * Test of getGearbox method, of class VehicleCombustion.
     */
    @Test
    public void testGetGearbox() {
        System.out.println("getGearbox");
        VehicleCombustion instance = new VehicleCombustion();

        assertTrue("The class name should be the same.", instance.getGearbox().getClass() == Gearbox.class);
    }

    /**
     * Test of getAcceleratorPedal method, of class VehicleCombustion.
     */
    @Test
    public void testGetThrottleList() {
        System.out.println("getThrottleList");
        VehicleCombustion instance = new VehicleCombustion();

        assertTrue("The class name should be the same", instance.getAcceleratorPedal().getClass() == AcceleratorPedal.class);
    }

    /**
     * Test of getMaximumVelocity method, of class VehicleCombustion.
     */
    @Test
    public void testGetMaximumVelocity() {
        System.out.println("getMaximumVelocity");
        VehicleCombustion instance = new VehicleCombustion();

        instance.setWheelSize(0.5);
        instance.setRpmMinimum(1000);
        instance.setRpmMaximum(5500);
        instance.setFinalDrive(2.6);
        instance.getGearbox().insertGearAndGearRatio(1, 3.5);
        instance.getGearbox().insertGearAndGearRatio(2, 2.5);
        instance.getGearbox().insertGearAndGearRatio(3, 1.9);
        instance.getGearbox().insertGearAndGearRatio(4, 1.2);
        instance.getGearbox().insertGearAndGearRatio(5, 0.8);

        double expResult = 0.0;
        double result = Math.round(instance.getMaximumVelocity() * 1000.0) / 1000.0;
        assertEquals(expResult, result, 69.226);
    }

    /**
     * Test of toString method, of class VehicleCombustion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleCombustion instance = new VehicleCombustion();
        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(0);
        instance.setDragCoefficient(0.35);
        instance.setFrontalArea(2.4);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);
        instance.setRpmMinimum(1000);
        instance.setRpmMaximum(5500);
        instance.setFinalDrive(2.6);
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);
        instance.getGearbox().insertGearAndGearRatio(1, 3.5);
        instance.getGearbox().insertGearAndGearRatio(2, 2.7);
        AcceleratorPedal accPedal = instance.getAcceleratorPedal();
        Throttle throttle = accPedal.newThrottle();
        Regime regime = throttle.newRegime();
        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);
        accPedal.insertThrottle(25, throttle);

        String expResult = ""
                + "\tName: Nissan Skyline\n"
                + "\tDescription: Sports Car\n"
                + "\tType: Car\n"
                + "\tFuel: Gasoline\n"
                + "\tMass: 1300.0 Kg\n"
                + "\tLoad: 0.0 Kg\n"
                + "\tDrag Coefficient: 0.35\n"
                + "\tFrontal Area: 2.4 m\n"
                + "\tRRC: 0.01\n"
                + "\tWheel Size: 0.6 m\n"
                + "\tVelocity Limits:\n"
                + "\t\tHighway: 33.3 m/s\n"
                + "\t\tRegular Road: 25.0 m/s\n"
                + "\tRPM Minimum: 1000\n"
                + "\tRPM Maximum: 5500\n"
                + "\tFinal Drive: 2.6 m\n"
                + "\tGearbox:\n"
                + "\t\t1 - Ratio: 3.5\n"
                + "\t\t2 - Ratio: 2.7\n"
                + "\tList of throttles:\n"
                + "\t\tPercentage: 25%\n"
                + "\t\tList of regimes:\n"
                + "\t\t\tTorque: 105\n"
                + "\t\t\tRPM lower interval: 1000\n"
                + "\t\t\tRPM higher interval: 2499\n"
                + "\t\t\tSFC: 8.2\n"
                + "\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class VehicleCombustion.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        VehicleCombustion instance = new VehicleCombustion();
        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(0);
        instance.setDragCoefficient(0.35);
        instance.setFrontalArea(2.4);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);
        instance.setRpmMinimum(1000);
        instance.setRpmMaximum(5500);
        instance.setFinalDrive(2.6);
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);
        instance.getGearbox().insertGearAndGearRatio(1, 3.5);
        instance.getGearbox().insertGearAndGearRatio(2, 2.7);
        AcceleratorPedal accPedal = instance.getAcceleratorPedal();
        Throttle throttle = accPedal.newThrottle();
        Regime regime = throttle.newRegime();
        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);
        accPedal.insertThrottle(25, throttle);

        String expResult = ""
                + "\t<li>Name: Nissan Skyline</li>\n"
                + "\t<li>Description: Sports Car</li>\n"
                + "\t<li>Type: Car</li>\n"
                + "\t<li>Fuel: Gasoline</li>\n"
                + "\t<li>Mass: 1300.0 Kg</li>\n"
                + "\t<li>Load: 0.0 Kg</li>\n"
                + "\t<li>Drag Coefficient: 0.35</li>\n"
                + "\t<li>Frontal Area: 2.4 m</li>\n"
                + "\t<li>RRC: 0.01</li>\n"
                + "\t<li>Wheel Size: 0.6 m</li>\n"
                + "\t<li>Velocity Limits:<ul>\n"
                + "\t\t<li>Highway: 33.3 m/s</li>\n"
                + "\t\t<li>Regular Road: 25.0 m/s</li>\n"
                + "\t</ul></li>\n"
                + "\t<li>RPM Minimum: 1000</li>\n"
                + "\t<li>RPM Maximum: 5500</li>\n"
                + "\t<li>Final Drive: 2.6 m</li>\n"
                + "\t<li>Gearbox:<ul>\n"
                + "\t\t<li>1 - Ratio: 3.5</li>\n"
                + "\t\t<li>2 - Ratio: 2.7</li>\n"
                + "\t</ul></li>\n"
                + "\t<li>List of throttles:<ul>\n"
                + "\t\t<li>Percentage: 25%</li>\n"
                + "\t\t<li>List of regimes:<ul>\n"
                + "\t\t\t<li>Torque: 105</li>\n"
                + "\t\t\t<li>RPM lower interval: 1000</li>\n"
                + "\t\t\t<li>RPM higher interval: 2499</li>\n"
                + "\t\t\t<li>SFC: 8.2</li>\n"
                + "\t\t</ul></li>\n"
                + "\t</ul></li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateVehicleCombustion method, of class VehicleCombustion.
     */
    @Test
    public void testValidateVehicleCombustion() {
        System.out.println("validateVehicleCombustion");
        VehicleCombustion instance = new VehicleCombustion();

        boolean result = false;

        try {

            instance.setRpmMaximum(2);
            result = instance.validateVehicleCombustion();
            assertFalse("", result);

            instance.setFinalDrive(25.1);
            instance.setRpmMinimum(4);
            result = instance.validateVehicleCombustion();
            assertFalse("", result);

        } catch (IllegalArgumentException e) {
        }

        instance.setRpmMaximum(10);
        instance.setFinalDrive(25.1);
        instance.setRpmMinimum(4);

        result = instance.validateVehicleCombustion();
        assertTrue("", result);
    }

    /**
     * Test of getMaximumVelocityInGear method, of class VehicleCombustion.
     */
    @Test
    public void testGetMaximumVelocityInGear() {
        System.out.println("getMaximumVelocityInGear");
        int gearIndex = 1;
        VehicleCombustion instance = new VehicleCombustion();
        instance.setName("Nissan Skyline");
        instance.setDescription("Sports Car");
        instance.setType("Car");
        instance.setFuel("Gasoline");
        instance.setMass(1300);
        instance.setLoad(0);
        instance.setDragCoefficient(0.35);
        instance.setFrontalArea(2.4);
        instance.setRollingResistanceCoefficient(0.01);
        instance.setWheelSize(0.6);
        instance.setRpmMinimum(1000);
        instance.setRpmMaximum(5500);
        instance.setFinalDrive(2.6);
        instance.insertVelocityLimit("Highway", 33.3);
        instance.insertVelocityLimit("Regular Road", 25.0);
        instance.getGearbox().insertGearAndGearRatio(1, 3.5);
        instance.getGearbox().insertGearAndGearRatio(2, 2.7);
        double expResult = 19;
        double result = Math.round(instance.getMaximumVelocityInGear(gearIndex));
        assertEquals(expResult, result, 0.0);
        
        expResult = -1;
        result = instance.getMaximumVelocityInGear(7);
        assertEquals(expResult,result,0.0);
    }

}
