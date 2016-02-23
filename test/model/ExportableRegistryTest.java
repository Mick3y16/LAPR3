
package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class ExportableRegistryTest {
    
    public ExportableRegistryTest() {
    }

    /**
     * Test of getExportableType method, of class ExportableRegistry.
     */
    @Test
    public void testGetExportableType() {
    System.out.println("getExportableType");
        String path = "documents/netBeans/hellohtml";
        ExportableRegistry instance = new ExportableRegistry();
        
        try {
            instance.getExportableType(path);
            assertFalse("An exception should have been thrown since the path has no file type.", true);
        } catch (IllegalArgumentException ex) {
        }
        
        
        path = "documents/netBeans/hello.html";
        Class<? extends ExportHTML> expResult = new ExportHTML().getClass();
        Class<? extends Exportable> result = instance.getExportableType(path).getClass();
        
        assertTrue("Both classes should be the same.", result.toString().equals(expResult.toString()));
        
        path = "documents/netBeans/hello.doc";
        try {
            instance.getExportableType(path);
            assertFalse("An exception should have been thrown since the file type exporter does not exists.", true);
        } catch(IllegalArgumentException ex) {
        }
    }
  
}
