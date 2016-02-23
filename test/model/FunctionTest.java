/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class FunctionTest {
    
    public FunctionTest() {
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
     * Test of getExpression method, of class Function.
     */
    @Test
    public void testGetSetExpression() {
        System.out.println("getExpression");
        Function instance = new Function();
        instance.setExpression("2 * x");
        String expResult = "2 * x";
        String result = instance.getExpression();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVariables method, of class Function.
     */
    @Test
    public void testGetSetVariables() {
        System.out.println("getVariables");
        Function instance = new Function();
        instance.setExpression("2 * x - ab + y - z");
        String[] expResult = {"x","ab","y","z"};
        String[] result = instance.getVariables();
        assertEquals(Arrays.toString(expResult), Arrays.toString(result));
    }

    /**
     * Test of getFunctionValueFor method, of class Function.
     */
    @Test
    public void testGetFunctionValueFor() {
        System.out.println("getFunctionValueFor");
        Double[] varValues = {5.0,2.0,3.0};
        Function instance = new Function();
        instance.setExpression("x * y ^ z");
        Double expResult = 40d;
        Double result = instance.getFunctionValueFor(varValues);
        assertEquals(expResult, result);
    }

    /**
     * Test of replaceVarsWithValues method, of class Function.
     */
    @Test
    public void testReplaceVarsWithValues() {
        System.out.println("replaceVarsWithValues");
        Double[] varValues = {1.0,2.0,3.0};
        Function instance = new Function();
        instance.setExpression("x + y * z");
        String expResult = "1.0 + 2.0 * 3.0 ";
        String result = instance.replaceVarsWithValues(varValues);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVarsFromExpression method, of class Function.
     */
    @Test
    public void testGetVarsFromExpression() {
        System.out.println("getVarsFromExpression");
        String exp = "a + b * 2 * t";
        Function instance = new Function();
        String[] expResult = {"a","b","t"};
        String[] result = instance.getVarsFromExpression(exp);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of shuntingYard method, of class Function.
     */
    @Test
    public void testShuntingYard() {
        System.out.println("shuntingYard");
        String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        String expResult = "3 4 2 * 1 5 - 2 3 ^ ^ / + ";
        String result = Function.shuntingYard(infix);
        assertEquals(expResult, result);
    }

    /**
     * Test of evalRPN method, of class Function.
     */
    @Test
    public void testEvalRPN() {
        System.out.println("evalRPN");
        String postfix = "3 4 2 * 1 5 - 2 3 ^ ^ / + ";
        Double expResult = 3.00012207;
        Double result = Function.evalRPN(postfix);
        assertEquals(String.format("%.9f",expResult), String.format("%.9f",result));
    }

    /**
     * Test of equals method, of class Function.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other = null;
        Function instance = new Function();
        instance.setExpression("2 * x");
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
        
        other = "This should not pass";
        result = instance.equals(other);
        assertEquals(expResult,result);
        
        other = new Function("3 * x");
        result = instance.equals(other);
        assertEquals(expResult,result);
        
        expResult=true;
        other = new Function("2 * x");
        result = instance.equals(other);
        assertEquals(expResult,result);
    }

    /**
     * Test of toString method, of class Function.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Function instance = new Function("2 * x / y");
        String expResult = "f[x, y]=2 * x / y";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
