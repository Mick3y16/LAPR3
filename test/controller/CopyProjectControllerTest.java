/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Project;
import model.Simulator;
import model.dao.DaoManager;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author G11
 */
public class CopyProjectControllerTest {

    public CopyProjectControllerTest() {
    }

    @Before
    public void setUp() {
        
    }

    /**
     * Tests the whole process of this controller.
     * CopyProjectController.
     */
    @Test
    public void testFullControllerTest() {
        System.out.println("fullControllerTest");
        Simulator simulator;
        CopyProjectController instance;
        
        simulator = new Simulator();
        
        Project p=new Project();
        simulator.getImportableRegistry().getImportableOfType(".xml").
                importProject(p, "./test/xml/Project.xml");
        simulator.setOpenProject(p);
        
        instance = new CopyProjectController(simulator);
        String oldName = instance.getProjectName();
        String oldDescription = instance.getProjectDescription();
        
        String newName = "TestSet03_V2";
        String newDesc = "5 node test set V3";
        
        instance.setProjectName(newName);
        instance.setProjectDescription(newDesc);
        
        assertTrue("The name of the project should have changed.",!oldName.equals(instance.getProjectName()));
        assertTrue("The description of the project should have changed.",!oldDescription.equals(instance.getProjectDescription()));
        
        assertTrue("The name of the project should be: "+newName,newName.equals(instance.getProjectName()));
        assertTrue("The description of the project should be: "+newDesc,newDesc.equals(instance.getProjectDescription()));
        
        assertTrue("Should have successfully registered.",instance.registerCopy());
        
        //Remove test from database.
        DaoManager.getInstance().getProjectDAO().deleteProject(newName);
    }

}
