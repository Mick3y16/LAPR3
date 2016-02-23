package model;

import java.util.Iterator;
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
public class ThrottleTest {

    public ThrottleTest() {
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
     * Test of newRegime method, of class Throttle.
     */
    @Test
    public void testNewRegime() {
        System.out.println("newRegime");
        Throttle instance = new Throttle();

        assertTrue("The class name should be the same", instance.newRegime().getClass() == Regime.class);
    }

    /**
     * Test of insertRegime method, of class Throttle.
     */
    @Test
    public void testInsertRegime() {
        System.out.println("insertRegime");
        Throttle instance = new Throttle();

        assertTrue("The regime should be added to the throttle", instance.insertRegime(instance.newRegime()));

        assertFalse("The regime should fail to add as it already exists there", instance.insertRegime(instance.newRegime()));
    }

    /**
     * Test of toString method, of class Throttle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Throttle instance = new Throttle();

        Regime regime = instance.newRegime();

        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);

        instance.insertRegime(regime);

        String expResult = ""
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
     * Test of equals method, of class Throttle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Throttle anotherInstance = new Throttle();
        Throttle instance = new Throttle();

        Regime regime1 = instance.newRegime();
        regime1.setTorque(105);
        regime1.setRpmLow(1000);
        regime1.setRpmHigh(2499);
        regime1.setSfc(8.2);

        Regime regime2 = instance.newRegime();
        regime2.setTorque(115);
        regime2.setRpmLow(2500);
        regime2.setRpmHigh(3999);
        regime2.setSfc(6.2);

        assertTrue("Both objects should be equal", instance.equals(anotherInstance));

        instance.insertRegime(regime1);

        assertFalse("Both objects should be different, since one contains one more regime", instance.equals(anotherInstance));

        anotherInstance.insertRegime(regime2);

        assertFalse("Both objects should be different, since they contain different regimes", instance.equals(anotherInstance));

        instance.insertRegime(regime2);
        anotherInstance.insertRegime(regime1);

        assertTrue("Both objects should be equal", instance.equals(anotherInstance));
    }

    /**
     * Test of getTorqueByRPM method, of class Throttle.
     */
    @Test
    public void testGetTorqueByRPM() {
        System.out.println("getTorqueByRPM");
        Throttle instance = new Throttle();

        Regime regime = instance.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        instance.insertRegime(regime);

        regime = instance.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        instance.insertRegime(regime);

        regime = instance.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        instance.insertRegime(regime);

        assertTrue("Expected 95", instance.getTorqueByRPM(2762.22222) == 95);

        assertTrue("Expected 80", instance.getTorqueByRPM(4765.4321) == 80);

        assertTrue("Expected -1", instance.getTorqueByRPM(7000) == -1);
    }

    /**
     * Test of getSFCByRPM method, of class Throttle.
     */
    @Test
    public void testGetSFCByRPM() {
        System.out.println("getSFCByRPM");
        Throttle instance = new Throttle();

        Regime regime = instance.newRegime();
        regime.setTorque(85);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);
        instance.insertRegime(regime);

        regime = instance.newRegime();
        regime.setTorque(95);
        regime.setRpmLow(2500);
        regime.setRpmHigh(3999);
        regime.setSfc(6.2);
        instance.insertRegime(regime);

        regime = instance.newRegime();
        regime.setTorque(80);
        regime.setRpmLow(4000);
        regime.setRpmHigh(5500);
        regime.setSfc(10.2);
        instance.insertRegime(regime);

        assertTrue("Expected 6.2", instance.getSFCByRPM(2762.22222) == 6.2);

        assertTrue("Expected 10.2", instance.getSFCByRPM(4765.4321) == 10.2);

        assertTrue("Expected -1", instance.getSFCByRPM(7000) == -1);
    }

    /**
     * Test of toStringHTML method, of class Throttle.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Throttle instance = new Throttle();

        Regime regime = instance.newRegime();

        regime.setTorque(105);
        regime.setRpmLow(1000);
        regime.setRpmHigh(2499);
        regime.setSfc(8.2);

        instance.insertRegime(regime);

        String expResult = ""
                + "\t\t<li>List of regimes:<ul>\n"
                + "\t\t\t<li>Torque: 105</li>\n"
                + "\t\t\t<li>RPM lower interval: 1000</li>\n"
                + "\t\t\t<li>RPM higher interval: 2499</li>\n"
                + "\t\t\t<li>SFC: 8.2</li>\n"
                + "\t\t</ul></li>\n";
        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClosestHighestRPM method, of class Throttle.
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
     * Test of validateThrottle method, of class Throttle.
     */
    @Test
    public void testValidateThrottle() {
        System.out.println("validateThrottle");
        Throttle instance = new Throttle();

        boolean result = false;

        try {
            result = instance.validateThrottle();
            assertFalse("", result);

        } catch (IllegalArgumentException e) {
        }

        instance.insertRegime(new Regime());

        result = instance.validateThrottle();
        assertTrue("", result);
    }

    /**
     * Test of iterator method, of class Throttle.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Throttle instance = new Throttle();
        
        Regime regime1 = instance.newRegime();
        regime1.setTorque(85);
        regime1.setRpmLow(1000);
        regime1.setRpmHigh(2499);
        regime1.setSfc(8.2);
        instance.insertRegime(regime1);

        Regime regime2 = instance.newRegime();
        regime2.setTorque(95);
        regime2.setRpmLow(2500);
        regime2.setRpmHigh(3999);
        regime2.setSfc(6.2);
        instance.insertRegime(regime2);

        Regime regime3 = instance.newRegime();
        regime3.setTorque(80);
        regime3.setRpmLow(4000);
        regime3.setRpmHigh(5500);
        regime3.setSfc(10.2);
        instance.insertRegime(regime3);
        
        Iterator<Regime> result = instance.iterator();
        
        assertTrue("Should be regime1", result.next().equals(regime1));
        assertTrue("Should be regime2", result.next().equals(regime2));
        assertTrue("Should be regime3", result.next().equals(regime3));
        assertFalse("Should have reached the end", result.hasNext());
    }
}
