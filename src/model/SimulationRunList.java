/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of {@link SimulationRun}. 
 * <p>
 * This class has all the methods to deal with a cluster of {@link SimulationRun}.
 * @author G11
 */
public class SimulationRunList {
    
    /**
     * List containing the {@link SimulationRun}s.
     */
    private List<SimulationRun> runList;
    
    /**
     * Creates an instance of {@link SimulationRunList} with null parameters.
     */
    public SimulationRunList()
    {
        this(new ArrayList());
    }
    
    /**
     * Creates an instance of {@link SimulationRunList} with the specified parameters.
     * @param list (List&lgt;{@link SimulationRun}&gt;) A list with values to pre-populate this list.
     */
    public SimulationRunList(List<SimulationRun> list)
    {
        runList=new ArrayList(list);
    }
    
    /**
     * Creates a new instance of {@link SimulationRun}.
     * @return ({@link SimulationRun}) The newly created instance.
     */
    public SimulationRun newRun()
    {
        return new SimulationRun();
    }
    
    /**
     * Returns a list containing the names of all simulation runs available
     * on this list.
     * @return (List&lt;String&gt;) The list of names.
     */
    public List<String> getAllRuns()
    {
        List<String> result = new ArrayList();
        for (SimulationRun element:runList)
        {
            result.add(element.getName());
        }
        return result;
    }
    
    /**
     * Adds a new {@link SimulationRun} to this list.
     * @param sR ({@link SimulationRun}) The simulation run to be added.
     * @return (boolean) True if successfully added.
     */
    public boolean addRun(SimulationRun sR)
    {
        return runList.add(sR);
    }
    
    /**
     * Removes a {@link SimulationRun} from this list.
     * @param sR ({@link SimulationRun}) The simulation run to be removed.
     * @return (boolean) True if successfully removed.
     */
    public boolean deleteRun(SimulationRun sR)
    {
        return runList.remove(sR);
    }
    
    /**
     * Removes a {@link SimulationRun} from this list.
     * @param srName (String) The name of the simulation run to be removed.
     * @return (boolean) True if successfully removed.
     */
    public boolean deleteRun(String srName)
    {
        return runList.remove(new SimulationRun(srName,new Simulation(new Project())));
    }
    
    /**
     * Returns the index-th {@link SimulationRun} of tihs list.
     * @param index (int) The index of the desired {@link SimulationRun} to get.
     * @return ({@link SimulationRun}) The desired simulation run.
     */
    public SimulationRun get(int index)
    {
        return runList.get(index);
    }
    
    /**
     * Returns the size of this list.
     * @return (int) The size of this list.
     */
    public int size()
    {
        return runList.size();
    }
    
    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && other.getClass().equals(getClass());
        if (result)
        {
            SimulationRunList sr = (SimulationRunList) other;
            result = this==sr || (sr.runList.equals(runList));
        }
        return result;
    }
    
    @Override
    public String toString()
    {
        return runList.toString();
    }
}
