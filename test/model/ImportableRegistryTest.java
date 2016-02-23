package model;

import java.util.ArrayList;
import java.util.List;
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
public class ImportableRegistryTest {
    
    public ImportableRegistryTest() {
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
     * Test of getImportableOfType method, of class ImportableRegistry.
     */
    @Test
    public void testGetImportableByType() {
        System.out.println("getImportableByType");
        String path = "documents/netBeans/helloxml";
        ImportableRegistry instance = new ImportableRegistry();
        
        try {
            instance.getImportableOfType(path);
            assertFalse("An exception should have been thrown since the path has no file type.", true);
        } catch (IllegalArgumentException ex) {
        }
        
        
        path = "documents/netBeans/hello.xml";
        Class<? extends ImportXML> expResult = new ImportXML().getClass();
        Class<? extends Importable> result = instance.getImportableOfType(path).getClass();
        
        assertTrue("Both classes should be the same.", result.toString().equals(expResult.toString()));
        
        path = "documents/netBeans/hello.doc";
        try {
            instance.getImportableOfType(path);
            assertFalse("An exception should have been thrown since the file type importer does not exists.", true);
        } catch(IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getListOfImportables method, of class ImportableRegistry.
     */
    @Test
    public void testGetListOfImportables() {
        System.out.println("getListOfImportables");
        ImportableRegistry instance = new ImportableRegistry();
        List<String> expResult = new ArrayList();
        expResult.add(".xml");
        List<String> result = instance.getListOfImportables();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImportableOfType method, of class ImportableRegistry.
     */
    @Test
    public void testGetImportableOfType() {
        System.out.println("getImportableOfType");
        String path = "./test_sets/TestSet01_Network.xml";
        ImportableRegistry instance = new ImportableRegistry();
        Importable result = instance.getImportableOfType(path);
        assertEquals(ImportXML.class, result.getClass());
    }
    
}
