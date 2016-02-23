/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.ImportXML;
import model.Project;
import model.Simulator;
import model.dao.DaoManager;
import model.dao.ProjectDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class EditProjectControllerTest {

    EditProjectController instance;
    Simulator sim;

    public EditProjectControllerTest() {
    }

    @Before
    public void setUp() {
        sim = new Simulator();
        //instance = new EditProjectController(sim, sim.getProjectHandler());
    }

    /**
     * Test of getProjectName method, of class EditProjectController.
     */
    @Test
    public void testFullController() {
        System.out.println("testFullController");
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        project.setName("proj_teste_1");

        DaoManager.getInstance().getProjectDAO().insertProject(project);
        try {
            instance = new EditProjectController(sim, sim.getProjectHandler());
        } catch (IllegalArgumentException ex) {
        }
        sim.setProjectByName("proj_teste_1");
        instance = new EditProjectController(sim, sim.getProjectHandler());

        String name = instance.getProjectName();
        assertTrue("The name project should be proj_teste_1 but was" + name, name.equals("proj_teste_1"));

        name = instance.getProjectDescription();
        assertTrue("The description project should be 5 node test set but was" + name, name.equals("5 node test set"));

        instance.setProjectName("proj_teste_1_edit");
        name = instance.getProjectName();
        assertTrue("The name project should be proj_teste_1_edit but was" + name, name.equals("proj_teste_1_edit"));

        instance.setProjectDescription("Description_edit");
        name = instance.getProjectDescription();
        assertTrue("The description project should be Description_edit but was" + name, name.equals("Description_edit"));

        //import roads
        filePath = "./test/xml/Network.xml";
        assertTrue("Result should be true", importXml.importRoads(project, filePath));
        assertTrue("Result should be " + instance.getRoadsList().size(), instance.getRoadsList().size() == 12);

        filePath = "./test/xml/Vehicles.xml";
        assertTrue("Result should be true", importXml.importVehicles(project, filePath));
        assertTrue("Result should be " + instance.getVehiclesList().size(), instance.getVehiclesList().size() == 3);

        assertTrue("Result should be true", instance.registerChanges());

       assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject("proj_teste_1"));
       assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject("proj_teste_1_edit"));

    }


}
