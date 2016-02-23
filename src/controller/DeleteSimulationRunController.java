/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Simulator;
import model.dao.DaoManager;

/**
 * Class that coordinates the deletion of a simulation run.
 * @author G11
 */
public class DeleteSimulationRunController {
    
    /**
     * The simulator that has all the data required.
     */
    private Simulator sim;
    
    /**
     * Creates an instance of {@link DeleteSimulationRunController} with the
     * specified parameters.
     * @param sim ({@link Simulator}) The simulator that has all the data required.
     */
    public DeleteSimulationRunController(Simulator sim)
    {
        this.sim=sim;
        if (sim.getOpenSimulation()==null)
        {
            throw new IllegalArgumentException("You must open a simulation!");
        }
    }
    
    /**
     * Returns a list containing the names of all simulation runs available
     * on the simulation.
     * @return (List&lt;String&gt;) The list of names.
     */
    public List<String> getRunList()
    {
        return sim.getOpenSimulation().getRunList().getAllRuns();
    }
    
    /**
     * Deletes the simulation run with the specified name.
     * @param name (String) The name of the simulation run.
     */
    public boolean deleteSimulationRun(String name)
    {
        boolean result = DaoManager.getInstance().getProjectDAO().deleteSimulationRun(name);//BDDAD METHOD MUST RETURN TRUE
        if (result)
        {
            sim.getOpenSimulation().getRunList().deleteRun(name);
        }
        return result;
    }
}
