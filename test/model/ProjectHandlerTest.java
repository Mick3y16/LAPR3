package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DaoManager;
import model.dao.ProjectDAO;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Represents an instance of ProjectHandlerTest.
 *
 * @author G11
 */
public class ProjectHandlerTest {

    public ProjectHandlerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test of newProject method, of class ProjectHandler.
     */
    @Test
    public void testNewProject() {
        System.out.println("newProject");
        ProjectHandler instance = new ProjectHandler();

        assertTrue("should be not null", instance.newProject() != null);

        Project p = new Project();
        String expResult = p.getClass().getSimpleName();
        String result = instance.newProject().getClass().getSimpleName();
        assertTrue("should be a same class", result.equalsIgnoreCase(expResult));
    }

    /**
     * Test of addProject method, of class ProjectHandler.
     */
    @Test
    public void testAddProject() throws SQLException {
        System.out.println("addProject");
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        String name = "testAddProject";
        project.setName(name);
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = true;
        boolean result = instance.addProject(project);
        assertEquals(expResult, result);

        ProjectDAO projectDao = DaoManager.getInstance().getProjectDAO();
        if (projectDao.isConnectionAlive()) {
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            System.out.println("ERRO");
        }
    }

    /**
     * Test of getProjectList method, of class ProjectHandler.
     */
    @Test
    public void testGetProjectList() {
        System.out.println("getProjectList");

        ProjectHandler instance = new ProjectHandler();
        /*ArrayList<String> expResult = new ArrayList();
        expResult.add("projA");
        expResult.add("projB");
        expResult.add("projC");
        expResult.add("TestSet02");
        expResult.add("DepreProject");
        expResult.add("TestSet01");
        expResult.add("TestCopy");
        expResult.add("NATestDiff");
        expResult.add("NATestDiff2");
        expResult.add("g_test_quadrado");
        expResult.add("Grafo");
        List<String> projList = instance.getProjectList();
        //fail("failed: the database is too unstable to test");
        assertTrue("should be not null!", projList != null);

        assertTrue("Size should be " + expResult.size() + " but was " + projList.size(), expResult.size() == projList.size());

        for (int i = 0; i < expResult.size(); i++) {
            String a = expResult.get(i);
            String b = projList.get(i);
            assertTrue("Next, should be " + a + " but was " + b, a.equals(b));
        }*/
        
        List<String> projList = instance.getProjectList();
        
        assertTrue("The list should not be empty.",!projList.isEmpty());
    }

    /**
     * Test of registerCopy method, of class ProjectHandler.
     */
    @Test
    public void testRegisterCopy() throws SQLException {
        System.out.println("registerCopy");
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        String name = "testRegisterCopyProject";
        project.setName(name);
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = true;
        boolean result = instance.registerCopy(project);
        assertEquals(expResult, result);
        ProjectDAO projectDao = DaoManager.getInstance().getProjectDAO();
        if (projectDao.isConnectionAlive()) {
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            assertTrue("Connection should be alive",false);
        }
    }

    /**
     * Test of registerChanges method, of class ProjectHandler.
     */
    @Test
    public void testRegisterChanges() throws SQLException {
        System.out.println("registerChanges");
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        Project oldP = new Project(project);
        String name = "testRegisterCopyProject";
        oldP.setName(name);
        String nameNew = "testRegisterCopyProjectChanged";
        project.setName(nameNew);
        project.setDescription("description");
        ProjectHandler instance = new ProjectHandler();
        boolean expResult = true;
        instance.addProject(oldP);
        boolean result = instance.registerChanges(project, oldP);
        assertEquals(expResult, result);
        ProjectDAO projectDao = DaoManager.getInstance().getProjectDAO();
        if (projectDao.isConnectionAlive()) {
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            assertTrue("Connection should be alive",false);
        }
    }

}
