/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class VehicleElectricTest {

    public VehicleElectricTest() {
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
     * Test of setAndGetRpmMinimum method, of class VehicleElectric.
     */
    @Test
    public void testSetAndGetRpmMinimum() {
        System.out.println("setAndGetRpmMinimum");
        VehicleElectric instance = new VehicleElectric();
        try {
            instance.setRpmMinimum(-3);
            assertTrue("Should be 0", instance.getRpmMinimum() == 0);
        } catch (IllegalArgumentException ex) {
        };
        instance.setRpmMinimum(100);
        assertTrue("Should be 100", instance.getRpmMinimum() == 100);

    }

    /**
     * Test of setAndGetRpmMaximum method, of class VehicleElectric.
     */
    @Test
    public void testSetAndGetRpmMaximum() {
        System.out.println("setAndGetRpmMaximum");
        VehicleElectric instance = new VehicleElectric();
        try {
            instance.setRpmMaximum(-5);
            assertTrue("Should be 0", instance.getRpmMaximum() == 0);
        } catch (IllegalArgumentException ex) {
        };
        instance.setRpmMaximum(200);
        assertTrue("Should be 200", instance.getRpmMaximum() == 200);
    }

    /**
     * Test of setAndGetFinalDrive method, of class VehicleElectric.
     */
    @Test
    public void testSetAndGetFinalDrive() {
        System.out.println("setAndGetFinalDrive");
        VehicleElectric instance = new VehicleElectric();
        try {
            instance.setFinalDrive(-5);
            assertTrue("Should be 0", instance.getFinalDrive() == 0);
        } catch (IllegalArgumentException ex) {
        };
        instance.setFinalDrive(10);
        assertTrue("Should be 10", instance.getFinalDrive() == 10);
    }

    /**
     * Test of getGearbox method, of class VehicleElectric.
     */
    @Test
    public void testGetGearbox() {
        System.out.println("getGearbox");
        VehicleElectric instance = new VehicleElectric();
        String gearResult = instance.getGearbox().getClass().getSimpleName();
        String gearExpresult = "Gearbox";
        assertTrue("Should be Gearbox", gearResult.equalsIgnoreCase(gearExpresult));

    }

    /**
     * Test of getAcceleratorPedal method, of class VehicleElectric.
     */
    @Test
    public void testGetAcceleratorPedal() {
        System.out.println("getAcceleratorPedal");
        VehicleElectric instance = new VehicleElectric();
        AcceleratorPedal expResult = new AcceleratorPedal();
        AcceleratorPedal result = instance.getAcceleratorPedal();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getMaximumVelocity method, of class VehicleElectric.
     */
    @Test
    public void testGetMaximumVelocity() {
        System.out.println("getMaximumVelocity");
        VehicleElectric instance = new VehicleElectric();
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
     * Test of getEnergyRegenerationRatio method, of class VehicleElectric.
     */
    @Test
    public void testGetSetEnergyRegenerationRatio() {
        System.out.println("getEnergyRegenerationRatio");
        VehicleElectric instance = new VehicleElectric();
        try {
            instance.setEnergyRegenerationRatio(-1);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        instance.setEnergyRegenerationRatio(0.9);

        assertTrue("Expected 0.9", instance.getEnergyRegenerationRatio() == 0.9);
    }

    /**
     * Test of toString method, of class VehicleElectric.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleElectric instance = new VehicleElectric();
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
                            + "	Name: Nissan Skyline\n"
                            + "	Description: Sports Car\n"
                            + "	Type: Car\n"
                            + "	Fuel: Gasoline\n"
                            + "	Mass: 1300.0 Kg\n"
                            + "	Load: 0.0 Kg\n"
                            + "	Drag Coefficient: 0.35\n"
                            + "	Frontal Area: 2.4 m\n"
                            + "	RRC: 0.01\n"
                            + "	Wheel Size: 0.6 m\n"
                            + "	Velocity Limits:\n"
                            + "		Highway: 33.3 m/s\n"
                            + "		Regular Road: 25.0 m/s\n"
                            + "	RPM Minimum: 1000\n"
                            + "	RPM Maximum: 5500\n"
                            + "	Final Drive: 2.6 m\n"
                            + "	Energy Regeneration Ratio: 0.0 \n"
                            + "	Gearbox:\n"
                            + "		1 - Ratio: 3.5\n"
                            + "		2 - Ratio: 2.7\n"
                            + "	List of throttles:\n"
                            + "		Percentage: 25%\n"
                            + "		List of regimes:\n"
                            + "			Torque: 105\n"
                            + "			RPM lower interval: 1000\n"
                            + "			RPM higher interval: 2499\n"
                            + "			SFC: 8.2\n"
                            + "\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class VehicleElectric.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        VehicleElectric instance = new VehicleElectric();
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

        String expResult = "	<li>Name: Nissan Skyline</li>\n"
                            + "	<li>Description: Sports Car</li>\n"
                            + "	<li>Type: Car</li>\n"
                            + "	<li>Fuel: Gasoline</li>\n"
                            + "	<li>Mass: 1300.0 Kg</li>\n"
                            + "	<li>Load: 0.0 Kg</li>\n"
                            + "	<li>Drag Coefficient: 0.35</li>\n"
                            + "	<li>Frontal Area: 2.4 m</li>\n"
                            + "	<li>RRC: 0.01</li>\n"
                            + "	<li>Wheel Size: 0.6 m</li>\n"
                            + "	<li>Velocity Limits:<ul>\n"
                            + "		<li>Highway: 33.3 m/s</li>\n"
                            + "		<li>Regular Road: 25.0 m/s</li>\n"
                            + "	</ul></li>\n"
                            + "	<li>RPM Minimum: 1000</li>\n"
                            + "	<li>RPM Maximum: 5500</li>\n"
                            + "	<li>Final Drive: 2.6 m</li>\n"
                            + "	<li>Energy Regeneration Ratio: 0.0</li>\n"
                            + "	<li>Gearbox:<ul>\n"
                            + "		<li>1 - Ratio: 3.5</li>\n"
                            + "		<li>2 - Ratio: 2.7</li>\n"
                            + "	</ul></li>\n"
                            + "	<li>List of throttles:<ul>\n"
                            + "		<li>Percentage: 25%</li>\n"
                            + "		<li>List of regimes:<ul>\n"
                            + "			<li>Torque: 105</li>\n"
                            + "			<li>RPM lower interval: 1000</li>\n"
                            + "			<li>RPM higher interval: 2499</li>\n"
                            + "			<li>SFC: 8.2</li>\n"
                            + "		</ul></li>\n"
                            + "	</ul></li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateVehicleElectric method, of class VehicleElectric.
     */
    @Test
    public void testValidateVehicleElectric() {
        System.out.println("validateVehicleElectric");
        VehicleElectric instance = new VehicleElectric();
        boolean result = false;
        try {

            instance.setRpmMaximum(2);
            result = instance.validateVehicleElectric();
            assertFalse("", result);

            instance.setFinalDrive(25.1);
            instance.setRpmMinimum(4);
            result = instance.validateVehicleElectric();
            assertFalse("", result);

        } catch (IllegalArgumentException e) {
        }

        instance.setRpmMaximum(10);
        instance.setFinalDrive(25.1);
        instance.setRpmMinimum(4);

        result = instance.validateVehicleElectric();
        assertTrue("", result);
    }

    /**
     * Test of getMaximumVelocityInGear method, of class VehicleElectric.
     */
    @Test
    public void testGetMaximumVelocityInGear() {
        System.out.println("getMaximumVelocityInGear");
        int gearIndex = 1;
        VehicleElectric instance = new VehicleElectric();
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
    }
}
