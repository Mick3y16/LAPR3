package controller;

import model.Project;
import model.Simulation;
import model.SimulationHandler;
import model.Simulator;

/**
 *
 * @author G11
 */
public class CopySimulationController {

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
     * Creates an instance of CopySimulationController.
     *
     * @param simulator Simulator.
     */
    public CopySimulationController(Simulator simulator) {
        this.simulator = simulator;
        this.simulationHandler = this.simulator.getSimulationHandler();
        if(this.simulator.getOpenSimulation() == null) {
            throw new IllegalArgumentException("You must open a simulation before you can edit it!");
        }
        this.openProject = this.simulator.getOpenProject();
        this.simulation = new Simulation(this.simulator.getOpenSimulation());
    }

    /**
     * Gets the name of the simulation.
     * 
     * @return The name of the simulation. 
     */
    public String getName() {
        return this.simulation.getName();
    }

    /**
     * Gets the description of the simulation.
     * 
     * @return The description of the simulation. 
     */
    public String getDescription() {
        return this.simulation.getDescription();
    }

    /**
     * Sets the name of the simulation.
     *
     * @param name New name.
     */
    public void setName(String name) {
        this.simulation.setName(name);
    }

    /**
     * Sets the description of the simulation.
     *
     * @param description New Description.
     */
    public void setDescription(String description) {
        this.simulation.setDescription(description);
    }

    /**
     * Registers the copy of the project.
     *
     * @return Returns true if successfully changed.
     */
    public boolean registerCopy() {
        return this.simulationHandler.registerCopy(this.simulation);
    }
}
