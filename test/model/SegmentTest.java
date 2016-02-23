package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author G11
 */
public class SegmentTest {

    public SegmentTest() {
    }

    /**
     * Test of getAnSetIndex method, of class Segment.
     */
    @Test
    public void testGetAndSetIndex() {
        System.out.println("setAndGetIndex");

        Segment instance = new Segment();
        int index = -2;

        try {
            instance.setIndex(index);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        index = 1;
        instance.setIndex(index);

        assertTrue("", instance.getIndex() == index);
    }

    /**
     * Test of getAndSetInitialHeight method, of class Segment.
     */
    @Test
    public void testGetAndSetInitialHeight() {
        System.out.println("setAndGetInitialHeight");

        Segment instance = new Segment();
        double initialHeight = 1;

        instance.setInitialHeight(initialHeight);

        assertTrue("", instance.getInitialHeight() == initialHeight);
    }

    /**
     * Test of getAndSetAngle method, of class Segment.
     */
    @Test
    public void testGetAnSetAngle() {
        System.out.println("setAndGetAngle");

        Segment instance = new Segment();
        double angle = 51;

        try {
            instance.setAngle(angle);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }
        
        angle = -50;
        try {
            instance.setAngle(angle);
            assertTrue("An exception should have been thrown", false);

        } catch (IllegalArgumentException ex) {
        }

        angle = 23;
        instance.setAngle(angle);
        assertTrue("", instance.getAngle() == angle);
    }

    /**
     * Test of getAnSetLength method, of class Segment.
     */
    @Test
    public void testGetAnSetLength() {
        System.out.println("setAndGetLength");

        Segment instance = new Segment();
        double length = 0;

        try {
            instance.setLength(length);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        length = 2;
        instance.setLength(length);

        assertTrue("", instance.getLength() == length);
    }

    /**
     * Test of getAndSetMaximumVelocity method, of class Segment.
     */
    @Test
    public void testGetAndSetMaximumVelocity() {
        System.out.println("getAndSetMaximumVelocity");

        Segment instance = new Segment();
        double maximumVelocity = -1;

        try {
            instance.setMaximumVelocity(maximumVelocity);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        maximumVelocity = 0.1;
        instance.setMaximumVelocity(maximumVelocity);

        assertTrue("", instance.getMaximumVelocity() == maximumVelocity);
    }

    /**
     * Test of getAndSetMinimumVelocity method, of class Segment.
     */
    @Test
    public void testGetAndSetMinimumVelocity() {
        System.out.println("getAndSetMinimumVelocity");

        Segment instance = new Segment();
        double minimumVelocity = -1;

        try {
            instance.setMinimumVelocity(minimumVelocity);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        minimumVelocity = 0.1;
        instance.setMinimumVelocity(minimumVelocity);

        assertTrue("", instance.getMinimumVelocity() == minimumVelocity);
    }

    /**
     * Test of getAndSetMaximumNumberVehicles method, of class Segment.
     */
    @Test
    public void testGetAndSetMaximumNumberVehicles() {
        System.out.println("getAndSetMaximumNumberVehicles");

        Segment instance = new Segment();
        int maximumNumberVehicles = 0;

        try {
            instance.setMaximumNumberVehicles(maximumNumberVehicles);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        maximumNumberVehicles = 4;
        instance.setMaximumNumberVehicles(maximumNumberVehicles);

        assertTrue("", instance.getMaximumNumberVehicles() == maximumNumberVehicles);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Segment();
        ((Segment) otherObject).setIndex(1);
        Segment instance = new Segment();
        instance.setIndex(1);

        assertTrue("The objects should be equals.", instance.equals(otherObject));

        instance.setIndex(2);

        assertFalse("The objects should be different.", instance.equals(otherObject));
    }

    /**
     * Test of validateSegment method, of class Segment.
     */
    @Test
    public void testValidateSegment() {
        System.out.println("validateSegment");
        Segment instance = new Segment();

        instance.setIndex(1);
        instance.setInitialHeight(1);
        instance.setLength(2.3);
        boolean result = false;

        try {
            result = instance.validateSegment();
            assertFalse("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.setMaximumNumberVehicles(2);
        instance.setMaximumVelocity(50);

        try {
            result = instance.validateSegment();
            assertTrue("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.setMinimumVelocity(60);

        try {
            result = instance.validateSegment();
            assertFalse("", result);
        } catch (IllegalArgumentException ex) {
        }

        instance.setAngle(2);
        instance.setMaximumNumberVehicles(2);
        try {
            instance.setMaximumVelocity(50);
        } catch (IllegalArgumentException ex) {
        }

        instance.setMaximumVelocity(100);
        instance.setAngle(2);
        result = instance.validateSegment();
        assertTrue("", result);

    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Segment instance = new Segment();
        instance.setIndex(1);
        instance.setInitialHeight(1);
        instance.setLength(2.3);
        instance.setAngle(20);
        instance.setMinimumVelocity(10);
        instance.setMaximumVelocity(100);
        instance.setMaximumNumberVehicles(5);
        String expResult = ""
                + "\t\tSegment: 1\n"
                + "\t\t\tInitial Height: 1.0 m\n"
                + "\t\t\tAngle: 20,000 º\n"
                + "\t\t\tLenght: 2,300 m\n"
                + "\t\t\tMinimum velocity: 10.0 m/s\n"
                + "\t\t\tMaximum velocity: 100.0 m/s\n"
                + "\t\t\tMaximum number of vehicles: 5 vehicles\n";

        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of toStringHTML method, of class Segment.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Segment instance = new Segment();
        String expResult = ""
                + "\t\t<li>Segment: -1<ul>\n"
                + "\t\t\t<li>Initial Height: 0.0m</li>\n"
                + "\t\t\t<li>Angle: 0,000º</li>\n"
                + "\t\t\t<li>Lenght: 0,000 m</li>\n"
                + "\t\t\t<li>Minimum velocity: 0.0 m/s</li>\n"
                + "\t\t\t<li>Maximum velocity: 0.0 m/s</li>\n"
                + "\t\t\t<li>Maximum number of vehicles: -1 vehicles</li>\n"
                + "\t\t</li></ul>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);

    }
}
