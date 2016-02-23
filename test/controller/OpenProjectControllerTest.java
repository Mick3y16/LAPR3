package controller;

import java.util.ArrayList;
import java.util.List;
import model.Simulator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class OpenProjectControllerTest {
    
    public OpenProjectControllerTest() {
    }

    /**
     * Test of getProjectList method, of class OpenProjectController.
     */
    @Test
    public void testFullOpenProjectControllerTest() {
        System.out.println("getProjectList");
        Simulator sim = new Simulator();
        sim.setProjectByName("projA");
        OpenProjectController instance = new OpenProjectController(sim);
        ArrayList<String> expResult = new ArrayList();
        expResult.add("projA");
        expResult.add("projB");
        expResult.add("projC");
        List<String> projList = instance.getProjectList();
        fail("failed: the database is too unstable to test");
        assertTrue("should be not null!", projList != null);
        
        assertTrue("Size should be " + expResult.size() + " but was " + projList.size(), expResult.size() == projList.size());
        
        for (int i = 0; i < expResult.size(); i++) {
            String a = expResult.get(i);
            String b = projList.get(i);
            assertTrue("Next, should be " + a + " but was " + b, a.equals(b));
        }
        
        String name2 = "projA";
        String name = instance.selectProject(name2);
        assertTrue("Result should be projA was " + name, name.equals(name2));
    }

  
    
}
