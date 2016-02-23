package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.ImportXML;
import model.Project;
import model.Simulation;
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
public class SimulationDAOTest {

    public SimulationDAOTest() {
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
     * Test of deleteSimulation method, of class SimulationDAO.
     */
    @Test
    public void testDeleteSimulation() throws SQLException {
        System.out.println("deleteSimulation");
        ProjectDAO projectDAO = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        if (projectDAO.isConnectionAlive()) {
            assertTrue("should be true", projectDAO.insertProject(project));
            SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
            String filePathSimulation = "./test/xml/Simulation.xml";
            Simulation simulation = new Simulation(project);
            importXml.importSimulation(simulation, filePathSimulation);
            String originName = simulation.getName();
            if (instance.isConnectionAlive()) {
                assertTrue("should be true", instance.insertSimulation(simulation));
                assertTrue("Should be true", DaoManager.getInstance().getSimulationDAO().deleteSimulation(simulation.getName()) == true);
            } else {
                System.out.println("ERRO!");
            }
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            System.out.println("ERRO");
        }
    }

    /**
     * Test of setConnection method, of class SimulationDAO.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        //Automatically uses setConnection.
        DaoManager.getInstance().getSimulationDAO();
    }

    /**
     * Test of isConnectionAlive method, of class SimulationDAO.
     */
    @Test
    public void testIsConnectionAlive() throws Exception {
        System.out.println("isConnectionAlive");
        SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
        boolean expResult = true;
        boolean result = instance.isConnectionAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of closeConnection method, of class SimulationDAO.
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("closeConnection");
        SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
        instance.closeConnection();
        assertEquals(false, instance.isConnectionAlive());
    }

    /**
     * Test of insertSimulation method, of class SimulationDAO.
     */
    @Test
    public void testInsertSimulation() throws SQLException {
        System.out.println("insertSimulation");
        ProjectDAO projectDAO = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);

        String name = "testProjectDaoProject1";
        project.setName(name);

        if (projectDAO.isConnectionAlive()) {
            assertTrue("should be true", projectDAO.insertProject(project));
            SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
            String filePathSimulation = "./test/xml/Simulation.xml";
            Simulation simulation = new Simulation(project);
            importXml.importSimulation(simulation, filePathSimulation);
            if (instance.isConnectionAlive()) {
                String name1 = "testSimulationDaoSimulation1";
                simulation.setName(name1);
                assertTrue("should be true", instance.insertSimulation(simulation));
                DaoManager.getInstance().getSimulationDAO().deleteSimulation(simulation.getName());
            } else {
                System.out.println("ERRO!");
            }
            DaoManager.getInstance().getProjectDAO().deleteProject(project.getName());
        } else {
            System.out.println("ERRO");
        }
    }

    /**
     * Test of getSimulationList method, of class SimulationDAO.
     */
    @Test
    public void testGetSimulationList() throws SQLException {
        System.out.println("getSimulationList");
        ProjectDAO projectDAO = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        if (projectDAO.isConnectionAlive()) {
            assertTrue("should be true", projectDAO.insertProject(project));
            SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
            String filePathSimulation = "./test/xml/Simulation.xml";
            Simulation simulation = new Simulation(project);
            importXml.importSimulation(simulation, filePathSimulation);
            if (instance.isConnectionAlive()) {
                assertTrue("should be true", instance.insertSimulation(simulation));
                List<String> simulationList = DaoManager.getInstance().getSimulationDAO().getSimulationList(project.getName());
                System.out.println("");
                assertTrue("should be not null!", simulationList != null);
                assertTrue("should be SimulationA.", instance.getSimulationList(project.getName()).get(0).equalsIgnoreCase(project.getName()));

            } else {
                System.out.println("ERRO!");
            }
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            System.out.println("ERRO");
        }
    }

    /**
     * Test of getSimulationByName method, of class SimulationDAO.
     */
    @Test
    public void testGetSimulationByName() throws SQLException {
        System.out.println("getSimulationByName");
        ProjectDAO projectDAO = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        if (projectDAO.isConnectionAlive()) {
            assertTrue("should be true", projectDAO.insertProject(project));
            SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
            String filePathSimulation = "./test/xml/Simulation.xml";
            Simulation simulation = new Simulation(project);
            importXml.importSimulation(simulation, filePathSimulation);
            if (instance.isConnectionAlive()) {
                assertTrue("should be true", instance.insertSimulation(simulation));
                Simulation getS = DaoManager.getInstance().getSimulationDAO().getSimulationByName(simulation.getName(), project);
            } else {
                System.out.println("ERRO!");
            }
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            System.out.println("ERRO");
        }
    }

    /**
     * Test of updateSimulation method, of class SimulationDAO.
     */
    @Test
    public void testUpdateSimulation() throws SQLException {
        System.out.println("updateSimulation");
        ProjectDAO projectDAO = DaoManager.getInstance().getProjectDAO();
        Project project = new Project();
        String filePath = "./test/xml/Project.xml";
        ImportXML importXml = new ImportXML();
        importXml.importProject(project, filePath);
        if (projectDAO.isConnectionAlive()) {
            assertTrue("should be true", projectDAO.insertProject(project));
            SimulationDAO instance = DaoManager.getInstance().getSimulationDAO();
            String filePathSimulation = "./test/xml/Simulation.xml";
            Simulation simulation = new Simulation(project);
            importXml.importSimulation(simulation, filePathSimulation);
            String originName = simulation.getName();
            if (instance.isConnectionAlive()) {
                assertTrue("should be true", instance.insertSimulation(simulation));
                simulation.setName("changed");
                simulation.setDescription("description");
                assertTrue("should be true", DaoManager.getInstance().getSimulationDAO().updateSimulation(simulation, originName));
            } else {
                System.out.println("ERRO!");
            }
            assertTrue("should be true", DaoManager.getInstance().getProjectDAO().deleteProject(project.getName()));
        } else {
            System.out.println("ERRO");
        }
    }
}
