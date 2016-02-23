package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class JunctionTest {
    
    public JunctionTest() {
    }

    /**
     * Test of getName method, of class Junction.
     */
    @Test
    public void testSetAndGetName() {
        System.out.println("setAndGetName");
        Junction instance = new Junction();
        
        try {
            instance.setName(null);
            assertTrue("An exception should have been thrown as the name is null", false);
        } catch (IllegalArgumentException ex) {
            
        }
        
        try {
            instance.setName("");
            assertTrue("An exception should have been thrown as the name is empty", false);
        } catch (IllegalArgumentException ex) {
        }
        
        instance.setName("n01");
        
        
        assertTrue("The name of the juntion should be n01", instance.getName().equals("n01"));
    }

    /**
     * Test of toString method, of class Junction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Junction instance = new Junction();
        instance.setName("n01");
        
        assertTrue("The name of the junction should be n01", instance.toString().equals("n01"));
    }

    /**
     * Test of equals method, of class Junction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Junction instance1 = new Junction();
        Junction instance2 = new Junction();
        
        // Testing equals
        instance1.setName("n01");
        instance2.setName("n01");
        assertTrue("Both junctions should be equal.", instance1.equals(instance2));
        
        // Testing equalsNot
        instance1.setName("n02");
        assertFalse("Both junctions shouldn't be equal.", instance1.equals(instance2));
    }

    /**
     * Test of validate method, of class Junction.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Junction instance = new Junction();
        
        try {
            instance.validate();
            assertTrue("An exception should have been thrown as the junction is not valid yet", false);
        } catch (IllegalArgumentException ex) {
        }
        
        instance.setName("n01");
        
        assertTrue("The junction should be valid", instance.validate());
    }
    
}
