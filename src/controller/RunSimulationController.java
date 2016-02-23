/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.SimulationRun;
import model.Simulator;
import model.VehicleRun;
import model.dao.DaoManager;

/**
 * Class that coordinates the running process of a simulation.
 * @author G11
 */
public class RunSimulationController {
    
    /**
     * The simulator whose this controller is responsible for.
     */
    private Simulator simulator;
    
    /**
     * The active simulation run.
     */
    private SimulationRun simulationRun;
    
    /**
     * Creates an instance of {@link RunSimulationController} with the specified parameters.
     * @param sim ({@link Simulator}) The simulator that holds all the data needed for the use case.
     */
    public RunSimulationController(Simulator sim)
    {
        simulator = sim;
        if (simulator.getOpenSimulation()==null)
        {
            throw new IllegalArgumentException("You must have a opened simulation.");
        }
    }
    
    /**
     * Creates a new simulation run with the specified attributes.
     * @param name (String) The name of the simulation's run.
     * @param startTime (int) The start time for the simulation's run.
     * @param finishTime (int) The finish time for the simulation's run.
     * @param timeStep (int) The time step for the simulation's run.
     * @param method (int) The best mpath method to be used.
     * @return (boolean) True if successfully created and run data is valid.
     */
    public boolean newSimulationRun(String name,int startTime,int finishTime,int timeStep,int method)
    {
        boolean result = false;
        simulationRun = simulator.getOpenSimulation().getRunList().newRun();
        simulationRun.setName(name);
        simulationRun.setStartTime(startTime);
        simulationRun.setFinishTime(finishTime);
        simulationRun.setStepTime(timeStep);
        simulationRun.setSimulation(simulator.getOpenSimulation());
        simulationRun.setMethodType(method);
       
        result = simulationRun.validateIntegrity();
        return result;
    }
    
    /**
     * Start running the simulation.
     */
    public void runSimulation()
    {
        new Thread(new Runnable(){
        
            @Override
            public void run()
            {
                simulationRun.startRun();
            }
        }).start();
    }
    
    /**
     * Checks whether the simulation is running or not.
     * @return (boolean) True if simulation is being ran.
     */
    public boolean isRunning()
    {
        return (simulationRun==null ) ? false : simulationRun.isRunning();
    }
    
    /**
     * Returns the current time of the simulation's run.
     * @return (int) The current time of the simulation. Returns -1 if simulation run
     * hasn't been created yet.
     */
    public int getCurrentTime()
    {
        return (simulationRun==null) ? -1 : simulationRun.getCurrentTime();
    }
    
    /**
     * Returns a summary of the results obtained during the simulation's run.
     * <p>
     * The results come in a CSV format, with the following order:<p>
     * NAME,DURATION,TIME_STEP,ALGORITHM_PATH,NUMBER_VEHICLES_COMPLETED_PATH,
     * NUMBER_VEHICLES_INCOMPLETE.<p>
     * In case the simulation hasn't finished yet, an empty string is returned.
     * @return (String) The summary of the simulation resutls.
     */
    public String getRunSummary()
    {
        return simulationRun.getSummaryResults();
    }
    
    /**
     * Returns a list of the completed vehicles of the simulation run.
     * <p>
     * Each vehicles provides a list of the results in each segment of the run.
     * @return (List&lt;{@link VehicleRun}&gt;) The list of completed vehicle runs.
     * Returns null if the simulation hasn't completed yet.
     */
    public List<VehicleRun> getListCompletedVehicleRuns()
    {
        return simulationRun.getListCompletedVehicleRuns();
    }
    
    /**
     * Returns a list of the vehicles that did not complete their path during the simulation run.
     * <p>
     * Each vehicles provides a list of the results in each segment of the run.
     * @return (List&lt;{@link VehicleRun}&gt;) The list of aborted vehicle runs.
     * Returns null if the simulation hasn't completed yet.
     */
    public List<VehicleRun> getListAbortedVehicleRuns()
    {
        return simulationRun.getListAbortedVehicleRuns();
    }
    
    /**
     * Saves the results obtained from the simulation run onto the database.
     */
    public void saveResults()
    {
        DaoManager.getInstance().getSimulationDAO().insertSimulationRun(this.simulationRun);
        simulationRun.getSimulation().getRunList().addRun(simulationRun);
    }
}
