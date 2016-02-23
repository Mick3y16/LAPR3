package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author G11
 */
public class SectionTest {

    public SectionTest() {
    }

    /**
     * Test of getKey method, of class Section.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Section instance = new Section();
        int expResult = 1;
        instance.setKey(expResult);
        int result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAndSetRoadName method, of class Section.
     */
    @Test
    public void testGetAndSetRoadName() {
        System.out.println("getAndSetRoadName");
        Section instance = new Section();
        String roadName = " ";

        try {
            instance.setRoadName(roadName);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        roadName = "Name of the road";
        instance.setRoadName(roadName);

        assertTrue("", instance.getRoadName().equals(roadName));
    }

    /**
     * Test of getAndSetTypology method, of class Section.
     */
    @Test
    public void testGetAndSetTypology() {
        System.out.println("getAndSetTypology");
        Section instance = new Section();
        String typology = " ";

        try {
            instance.setTypology(typology);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        typology = "Typology";
        instance.setTypology(typology);

        assertTrue("", instance.getTypology().equals(typology));
    }

    /**
     * Test of getAndSetToll method, of class Section.
     */
    @Test
    public void testGetAndSetToll() {
        System.out.println("getAndSetToll");
        Section instance = new Section();

        double toll = -1;

        try {
            instance.setToll(toll);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        toll = 1.45;
        instance.setToll(toll);

        assertTrue("", instance.getToll() == toll);
    }

    /**
     * Test of getAndSetWindSpeed method, of class Section.
     */
    @Test
    public void testGetAndSetWindSpeed() {
        System.out.println("getAndSetWindSpeed");
        Section instance = new Section();

        double windSpeed = -1;

        try {
            instance.setWindSpeed(windSpeed);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        windSpeed = 1.45;
        instance.setWindSpeed(windSpeed);

        assertTrue("", instance.getWindSpeed() == windSpeed);
    }

    /**
     * Test of getAndSetWindOrientation method, of class Section.
     */
    @Test
    public void testGetAndSetWindOrientation() {
        System.out.println("getAndSetWindOrientation");
        Section instance = new Section();

        double windOrientation = -1;

        try {
            instance.setWindOrientation(windOrientation);
            assertTrue("An exception should have been thrown", true);
        } catch (IllegalArgumentException ex) {
        }

        windOrientation = 361;

        try {
            instance.setWindOrientation(windOrientation);
            assertTrue("An exception should have been thrown", false);
        } catch (IllegalArgumentException ex) {
        }

        windOrientation = 45;
        instance.setWindOrientation(windOrientation);

        assertTrue("", instance.getWindOrientation() == windOrientation);
    }

    /**
     * Test of getSegmentList method, of class Section.
     */
    @Test
    public void testGetSegmentList() {
        System.out.println("getgetSegmentList");
        Section instance = new Section();

        SegmentList segmentList = new SegmentList();

        assertTrue("", instance.getSegmentList().getClass().toString().equals(segmentList.getClass().toString()));
    }

    /**
     * Test of equals method, of class Section.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObject = new Section();
        ((Section) otherObject).setRoadName("A28-n01-n02");
        Section instance = new Section();
        instance.setRoadName("A28-n01-n02");

        assertTrue("The objects should be equals.", instance.equals(otherObject));

        instance.setRoadName("A28-n01-n03");

        assertFalse("The objects should be different.", instance.equals(otherObject));
    }

    /**
     * Test of validateSection method, of class Section.
     */
    @Test
    public void testValidateSection() {
        System.out.println("validateSection");
        Section instance = new Section();
        boolean result = false;

        try {
            result = instance.validateSection();
            assertFalse("", result);

            instance.setWindSpeed(30);
            instance.setTypology("typology");

            result = instance.validateSection();
            assertFalse("", result);

            instance.setToll(2);
            result = instance.validateSection();
            assertFalse("", result);

            instance.setWindOrientation(2.1);

            instance.setRoadName("road name");
            result = instance.validateSection();
            assertFalse("", result);

        } catch (IllegalArgumentException ex) {
        }

        instance.setRoadName("road name");
        instance.setTypology("typology");
        instance.setToll(2);
        instance.setWindOrientation(2.1);
        instance.setWindSpeed(30);

        result = instance.validateSection();
        assertTrue("", result);
    }

    /**
     * Test of toString method, of class Section.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Section instance = new Section();

        instance.setRoadName("A1");
        instance.setTypology("Autoestrada");
        instance.setToll(6.50f);
        instance.setWindSpeed(20);
        instance.setWindOrientation(45);

        SegmentList segList = instance.getSegmentList();

        Segment seg1 = new Segment();
        seg1.setIndex(1);
        seg1.setInitialHeight(1);
        seg1.setLength(2.3);
        seg1.setAngle(20);
        seg1.setMinimumVelocity(10);
        seg1.setMaximumVelocity(100);
        seg1.setMaximumNumberVehicles(5);

        Segment seg2 = new Segment();
        seg2.setIndex(2);
        seg2.setInitialHeight(10);
        seg2.setLength(2.4);
        seg2.setAngle(10);
        seg2.setMinimumVelocity(50);
        seg2.setMaximumVelocity(100);
        seg2.setMaximumNumberVehicles(4);
        segList.addSegment(seg1);
        segList.addSegment(seg2);

        String expResult = ""
                + "\tRoad name: A1\n"
                + "\tTypology: Autoestrada\n"
                + "\tToll: 6.5\n"
                + "\tWind speed: 20.0m/s\n"
                + "\tWind orientation: 45.0º\n"
                + "\tList of segments:\n"
                + "\t\tSegment: 1\n"
                + "\t\t\tInitial Height: 1.0 m\n"
                + "\t\t\tAngle: 20,000 º\n"
                + "\t\t\tLenght: 2,300 m\n"
                + "\t\t\tMinimum velocity: 10.0 m/s\n"
                + "\t\t\tMaximum velocity: 100.0 m/s\n"
                + "\t\t\tMaximum number of vehicles: 5 vehicles\n"
                + "\t\tSegment: 2\n"
                + "\t\t\tInitial Height: 10.0 m\n"
                + "\t\t\tAngle: 10,000 º\n"
                + "\t\t\tLenght: 2,400 m\n"
                + "\t\t\tMinimum velocity: 50.0 m/s\n"
                + "\t\t\tMaximum velocity: 100.0 m/s\n"
                + "\t\t\tMaximum number of vehicles: 4 vehicles\n";

        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of toStringHTML method, of class Section.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Section instance = new Section();
        instance.setRoadName("A1");
        instance.setTypology("Autoestrada");
        instance.setToll(6.50f);
        instance.setWindSpeed(20);
        instance.setWindOrientation(45);

        SegmentList segList = instance.getSegmentList();

        Segment seg1 = new Segment();
        seg1.setIndex(1);
        seg1.setInitialHeight(1);
        seg1.setLength(2.3);
        seg1.setAngle(20);
        seg1.setMinimumVelocity(10);
        seg1.setMaximumVelocity(100);
        seg1.setMaximumNumberVehicles(5);

        Segment seg2 = new Segment();
        seg2.setIndex(2);
        seg2.setInitialHeight(10);
        seg2.setLength(2.4);
        seg2.setAngle(10);
        seg2.setMinimumVelocity(50);
        seg2.setMaximumVelocity(100);
        seg2.setMaximumNumberVehicles(4);
        segList.addSegment(seg1);
        segList.addSegment(seg2);

        String expResult = ""
                + "\t<li>Road name: A1</li>\n"
                + "\t<li>Typology: Autoestrada</li>\n"
                + "\t<li>Toll: 6.5</li>\n"
                + "\t<li>Wind speed: 20.0 m/s</li>\n"
                + "\t<li>Wind orientation: 45.0º</li>\n"
                + "\t<li>\tList of segments:<ul>\n"
                + "\t\t<li>Segment: 1<ul>\n"
                + "\t\t\t<li>Initial Height: 1.0m</li>\n"
                + "\t\t\t<li>Angle: 20,000º</li>\n"
                + "\t\t\t<li>Lenght: 2,300 m</li>\n"
                + "\t\t\t<li>Minimum velocity: 10.0 m/s</li>\n"
                + "\t\t\t<li>Maximum velocity: 100.0 m/s</li>\n"
                + "\t\t\t<li>Maximum number of vehicles: 5 vehicles</li>\n"
                + "\t\t</li></ul>\n"
                + "\t\t<li>Segment: 2<ul>\n"
                + "\t\t\t<li>Initial Height: 10.0m</li>\n"
                + "\t\t\t<li>Angle: 10,000º</li>\n"
                + "\t\t\t<li>Lenght: 2,400 m</li>\n"
                + "\t\t\t<li>Minimum velocity: 50.0 m/s</li>\n"
                + "\t\t\t<li>Maximum velocity: 100.0 m/s</li>\n"
                + "\t\t\t<li>Maximum number of vehicles: 4 vehicles</li>\n"
                + "\t\t</li></ul>\n"
                + "\t</ul>\n\t</li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

}
