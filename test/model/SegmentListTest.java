package model;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class SegmentListTest {

    public SegmentListTest() {
    }

    /**
     * Test of addSegment method, of class SegmentList.
     */
    @Test
    public void testAddSegment() {
        System.out.println("addSegment");
        Segment segment = new Segment();
        SegmentList instance = new SegmentList();

        assertTrue("Should add the segment to the list", instance.addSegment(segment));

        assertFalse("Shouldn't add the segment to the list", instance.addSegment(segment));
    }

    /**
     * Test of removeSegment method, of class SegmentList.
     */
    @Test
    public void testRemoveSegment() {
        System.out.println("removeSegment");
        Segment segment = new Segment();
        SegmentList instance = new SegmentList();
        instance.addSegment(segment);

        assertTrue("Should removed the segment", instance.removeSegment(segment));

        assertFalse("Should not remove the segment as it does not exist", instance.removeSegment(segment));
    }

    /**
     * Test of newSegment method, of class SegmentList.
     */
    @Test
    public void testNewSegment() {
        System.out.println("newSegment");
        SegmentList instance = new SegmentList();
        Segment expResult = new Segment();
        Segment result = instance.newSegment();

        assertTrue("Segments should be the same", expResult.equals(result));
    }

    /**
     * Test of toString method, of class SegmentList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SegmentList instance = new SegmentList();

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

        instance.addSegment(seg1);
        instance.addSegment(seg2);
        String expResult = ""
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
     * Test of iterator method, of class SegmentList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        SegmentList instance = new SegmentList();

        Segment segment = new Segment();
        instance.addSegment(segment);

        Iterator<Segment> iterator = instance.iterator();

        assertTrue("The objects should be the same", iterator.next().equals(segment));

        assertFalse("There should be no more segments", iterator.hasNext());
    }

    /**
     * Test of toStringHTML method, of class SegmentList.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        SegmentList instance = new SegmentList();

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

        instance.addSegment(seg1);
        instance.addSegment(seg2);
        String expResult = ""
                + "\tList of segments:<ul>\n"
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
                + "\t</ul>\n";
        String result = instance.toStringHTML();
        assertEquals(expResult, result);

    }

    /**
     * Test of size method, of class SegmentList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SegmentList instance = new SegmentList();
        int valor = instance.size();
        assertTrue("Result should be 1 but was " + valor, valor == 0);
        instance.addSegment(new Segment());
        valor = instance.size();
        assertTrue("Result should be 1 but was " + valor, valor == 1);
    }
}
