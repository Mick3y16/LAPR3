package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class SectionResultsTest {

    public SectionResultsTest() {
    }

    /**
     * Test of getName method and setName method, of class SectionResults.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getSetName");
        SectionResults instance = new SectionResults();
        String expResult = "name";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergy method and setEnergy method, of class SectionResults.
     */
    @Test
    public void testGetSetEnergy() {
        System.out.println("getSetEnergy");
        SectionResults instance = new SectionResults();
        double expResult = 20.0;
        instance.setEnergy(expResult);
        double result = instance.getEnergy();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getToll method and setToll method, of class SectionResults.
     */
    @Test
    public void testGetSetToll() {
        System.out.println("getSetToll");
        SectionResults instance = new SectionResults();
        double expResult = 20.0;
        instance.setToll(expResult);
        double result = instance.getToll();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTimeTravel method, of class SectionResults.
     */
    @Test
    public void testGetSetTimeTravel() {
        System.out.println("getTimeTravel");
        SectionResults instance = new SectionResults();
        double expResult = 20.0;
        instance.setTimeTravel(expResult);
        double result = instance.getTimeTravel();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toString method, of class SectionResults.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SectionResults instance = new SectionResults();
        instance.setName("CandyVille");
        String expResult = "CandyVille";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class SectionResults.
     */
    @Test
    public void testGetSetType() {
        System.out.println("getType");
        SectionResults instance = new SectionResults();
        instance.setType(SectionResults.REAL_EFFICIENT);
        int expResult = SectionResults.REAL_EFFICIENT;
        int result = instance.getType();
        assertEquals(expResult, result);

        try {
            instance.setType(40);
            assertTrue("There should have been an exception", false);
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test of getFuel method, of class SectionResults.
     */
    @Test
    public void testGetSetFuel() {
        System.out.println("getFuel");
        SectionResults instance = new SectionResults();
        instance.setFuel(500);
        double expResult = 500.0;
        double result = instance.getFuel();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toStringHTML method, of class SectionResults.
     */
    @Test
    public void testToStringHTML() {
        System.out.println("toStringHTML");
        SectionResults instance = new SectionResults();
        instance.setEnergy(10);
        instance.setFuel(10);
        instance.setName("A28-Espinho-Aveiro");
        instance.setTimeTravel(10);
        instance.setToll(4);
        instance.setType(2);

        String expResult = "<tr><td>A28-Espinho-Aveiro</td><td>10.0 s</td><td>10.0 J</td><td>4.0 €</td><td>10.0 g</td></tr>\n";
        String result = instance.toStringHTML();
        assertEquals(expResult, result);
    }

}
