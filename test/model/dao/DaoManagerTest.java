package model.dao;

import java.sql.Connection;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Represents an instance of DaoManagerTest.
 *
 * @author G11
 */
public class DaoManagerTest {

    private static final String host="jdbc:oracle:thin:@94.60.237.51:1521:xe",username="LAPR3",password="ABC123";
    
    public DaoManagerTest() {
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
     * Test of getInstance method, of class DaoManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");

        assertTrue("Should be true", DaoManager.getInstance() != null);

        String result = DaoManager.getInstance().getClass().getSimpleName();
        assertTrue("Should be a same class", result.equalsIgnoreCase("DaoManager"));
    }

    /**
     * Test of resetDaoManagerToTest method, of class DaoManager.
     */
    @Test
    public void testResetDaoManagerToTest() {
        System.out.println("resetDaoManagerToTest");
        DaoManager instance = DaoManager.getInstance();
    }

    /**
     * Test of getProjectDAO method, of class DaoManager.
     */
    @Test
    public void testGetProjectDAO() {
        System.out.println("getProjectDAO");
        DaoManager instance = DaoManager.getInstance();

        assertTrue("should be true", instance.getProjectDAO() != null);
        String result = instance.getProjectDAO().getClass().getSimpleName();
        assertTrue("Should be a same class", result.equalsIgnoreCase("ProjectDAO"));

    }

    /**
     * Test of isEnableDBMSonAll method, of class DaoManager.
     */
    @Test
    public void testSetAndIsEnableDBMSonAll() {
        System.out.println("isEnableDBMSonAll");
        DaoManager instance = DaoManager.getInstance();
        boolean expResult = false;
        boolean result = instance.isEnableDBMSonAll();
        assertEquals(expResult, result);

        instance.setEnableDBMSonAll(true);
        expResult = true;
        assertEquals(expResult, instance.isEnableDBMSonAll());
    }

    /**
     * Test of printDBMSOutput method, of class DaoManager.
     */
    @Test
    public void testPrintDBMSOutput() throws Exception {
        System.out.println("printDBMSOutput");
        Connection connection;
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(host);
        connection = ds.getConnection(username, password);
        DaoManager.getInstance().enableDBMSOutput(connection, 20000);
        String expResult = "Sentence 1\nSentence 2\nSentence 3\nSentence 4\nSentence 5\n\n";
        DaoManager.getInstance().callVoidProcedure(connection, "JUnitTestPrintDBMS", new SQLDataBundle());
        String result = DaoManager.getInstance().printDBMSOutput(connection);
        assertEquals(expResult, result);
    }

    /**
     * Test of callVoidProcedure method, of class DaoManager.
     */
    @Test
    public void testCallVoidProcedure() throws Exception {
        System.out.println("callVoidProcedure");
        Connection connection;
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(host);
        connection = ds.getConnection(username, password);
        DaoManager.getInstance().enableDBMSOutput(connection, 20000);
        String command = "JUnitTestVoidProcedure(?)";
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(50,Integer.class));
        DaoManager.getInstance().callVoidProcedure(connection, command, bundle);
        String expResult = "Number sent:50\n\n";
        String result = DaoManager.getInstance().printDBMSOutput(connection);
        
        assertEquals(expResult,result);
    }

    /**
     * Test of enableDBMSOutput method, of class DaoManager.
     */
    @Test
    public void testEnableDBMSOutput() throws Exception {
        System.out.println("enableDBMSOutput");
        DaoManager instance = DaoManager.getInstance();
        OracleDataSource ds = new OracleDataSource();
        String host = "jdbc:oracle:thin:@94.60.237.51:1521:xe";
        String username = "LAPR3";
        String password = "ABC123";
        ds.setURL(host);
        Connection connection = ds.getConnection(username, password);
        int bufferSize = 10000;
        instance.enableDBMSOutput(connection, bufferSize);
    }

  
    /**
     * Test of getSimulationDAO method, of class DaoManager.
     */
    @Test
    public void testGetSimulationDAO() {
        System.out.println("getSimulationDAO");
        DaoManager instance = DaoManager.getInstance();

        assertTrue("should be true", instance.getSimulationDAO() != null);
        String result = instance.getSimulationDAO().getClass().getSimpleName();
        assertTrue("Should be a same class", result.equalsIgnoreCase("SimulationDAO"));
    }

}
