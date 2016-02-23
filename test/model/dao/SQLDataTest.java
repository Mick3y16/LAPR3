/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Project;
import model.Simulator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbraga
 */
public class SQLDataTest {
    
    public SQLDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setValue method, of class SQLData.
     */
    @Test
    public void testSetGetValue() {
        System.out.println("setValue");
        Object value = new Project();
        SQLData instance = new SQLData();
        instance.setValue(value);
        assertEquals(value,instance.getValue());
    }

    /**
     * Test of setClazz method, of class SQLData.
     */
    @Test
    public void testSetGetClazz() {
        System.out.println("setClazz");
        Class clazz = Simulator.class;
        SQLData instance = new SQLData();
        instance.setClazz(clazz);
    }
    
}
