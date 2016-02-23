package model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
public class AcceleratorPedalTest {

    public AcceleratorPedalTest() {
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
     * Test of newThrottle method, of class AcceleratorPedal.
     */
    @Test
    public void testNewThrottle() {
        System.out.println("newThrottle");
        AcceleratorPedal instance = new AcceleratorPedal();

        assertTrue("The classes should be the same", instance.newThrottle().getClass() == Throttle.class);
    }

    /**
     * Test of insertThrottle method, of class AcceleratorPedal.
     */
    @Test
    public void testInsertThrottle() {
        System.out.println("insertThrottle");
        AcceleratorPedal instance = new AcceleratorPedal();
        Throttle throttle = instance.newThrottle();

        assertTrue("The throttle should be added", instance.insertThrottle(25, throttle));

        assertTrue("The throttle should be added", instance.insertThrottle(75, throttle));

        assertFalse("The throttle should fail to add", instance.insertThrottle(25, throttle));
    }

    /**
     * Test of toString method, of class AcceleratorPedal.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AcceleratorPedal instance = new AcceleratorPedal();

        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();

        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);

        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);

        String expResult = ""
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
     * Test of getTorqueByPercentageAndRPM method, of class AcceleratorPedal.
     */
    @Test
    public void testGetTorqueByPercentageAndRPM() {
        System.out.println("getTorqueByPercentageAndRPM");
        AcceleratorPedal instance = new AcceleratorPedal();

        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);

        assertTrue("Expected 95", instance.getTorqueByPercentageAndRPM(25, 2762.22222) == 95);

        assertTrue("Expected 80", instance.getTorqueByPercentageAndRPM(25, 4765.4321) == 80);

        assertTrue("Expected -1", instance.getTorqueByPercentageAndRPM(25, 7000) == -1);
    }

    /**
     * Test of getSFCByPercentageAndRPM method, of class AcceleratorPedal.
     */
    @Test
    public void testGetSFCByPercentageAndRPM() {
        System.out.println("getSFCByPercentageAndRPM");
        AcceleratorPedal instance = new AcceleratorPedal();

        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);

        assertTrue("Expected 6.2", instance.getSFCByPercentageAndRPM(25, 2762.22222) == 6.2);

        assertTrue("Expected 10.2", instance.getSFCByPercentageAndRPM(25, 4765.4321) == 10.2);

        assertTrue("Expected -1", instance.getSFCByPercentageAndRPM(25, 7000) == -1);
    }

    /**
     * Test of getClosestHighestRPM method, of class AcceleratorPedal.
     */
    @Test
    public void testGetClosestHighestRPM() {
        System.out.println("getClosestHighestRPM");
        int iThrottle = 25;
        int torque = 93;
        AcceleratorPedal instance = new AcceleratorPedal();

        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);
        int expResult = 3999;
        int result = instance.getClosestHighestRPM(iThrottle, torque);
        assertEquals(expResult, result);

        expResult = 5500;
        torque = 82;
        result = instance.getClosestHighestRPM(iThrottle, torque);
        assertEquals(expResult, result);

        expResult = 2499;
        torque = 90;
        result = instance.getClosestHighestRPM(iThrottle, torque);
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class AcceleratorPedal.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        AcceleratorPedal instance = new AcceleratorPedal();

        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();

        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);

        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);
        String expResult = ""
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
     * Test of validateAcceleratorPedal method, of class AcceleratorPedal.
     */
    @Test
    public void testValidateAcceleratorPedal() {
        System.out.println("validateAcceleratorPedal");
        AcceleratorPedal instance = new AcceleratorPedal();

        boolean result = false;

        try {
            result = instance.validateAcceleratorPedal();
            assertFalse("", result);

        } catch (IllegalArgumentException e) {
        }

        instance.insertThrottle(25, new Throttle());
        result = instance.validateAcceleratorPedal();
        assertTrue("", result);
    }

    /**
     * Test of entrySet method, of class AcceleratorPedal.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");
        AcceleratorPedal instance = new AcceleratorPedal();
        
        Throttle throttle = instance.newThrottle();

        Regime regime = throttle.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        throttle.insertRegime(regime);

        regime = throttle.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        throttle.insertRegime(regime);

        instance.insertThrottle(25, throttle);
        
        Set<Map.Entry<Integer, Throttle>> result = instance.entrySet();
        
        assertTrue("Size should be 1", result.size() == 1);
        
        for(Entry<Integer, Throttle> entry : result) {
            assertTrue("Percentage should be 25", entry.getKey() == 25);
            assertTrue("Throttles should be equal", entry.getValue().equals(throttle));
        }
    }

}
