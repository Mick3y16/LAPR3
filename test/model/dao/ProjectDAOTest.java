package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ImportXML;
import model.Project;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Represents an instance of ProjectDAOTest.
 *
 * @author G11
 */
public class ProjectDAOTest {

    public ProjectDAOTest() {
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
     * Test of insertProject method, of class ProjectDAO.
     */
    @Test
    public void testInsertProject() throws SQLException {
        System.out.println("insertProject");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String name = "testProjectDaoProject";
        project.setName(name);
        
        if (instance.isConnectionAlive()) {
            assertTrue("should be true", instance.insertProject(project));
            DaoManager.getInstance().getProjectDAO().deleteProject(project.getName());
        } else {
            assertTrue("Connection should be alive",false);
        }

    }

    /**
     * Test of getProjectList method, of class ProjectDAO
     */
    @Test
    public void testGetProjectList() {
        System.out.println("getProjectList");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();

        /*
        ArrayList<String> expResult = new ArrayList();
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
     * Test of updateProject method, of class ProjectDAO.
     */
    @Test
    public void testUpdateProject() throws SQLException {
        System.out.println("updateProject");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String name = "testProjectDaoProject";
        project.setName(name);
        String nameChanged = "testProjectDaoProjectChanged";
        
        if (instance.isConnectionAlive()) {
            Project proj2 = new Project(project);
            assertTrue("should be true", instance.insertProject(project));

            proj2.setName(nameChanged);
            proj2.setDescription("descriptionChangedJava");
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().updateProject(proj2, project));
            DaoManager.getInstance().getProjectDAO().deleteProject(proj2.getName());
        } else {
            assertTrue("Connectino should be alive",false);
        }
    }

    /**
     * Test of setConnection method, of class ProjectDAO.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        // Automatically uses setConnection.
        DaoManager.getInstance().getProjectDAO();
    }

    /**
     * Test of isConnectionAlive method, of class ProjectDAO.
     */
    @Test
    public void testIsConnectionAlive() throws Exception {
        System.out.println("isConnectionAlive");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        boolean expResult = true;
        boolean result = instance.isConnectionAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of closeConnection method, of class ProjectDAO.
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("closeConnection");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        instance.closeConnection();
        assertEquals(false, instance.isConnectionAlive());
    }

    /**
     * Test of getProject method, of class ProjectDAO.
     */
    @Test
    public void testGetProject() throws SQLException {
        System.out.println("getProject");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String name = "testProjectDaoProject";
        project.setName(name);
        
        if (instance.isConnectionAlive()) {
            assertTrue("should be true", instance.insertProject(project));
            Project getProject=DaoManager.getInstance().getProjectDAO().getProject(project.getName());
            DaoManager.getInstance().getProjectDAO().deleteProject(project.getName());
            assertTrue("Project should not be null", getProject!=null);
        } else {
            assertTrue("Connection should be alive",false);
        }
//        String name = "projA";
//        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
//        Project proj = instance.getProject(name);
//        assertTrue("should be ", proj.getDescription().equals("descriptionA"));
//        int valor = proj.getJunctions().size();
//
//        assertTrue("should be 2 but was " + valor, valor == 2);
//
//        valor = proj.getSections().size();
//        assertTrue("should be 1 but was " + valor, valor == 1);
//
//        valor = proj.getSections().get(0).getSegmentList().size();
//        assertTrue("should be 2 but was " + valor, valor == 2);
//
//        valor = proj.getVehicleList().size();
//        assertTrue("should be 2 but was " + valor, valor == 3);
//        System.out.println("veiculo 1: " + proj.getVehicleList().getVehicleList().get(0));
//        System.out.println("veiculo 1: " + proj.getVehicleList().getVehicleList().get(1));
//        System.out.println("veiculo 1: " + proj.getVehicleList().getVehicleList().get(2));

    }

    /**
     * Test of deleteProject method, of class ProjectDAO.
     */
    @Test
    public void testDeleteProject() throws SQLException {
        System.out.println("deleteProject");
        ProjectDAO instance = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        
        String name = "testProjectDaoProject";
        project.setName(name);
        
        if (instance.isConnectionAlive()) {
            assertTrue("should be true", instance.insertProject(project));
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            assertTrue("The connection should be alive",false);
        }
    }
}
