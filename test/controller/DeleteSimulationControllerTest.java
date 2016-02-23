package controller;

import java.util.List;
import model.Simulator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G11
 */
public class DeleteSimulationControllerTest {

    public DeleteSimulationControllerTest() {
    }

    /**
     * Test of getSimulationList method, of class DeleteSimulationController.
     */
    @Test
    public void testFullClass() {
        System.out.println("testFullClass");
        Simulator sim = new Simulator();
        sim.setProjectByName("projA");

        DeleteSimulationController instance = new DeleteSimulationController(sim);
        fail("failed: the database is too unstable to test");
        assertTrue("Result should be false", instance.getSimulationList() != null);
        List<String> listSim = instance.getSimulationList();

        assertTrue("Result should be 3 but was" + listSim.size(), listSim.size() == 3);
        assertTrue("result should be name but was " + listSim.get(0), listSim.get(0).equals("name"));
        assertTrue("result should be name1 but was " + listSim.get(1), listSim.get(1).equals("name1"));
        assertTrue("result should be name6 but was " + listSim.get(2), listSim.get(2).equals("name6"));

        instance.selectSimulation("name6");
        instance.deleteSimulation();
        listSim = instance.getSimulationList();
        assertTrue("Result should be 2 because delete simulation with name: name6 but was" + listSim.size(), listSim.size() == 2);

    }

}
