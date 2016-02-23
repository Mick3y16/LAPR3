package controller;

import model.Simulator;
import org.junit.Test;

/**
 *
 * @author G11
 */
public class CreateProjectControllerTest {

    public CreateProjectControllerTest() {
    }

    /**
     * Test of newProject method, of class CreateProjectController.
     */
    @Test
    public void testFullCreateProjectController() {
        System.out.println("FullCreateProjectController");
        Simulator simulator = new Simulator();
        CreateProjectController instance = new CreateProjectController(simulator);
        instance.newProject();
        
        String path = "./test/xml/Project.xml";

        try {
            instance.setPath(path);
        } catch (IllegalArgumentException ex) {
        }
        
        
    }

}
