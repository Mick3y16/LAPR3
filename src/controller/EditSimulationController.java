package controller;

import model.Simulation;
import model.Simulator;

/**
 *
 * @author G11
 */
public class EditSimulationController {

    /**
     * Simulator.
     */
    private Simulator simulator;
    
    /**
     * The clone of the simulation.
     */
    private Simulation clone;

    /**
     * Creates an instance of EditSimulationController.
     *
     * @param simulator Simulator.
     */
    public EditSimulationController(Simulator simulator) {
        this.simulator = simulator;
        if(this.simulator.getOpenSimulation() == null) {
            throw new IllegalArgumentException("You must open a simulation before you can edit it!");
        }
        this.clone = new Simulation(simulator.getOpenSimulation());
    }

    /**
     * Returns the simulation's name.
     *
     * @return (String) The simulation's name.
     */
    public String getSimulationName() {
        return this.clone.getName();
    }

    /**
     * Returns the simulation's description.
     *
     * @return (String) The simulation's description.
     */
    public String getSimulationDesription() {
        return this.clone.getDescription();
    }

    /**
     * Sets the simulation's name.
     *
     * @param name (String) The new name of the simulation.
     */
    public void setSimulationName(String name) {
        this.clone.setName(name);
    }

    /**
     * Sets the simulation's description.
     *
     * @param description (String) The new description of the simulation.
     */
    public void setSimulationDescription(String description) {
        this.clone.setDescription(description);
    }

    /**
     * Registers the changes made to the simualtion.
     *
     * @return (boolean) Returns true if successfully changed.
     */
    public boolean registerChanges() {
        if(this.simulator.getSimulationHandler().registerChanges(this.clone, this.simulator.getOpenSimulation().getName())) {
            this.simulator.setOpenSimulation(this.clone);
            if(this.simulator.getOpenSimulation().equals(this.clone)) {
                return true;
            }
        }
        return false;
    }
}
