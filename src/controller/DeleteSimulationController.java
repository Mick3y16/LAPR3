package controller;

import java.util.List;
import model.SimulationHandler;
import model.Simulator;

/**
 * Represents an instance of DeleteSimulationController.
 *
 * @author G11
 */
public class DeleteSimulationController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Simulation handler of project.
     */
    private SimulationHandler sh;

    /**
     * List of names of simulations.
     */
    private List<String> nameSimulationList;

    /**
     * Simulation name.
     */
    private String nameSimulation;

    /**
     * Creates an instance of DeleteSimulationController.
     *
     * @param simulator simulator
     */
    public DeleteSimulationController(Simulator simulator) {
        if (simulator.getOpenSimulation() == null) {
            throw new IllegalArgumentException("You must open a simulation before you can delete it!");
        }
        this.simulator = simulator;
    }

    /**
     * Returns the list of existing simulations in the database.
     *
     * @return list of existing simulations
     */
    public List<String> getSimulationList() {
        this.sh = this.simulator.getSimulationHandler();
        this.nameSimulationList = this.sh.getSimulationList(this.simulator.getOpenProject());
        return this.nameSimulationList;
    }

    /**
     * Selects the simulation to clear.
     *
     * @param name name of simulation
     */
    public void selectSimulation(String name) {
        this.nameSimulation = name;
    }

    /**
     * Deletes the open simulation of simulation handler
     *
     * @return true to delete the successful simulation and false if not
     */
    public boolean deleteSimulation() {
        return this.sh.deleteSimulation(this.nameSimulation);
    }
}
