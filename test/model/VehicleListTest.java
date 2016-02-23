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
public class VehicleListTest {

    public VehicleListTest() {
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
     * Test of addVehicle method, of class VehicleList.
     */
    @Test
    public void testAddVehicle() {
        System.out.println("addVehicle");
        Vehicle vehicle1 = new VehicleCombustion();
        vehicle1.setName("Dummy");
        vehicle1.setDescription("Dummy description");
        Vehicle vehicle2 = new VehicleCombustion();
        vehicle2.setName("Dummy");
        vehicle2.setDescription("Dummy description");
        VehicleList instance = new VehicleList();

        boolean result = instance.addVehicle(vehicle1);
        assertTrue("Vehicle should be added without any problem", result);

        result = instance.addVehicle(vehicle2);
        assertFalse("Vehicle should have failed to add", result);

        vehicle1.setDescription("Testing the name generator");
        result = instance.addVehicle(vehicle2);
        assertTrue("Vehicle should be added without any problem", result);

        Iterator<Vehicle> iterator = instance.iterator();

        assertTrue("First vehicle should be Dummy", iterator.next().getName().equals("Dummy"));
        assertTrue("Second vehicle should be Dummy2", iterator.next().getName().equals("Dummy2"));
    }

    /**
     * Test of removeVehicle method, of class VehicleList.
     */
    @Test
    public void testRemoveVehicle() {
        System.out.println("removeVehicle");
        Vehicle vehicle = new VehicleCombustion();

        VehicleList instance = new VehicleList();
        instance.addVehicle(vehicle);

        Vehicle vehicle1 = new VehicleCombustion();
        vehicle1.setName("Vehicle");
        instance.addVehicle(vehicle1);

        boolean result = instance.removeVehicle(vehicle);
        assertTrue("", result);

        result = instance.removeVehicle(vehicle);
        assertFalse("", result);

        result = instance.removeVehicle(vehicle1);
        assertTrue("", result);

    }

    /**
     * Test of iterator method, of class VehicleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        VehicleList instance = new VehicleList();

        Vehicle vehicle = new VehicleCombustion();
        instance.addVehicle(vehicle);

        Iterator<Vehicle> iterator = instance.iterator();

        assertTrue("The objects should be the same", iterator.next().equals(vehicle));

        assertFalse("There should be no more vehicles", iterator.hasNext());
    }

    /**
     * Test of newVehicleCombustion method, of class VehicleList.
     */
    @Test
    public void testNewVehicleCombustion() {
        System.out.println("newVehicleCombustion");
        VehicleList instance = new VehicleList();

        Vehicle expResult = new VehicleCombustion();
        expResult.setName("name");
        expResult.setType("type");
        expResult.setFuel("fuel");
        expResult.setMass(2);
        expResult.setLoad(2);
        expResult.setDragCoefficient(2);
        expResult.setRollingResistanceCoefficient(2);
        expResult.setWheelSize(2);
        expResult.setEnergyEfficiency(new Function());

        Vehicle result = instance.newVehicleCombustion();
        result.setName("name");
        result.setType("type");
        result.setFuel("fuel");
        result.setMass(2);
        result.setLoad(2);
        result.setDragCoefficient(2);
        result.setRollingResistanceCoefficient(2);
        result.setWheelSize(2);
        result.setEnergyEfficiency(new Function());

        assertTrue("", expResult.equals(result));

        expResult.setName("Vehicle2");

        assertFalse("", expResult.equals(result));
    }

    /**
     * Test of newVehicleElectric method, of class VehicleList.
     */
    @Test
    public void testNewVehicleElectric() {
        System.out.println("newVehicleElectric");
        VehicleList instance = new VehicleList();
        String name = "Vehicle";

        Vehicle expResult = new VehicleElectric();
        expResult.setName(name);

        Vehicle result = instance.newVehicleElectric();
        result.setName(name);

        assertTrue("", expResult.equals(result));

        expResult.setName("Vehicle2");

        assertFalse("", expResult.equals(result));
    }

    /**
     * Test of toString method, of class VehicleList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleList instance = new VehicleList();
        VehicleCombustion vehicle = instance.newVehicleCombustion();
        vehicle.setName("Nissan Skyline");
        vehicle.setDescription("Sports Car");
        vehicle.setType("Car");
        vehicle.setFuel("Gasoline");
        vehicle.setMass(1300);
        vehicle.setLoad(0);
        vehicle.setDragCoefficient(0.35);
        vehicle.setFrontalArea(2.4);
        vehicle.setRollingResistanceCoefficient(0.01);
        vehicle.setWheelSize(0.6);
        vehicle.setRpmMinimum(1000);
        vehicle.setRpmMaximum(5500);
        vehicle.setFinalDrive(2.6);
        vehicle.insertVelocityLimit("Highway", 33.3);
        vehicle.insertVelocityLimit("Regular Road", 25.0);
        vehicle.getGearbox().insertGearAndGearRatio(1, 3.5);
        vehicle.getGearbox().insertGearAndGearRatio(2, 2.7);
        AcceleratorPedal accPedal = vehicle.getAcceleratorPedal();
        Throttle throttle = accPedal.newThrottle();
        Regime regime = throttle.newRegime();
        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);
        accPedal.insertThrottle(25, throttle);
        instance.addVehicle(vehicle);

        String expResult = ""
                + "List of Vehicles:\n"
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
     * Test of size method, of class VehicleList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        VehicleList instance = new VehicleList();

        VehicleCombustion v1 = new VehicleCombustion();
        v1.setName("Combustion Vehicle");
        VehicleElectric v2 = new VehicleElectric();
        v2.setName("Electric Vehicle");

        assertTrue("The list should be empty", instance.size() == 0);

        instance.addVehicle(v1);
        instance.addVehicle(v2);

        assertTrue("The list should contain 3 vehicles", instance.size() == 2);
    }

    /**
     * Test of getVehicleList method, of class VehicleList.
     */
    @Test
    public void testGetVehicleList() {
        System.out.println("getVehicleList");
        VehicleList instance = new VehicleList();

        String vehicleName = "Super Mega Hiper Fast Car With Wheels";

        VehicleCombustion vehicle = instance.newVehicleCombustion();
        vehicle.setName(vehicleName);
        instance.addVehicle(vehicle);

        List<String> expResult = new ArrayList<>();
        expResult.add(vehicleName);

        List<String> result = instance.getVehicleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of validadeVehicleList method, of class VehicleList.
     */
    @Test
    public void testValidadeVehicleList() {
        System.out.println("validadeVehicleList");
        VehicleList instance = new VehicleList();
        boolean result = false;

        try {
            result = instance.validateVehicleList();
            assertFalse("", result);
        } catch (IllegalArgumentException e) {
        }

        instance.addVehicle(new VehicleCombustion());

        result = instance.validateVehicleList();
        assertTrue("", result);

    }

    /**
     * Test of toStringHTML method, of class VehicleList.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        VehicleList instance = new VehicleList();
        VehicleCombustion vehicle = instance.newVehicleCombustion();
        vehicle.setName("Nissan Skyline");
        vehicle.setDescription("Sports Car");
        vehicle.setType("Car");
        vehicle.setFuel("Gasoline");
        vehicle.setMass(1300);
        vehicle.setLoad(0);
        vehicle.setDragCoefficient(0.35);
        vehicle.setFrontalArea(2.4);
        vehicle.setRollingResistanceCoefficient(0.01);
        vehicle.setWheelSize(0.6);
        vehicle.setRpmMinimum(1000);
        vehicle.setRpmMaximum(5500);
        vehicle.setFinalDrive(2.6);
        vehicle.insertVelocityLimit("Highway", 33.3);
        vehicle.insertVelocityLimit("Regular Road", 25.0);
        vehicle.getGearbox().insertGearAndGearRatio(1, 3.5);
        vehicle.getGearbox().insertGearAndGearRatio(2, 2.7);
        AcceleratorPedal accPedal = vehicle.getAcceleratorPedal();
        Throttle throttle = accPedal.newThrottle();
        Regime regime = throttle.newRegime();
        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);
        accPedal.insertThrottle(25, throttle);
        instance.addVehicle(vehicle);

        String expResult = ""
                + "List of Vehicles:<ul>\n"
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
                + "\t</ul></li>\n"
                + "</ul>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

}
