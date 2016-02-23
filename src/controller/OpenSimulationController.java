package controller;

import java.util.List;
import model.Project;
import model.Simulation;
import model.SimulationHandler;
import model.Simulator;

/**
 *
 * @author G11
 */
public class OpenSimulationController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Simulation handler of project.
     */
    private SimulationHandler simulationHandler;

    /**
     * Simulation.
     */
    private Simulation simulation;

    /**
     * Open project.
     */
    private Project openProject;

    /**
     * Creates an instance of OpenSimulationController.
     *
     * @param simulator Simulator.
     */
    public OpenSimulationController(Simulator simulator) {
        this.simulator = simulator;

        if (this.simulator.getOpenProject() == null) {
            throw new IllegalArgumentException("You must open a project before you can open a simulation!");
        }
        this.simulationHandler = this.simulator.getSimulationHandler();
        this.openProject = this.simulator.getOpenProject();
    }

    /**
     * Returns the list of existing simulations of the open project.
     *
     * @return List of existing simulations.
     */
    public List<String> getSimulationList() {
        return this.simulationHandler.getSimulationList(this.openProject);
    }

    /**
     * Selects the desire simulation to be the open simulation.
     *
     * @param simulationName Name of the simulation.
     * @return The name of the current simulation.
     */
    public String selectSimulation(String simulationName) {
        this.simulator.getSimulationByName(simulationName);
        return this.simulator.getOpenSimulation().getName();
    }
}
