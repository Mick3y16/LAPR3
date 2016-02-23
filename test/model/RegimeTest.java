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
public class RegimeTest {

    public RegimeTest() {
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
     * Test of getTorque method, of class Regime.
     */
    @Test
    public void testSetAndGetTorque() {
        System.out.println("setAngGetTorque");

        Regime instance = new Regime();
        int torque = -1;

        try {
            instance.setTorque(torque);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        torque = 1;
        instance.setTorque(torque);

        assertTrue("", instance.getTorque() == torque);
    }

    /**
     * Test of getRpmLow method, of class Regime.
     */
    @Test
    public void testSetAndGetRpmLow() {
        System.out.println("setAndGetRpmLow");

        Regime instance = new Regime();
        int rpmLow = -1;

        try {
            instance.setRpmLow(rpmLow);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        rpmLow = 1;
        instance.setRpmLow(rpmLow);

        assertTrue("", instance.getRpmLow() == rpmLow);
    }

    /**
     * Test of getRpmHigh method, of class Regime.
     */
    @Test
    public void testSetAndGetRpmHigh() {
        System.out.println("setAndGetRpmHigh");
        Regime instance = new Regime();
        int rpmHigh = -1;

        try {
            instance.setRpmHigh(rpmHigh);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        rpmHigh = 1;
        instance.setRpmHigh(rpmHigh);

        assertTrue("", instance.getRpmHigh() == rpmHigh);
    }

    /**
     * Test of getSfc method, of class Regime.
     */
    @Test
    public void testSetAndGetSfc() {
        System.out.println("setAndGetSfc");
        Regime instance = new Regime();
        int sfc = -1;

        try {
            instance.setSfc(sfc);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        sfc = 1;
        instance.setSfc(sfc);

        assertTrue("", instance.getSfc() == sfc);
    }

    /**
     * Test of toString method, of class Regime.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Regime instance = new Regime();
        instance.setTorque(2);
        instance.setRpmLow(3);
        instance.setRpmHigh(4);
        instance.setSfc(2.1);

        String expResult = ""
                + "\t\t\tTorque: 2\n"
                + "\t\t\tRPM lower interval: 3\n"
                + "\t\t\tRPM higher interval: 4\n"
                + "\t\t\tSFC: 2.1\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Regime.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Regime();
        ((Regime) otherObject).setTorque(2);
        ((Regime) otherObject).setRpmLow(4);
        ((Regime) otherObject).setRpmHigh(10);
        ((Regime) otherObject).setSfc(20);

        Regime instance = new Regime();
        instance.setTorque(2);
        instance.setRpmLow(11);
        instance.setRpmHigh(20);
        instance.setSfc(2.1);

        assertFalse("The objects should be equals.", instance.equals(otherObject));

        instance.setRpmLow(4);
        assertTrue("The objects should be different.", instance.equals(otherObject));

        instance.setRpmLow(5);
        assertTrue("The objects should be different.", instance.equals(otherObject));

        instance.setRpmLow(1);
        instance.setRpmHigh(10);
        assertTrue("The objects should be different.", instance.equals(otherObject));

        instance.setRpmHigh(12);
        assertTrue("The objects should be different.", instance.equals(otherObject));

    }

    /**
     * Test of toStringHTML method, of class Regime.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Regime instance = new Regime();
        instance.setTorque(2);
        instance.setRpmLow(3);
        instance.setRpmHigh(4);
        instance.setSfc(2.1);

        String expResult = ""
                + "\t\t\t<li>Torque: 2</li>\n"
                + "\t\t\t<li>RPM lower interval: 3</li>\n"
                + "\t\t\t<li>RPM higher interval: 4</li>\n"
                + "\t\t\t<li>SFC: 2.1</li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateRegime method, of class Regime.
     */
    @Test
    public void testValidateRegime() {
        System.out.println("validateRegime");
        Regime instance = new Regime();
        boolean result = false;

        try {
            instance.setSfc(2);
            result = instance.validateRegime();
            assertFalse("", result);

            instance.setTorque(3);
            result = instance.validateRegime();
            assertFalse("", result);

            instance.setRpmHigh(30);
            instance.setRpmLow(31);
            result = instance.validateRegime();
            assertFalse("", result);

        } catch (IllegalArgumentException e) {
        }

        instance.setSfc(2);
        instance.setTorque(3);
        instance.setRpmHigh(35);
        instance.setRpmLow(31);
        result = instance.validateRegime();
        assertTrue("", result);
    }

}
