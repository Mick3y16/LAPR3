package model;

import java.util.List;
import model.dao.DaoManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class SimulatorTest {

    public SimulatorTest() {
    }

    /**
     * Test of getProjectHandler method, of class Simulator.
     */
    @Test
    public void testGetProjectHandler() {
        System.out.println("getProjectHandler");
        Simulator instance = new Simulator();
        Class<? extends ProjectHandler> expResult = new ProjectHandler().getClass();
        Class<? extends ProjectHandler> result = instance.getProjectHandler().getClass();

        assertTrue("Both classes should be the same.", result.toString().equals(expResult.toString()));
    }

    /**
     * Test of getImportableRegistry method, of class Simulator.
     */
    @Test
    public void testGetImportableRegistry() {
        System.out.println("getImportableRegistry");
        Simulator instance = new Simulator();
        Class<? extends ImportableRegistry> expResult = new ImportableRegistry().getClass();
        Class<? extends ImportableRegistry> result = instance.getImportableRegistry().getClass();

        assertTrue("Both classes should be the same.", result.toString().equals(expResult.toString()));
    }

    /**
     * Test of getExportableRegistry method, of class Simulator.
     */
    @Test
    public void testGetExportableRegistry() {
        System.out.println("getExportableRegistry");
        Simulator instance = new Simulator();
        Class<? extends ExportableRegistry> expResult = new ExportableRegistry().getClass();
        Class<? extends ExportableRegistry> result = instance.getExportableRegistry().getClass();

        assertTrue("Both classes should be the same.", result.toString().equals(expResult.toString()));
    }

    /**
     * Test of getSimulationHandler method, of class Simulator.
     */
    @Test
    public void testGetSimulationHandler() {
        System.out.println("getSimulationHandler");
        Simulator instance = new Simulator();
        assertFalse("Result should be false", instance.getSimulationHandler() == null);

        String result = instance.getSimulationHandler().getClass().getSimpleName();
        assertTrue("Result should be true", result.equalsIgnoreCase("SimulationHandler"));

    }

    /**
     * Test of getOpenProject method, of class Simulator.
     */
    @Test
    public void testGetOpenProjectAndName() {
        System.out.println("getOpenProjectAndName");
        Simulator instance = new Simulator();
        assertTrue("Result should be false", instance.getOpenProject() == null);
        
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String pname = "testGetOpenProject";
        project.setName(pname);
        
        instance.setOpenProject(project);
        
        Project p = instance.getOpenProject();
        
        assertTrue("Should be "+pname+" but was " + p.getName(), p.getName().equals(pname));
        
        assertTrue("Name returned by getOpenProjectName should be "+pname+" but was " + instance.getNameOpenProject()
                , instance.getNameOpenProject().equals(pname));

    }

    /**
     * Test of setSimulationByName method, of class Simulator.
     */
    @Test
    public void testSetGetSimulationByNameAndProjectByName() {
        System.out.println("setSimulationByNameAndProjectByName");
        Simulator instance = new Simulator();
        
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String pname = "testSetSimulationByName";
        project.setName(pname);
        
        if (DaoManager.getInstance().getProjectDAO().insertProject(project))
        {

            instance.setProjectByName(pname);
            assertTrue("Result should be true", instance.getOpenSimulation() == null);
            Simulation sim = new Simulation(project);
            sim.setName(pname);
            
            DaoManager.getInstance().getSimulationDAO().insertSimulation(sim);

            instance.getSimulationByName(pname);
            DaoManager.getInstance().getSimulationDAO().deleteSimulation(sim.getName());
            
            DaoManager.getInstance().getProjectDAO().deleteProject(pname);
            
            assertTrue("Result should be true", instance.getOpenSimulation().getName().equals(pname));
            
            
        }
        else
        {
            fail("Failed to insert the project.");
        }
    }

    /**
     * Test of getOpenSimulation method, of class Simulator.
     */
    @Test
    public void testGetAndSetOpenSimulation() {
        System.out.println("getAndSetOpenSimulation");
        Simulator instance = new Simulator();
        assertTrue("Result should be true", instance.getOpenSimulation() == null);
        Project p = new Project();
        p.setName("projA");

        Simulation sim = new Simulation(p);
        sim.setName("name");
        instance.setOpenSimulation(sim);

        String nameSimulation = instance.getOpenSimulation().getName();
        assertTrue("Should be name but was " + nameSimulation, nameSimulation.equals("name"));

        sim.setName("name2");
        instance.setOpenSimulation(sim);
        nameSimulation = instance.getOpenSimulation().getName();
        assertTrue("Should be name2 but was " + nameSimulation, nameSimulation.equals("name2"));

    }

}
