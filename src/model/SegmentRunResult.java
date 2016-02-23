/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents the run results of a segment.
 * @author G11
 */
public class SegmentRunResult {
    
    /**
     * The section's name.
     */
    private String name;
    /**
     * The instant the vehicle got in the segment.
     */
    private int instantIn;
    
    /**
     * The instant the vehicle got out of the segment.
     */
    private int instantOut;
    
    /**
     * The energy spent in the segment.
     */
    private double energySpent;
    
    /**
     * Creates an instance of {@link SegmentRunResult} with null parameters.
     */
    public SegmentRunResult()
    {
        
    }

    /**
     * Returns the instant that the vehicle got in the segment.
     * @return (int) The instant in.
     */
    public int getInstantIn() {
        return instantIn;
    }

    /**
     * Sets the isntant that the vehicle got in the segment.
     * @param instantIn (int) The instant that the vehicle got in the segment.
     */
    public void setInstantIn(int instantIn) {
        this.instantIn = instantIn;
    }

    /**
     * Returns the instant that the vehicle got out of the segment.
     * @return (int) The instant out.
     */
    public int getInstantOut() {
        return instantOut;
    }

    /**
     * Sets the instant that the vehicle got out of the segment.
     * @param instantOut (int) The instant that the vehicle got out of the segment.
     */
    public void setInstantOut(int instantOut) {
        this.instantOut = instantOut;
    }

    /**
     * Returns the energy spent in the segment.
     * @return (double) The energy spent.
     */
    public double getEnergySpend() {
        return energySpent;
    }

    /**
     * Sets the energy spent in the segment.
     * @param energySpent (double) The energy spent in the segment.
     */
    public void setEnergySpend(double energySpent) {
        this.energySpent = energySpent;
    }
    
    /**
     * Returns the time spent in this segment.
     * @return (int) The time spent in this segment.
     */
    public int getElapsedTime()
    {
        return instantOut-instantIn;
    }

    /**
     * Returns the name of the segment.
     * @return (String) The name of the segment.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the segment.
     * @param name (String) The new name of the segment.
     */
    public void setName(String name) {
        this.name = name;
    }
}
