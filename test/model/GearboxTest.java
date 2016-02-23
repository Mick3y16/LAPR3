package model;

import java.util.Iterator;
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
 * @author G11
 */
public class GearboxTest {

    public GearboxTest() {
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
     * Test of insertGearAndGearRatio method, of class Gearbox.
     */
    @Test
    public void testInsertGearAndGearRatio() {
        System.out.println("insertGearAndGearRatio");
        Gearbox instance = new Gearbox();
        instance.insertGearAndGearRatio(1, 3.2);

        assertTrue("The gear should exist and its ratio should be 3.2", instance.getGearRatioOfGear(1) == 3.2);
    }

    /**
     * Test of hasGear method, of class Gearbox.
     */
    @Test
    public void testHasGear() {
        System.out.println("hasGear");
        Gearbox instance = new Gearbox();

        instance.insertGearAndGearRatio(1, 3.2);
        assertTrue("The gear should exist", instance.hasGear(1));

        assertFalse("The gear shouldn't exist", instance.hasGear(2));
    }

    /**
     * Test of getGearRatioOfGear method, of class Gearbox.
     */
    @Test
    public void testGetGearRatioOfGear() {
        System.out.println("getGearRatioOfGear");
        Gearbox instance = new Gearbox();

        instance.insertGearAndGearRatio(1, 3.2);
        assertTrue("The gear should exist and its ratio should be 3.2", instance.getGearRatioOfGear(1) == 3.2);
    }

    /**
     * Test of getLowestGearRatio method, of class Gearbox.
     */
    @Test
    public void testGetLowestGearRatio() {
        System.out.println("getLowestGearRatio");
        Gearbox instance = new Gearbox();

        instance.insertGearAndGearRatio(1, 3.5);
        instance.insertGearAndGearRatio(2, 2.5);
        instance.insertGearAndGearRatio(3, 1.9);
        instance.insertGearAndGearRatio(4, 1.2);
        instance.insertGearAndGearRatio(5, 0.8);

        assertTrue("Expected 0.8", instance.getLowestGearRatio() == 0.8);
    }

    /**
     * Test of toString method, of class Gearbox.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Gearbox instance = new Gearbox();
        instance.insertGearAndGearRatio(1, 3.5);
        instance.insertGearAndGearRatio(2, 2.7);

        String expResult = ""
                + "\tGearbox:\n"
                + "\t\t1 - Ratio: 3.5\n"
                + "\t\t2 - Ratio: 2.7\n";

        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringHTML method, of class Gearbox.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        Gearbox instance = new Gearbox();
        instance.insertGearAndGearRatio(1, 3.5);
        instance.insertGearAndGearRatio(2, 2.7);

        String expResult = ""
                + "\t<li>Gearbox:<ul>\n"
                + "\t\t<li>1 - Ratio: 3.5</li>\n"
                + "\t\t<li>2 - Ratio: 2.7</li>\n"
                + "\t</ul></li>\n";

        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateGearBox method, of class Gearbox.
     */
    @Test
    public void testValidateGearBox() {
        System.out.println("validateGearBox");
        Gearbox instance = new Gearbox();

        boolean result = false;

        try {
            result = instance.validateGearBox();
            assertFalse("", result);

            instance.insertGearAndGearRatio(1, 0);
            result = instance.validateGearBox();
            assertTrue("", result);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        instance.insertGearAndGearRatio(1, 2.1);
        result = instance.validateGearBox();
        assertTrue("", result);
    }

    /**
     * Test of entrySet method, of class Gearbox.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");
        Gearbox instance = new Gearbox();

        instance.insertGearAndGearRatio(1, 3.5);
        instance.insertGearAndGearRatio(2, 2.5);
        instance.insertGearAndGearRatio(3, 1.9);
        instance.insertGearAndGearRatio(4, 1.2);
        instance.insertGearAndGearRatio(5, 0.8);

        Set<Map.Entry<Integer, Double>> result = instance.entrySet();
        
        Iterator<Entry<Integer, Double>> iterator = result.iterator();
        
        Entry<Integer, Double> entry = iterator.next();
        assertTrue("1st gear should have 3.5 ratio", entry.getKey() == 1 && entry.getValue() == 3.5);
        
        entry = iterator.next();
        assertTrue("2nd gear should have 2.5 ratio", entry.getKey() == 2 && entry.getValue() == 2.5);
        
        entry = iterator.next();
        assertTrue("3rd gear should have 1.9 ratio", entry.getKey() == 3 && entry.getValue() == 1.9);
        
        entry = iterator.next();
        assertTrue("4th gear should have 1.2 ratio", entry.getKey() == 4 && entry.getValue() == 1.2);
        
        entry = iterator.next();
        assertTrue("5th gear should have 0.8 ratio", entry.getKey() == 5 && entry.getValue() == 0.8);
        
        assertFalse("Should have no more entrys", iterator.hasNext());
    }

}
