package controller;

import java.util.List;
import model.ProjectHandler;
import model.Simulator;

/**
 * Represents an instance of OpenProjectController.
 *
 * @author G11
 */
public class OpenProjectController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Creates an instance of OpenProjectController.
     *
     * @param simulator simulator
     */
    public OpenProjectController(Simulator simulator) {
        this.simulator = simulator;
    }

    /**
     * Returns the list of existing projects in the database.
     *
     * @return list of existing projects
     */
    public List<String> getProjectList() {
        ProjectHandler ph = this.simulator.getProjectHandler();
        return ph.getProjectList();
    }

    /**
     * Changes the project in use by name.
     *
     * @param name name of new project in use
     */
    public String selectProject(String name) {
        this.simulator.setProjectByName(name);
        this.simulator.setOpenSimulation(null);
        return this.simulator.getNameOpenProject();
    }
}
