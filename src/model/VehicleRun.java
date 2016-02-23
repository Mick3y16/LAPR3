/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static model.NetworkAnalysis.getForceInSegment;

/**
 * Represents a vehicle during a simulation's run.
 * @author G11
 */
public class VehicleRun {
    
    /**
     * The vehicle's unique ID.
     */
    private int uniqueID;
    
    /**
     * The vehicle that this {@link VehicleRun} represents.
     */
    private Vehicle vehicle;
    
    /**
     * The path this vehicle has to go through.
     */
    private Iterator<Section> path;
    
    /**
     * The segment iterator of this vehicle.
     */
    private Iterator<Segment> segmentPath;

    /**
     * The current section where this vehicle is positioned.
     */
    private Section currentSection;
    
    /**
     * The next segment this vehicle has to go to.
     */
    private Segment next;
    
    /**
     * The current segment this vehicle is positioned.
     */
    private Segment current;
    
    /**
     * Time left to go to the next segment.
     */
    private double timeLeft;
    
    /**
     * Energy used on a segment.
     */
    private double energy;
    
    /**
     * The list of results obtained in each segment.
     */
    private List<SegmentRunResult> results;
    
    /**
     * The current result to be obtaiend in the current segment.
     */
    private SegmentRunResult srr;
    
    /**
     * Whether this vehicle has completed its path and is no longer accepting
     * any orders.
     */
    private boolean ended;
    
    /**
     * The starting node of this vehicle's path.
     */
    private Junction beginNode;
    
    /**
     * The finishing node of this vehicle's path.
     */
    private Junction endNode;
    
    /**
     * The time that this vehicle was dropped out, in case the vehicle was unsuccessfully injected.
     */
    private int dropoutTime;
    
    /**
     * Creates an instance of {@link VehicleRun} with null parameters.
     * Private access means only this class can create empty instances. (used in
     * loadFromDatabase()).
     */
    private VehicleRun()
    {
        
    }
    
    /**
     * Creates an instance of {@link VehicleRun} with the specified parameters.
     * @param vehicle ({@link Vehicle}) The vehicle that this vehicle run represents.
     * @param path (Iterator&lt;{@link Section}&gt;) An iterator with all the sections
     * this vehicle has to go through.
     * @param currTime (int) The current time of the simulation run.
     */
    public VehicleRun(Vehicle vehicle,Iterator<Section> path,int currTime)
    {
        this.vehicle=vehicle;
        this.path=path;
        next=null;
        currentSection=path.next();
        segmentPath=currentSection.getSegmentList().iterator();
        current=segmentPath.next();
        results=new ArrayList();
        dropoutTime=-1;
        srr=new SegmentRunResult();
        srr.setInstantIn(currTime);
        srr.setName(currentSection.getRoadName()+"-"+current.getIndex());
        calculateSegmentBehaviour();
        ended=false;
    }
    
    /**
     * Returns the name of the vehicle that this vehiclerun represents-
     * @return (String) The vehicle name.
     */
    public String getVehicleName()
    {
        return vehicle.getName();
    }
    
    /**
     * Checks if the vehicle has reached the end of the path.
     * @return (boolean) True if successful.
     */
    public boolean hasReachedEnd()
    {
        return !path.hasNext() && !segmentPath.hasNext();
    }
    
    /**
     * Check if this {@link VehicleRun} can move to the next segment.
     * <p>
     * If the vehicle can move, the segment's unique simulation run ID is returned.
     * Elsewise, an empty string is returned.
     * @param carsLeft (Map&lt;String,Integer&gt;) A map containing the number of
     * free cars spaces left in a segment.
     * @param currTime (int) The current time of the simulation.
     * @return (String) The segment's unique simulation run ID. An empty string is returned
     * if the vehicle cannot move.
     */
    public String canMoveToNext(Map<String,Integer> carsLeft,int currTime)
    {
        String result ="";
        Section lastSection = null;
        if (!segmentPath.hasNext())
        {
            lastSection=currentSection;
            currentSection = path.next();
            segmentPath=currentSection.getSegmentList().iterator();
        }
        if (next==null)
        {
            next = segmentPath.next();
        }
        String nextKey = currentSection.getRoadName()+"-"+next.getIndex();
        if (carsLeft.get(nextKey)>0)
        {
            //Se a section for a anterior, é mau. CORRIGIR!
            String keyCurr;
            if (lastSection!=null)
            {
                keyCurr=lastSection.getRoadName()+"-"+current.getIndex();
            }
            else
            {
                keyCurr=currentSection.getRoadName()+"-"+current.getIndex();
            }
            carsLeft.put(keyCurr,carsLeft.get(keyCurr)+1);
            
            carsLeft.put(nextKey,carsLeft.get(nextKey)-1);
            
            srr.setEnergySpend(energy);
            srr.setInstantOut(currTime);
            results.add(srr);
            
            //Go to next.
            current = next;
            next = null;
            
            calculateSegmentBehaviour();
            
            result = nextKey;
            srr = new SegmentRunResult();
            srr.setInstantIn(currTime);
            srr.setName(currentSection.getRoadName()+"-"+current.getIndex());
        }        
        return result;
    }
    
    /**
     * Makes sure the vehicle gets to the ends of the desired path.
     * @param currTime (int) The current time of the simulation.
     * @param carsLeft (Map&lt;String,Integer&gt;) A map containing the number of
     * free cars spaces left in a segment.
     */
    public void endVehicleRun(int currTime,Map<String,Integer> carsLeft)
    {
        if (hasReachedEnd())
        {
            String key=currentSection.getRoadName()+"-"+current.getIndex();
            carsLeft.put(key, carsLeft.get(key)+1);
            srr.setEnergySpend(energy);
            srr.setInstantOut(currTime);
            results.add(srr);
            ended=true;
        }
    }
    
    /**
     * Calculates the energy behaviour of a vehicle in the current segment.
     */
    private void calculateSegmentBehaviour()
    {
        List<Integer> throttles = new ArrayList();
        for (Map.Entry<Integer,Throttle> val :vehicle.getAcceleratorPedal().entrySet())
        {
            throttles.add(val.getKey());
        }
        Collections.sort(throttles);
        
        int highestGear=-1;
        for (Map.Entry<Integer,Double> val :vehicle.getGearbox().entrySet())
        {
            if (highestGear<val.getKey())
            {
                highestGear=val.getKey();
            }
        }
        
        double fa=0;
        double vel=0;

        double w=0,torque=0;
        int i;
        int throttleAcc=0;
        
        for (Integer acceleration:throttles)
        {
            for (i=highestGear;i>0;i--)
            {
                fa=getForceInSegment(currentSection, vehicle, current, i);
                vel = NetworkAnalysis.getMaximumVelocityIn(current, vehicle, currentSection, i);
                w = vel*9.54930*vehicle.getFinalDrive()*vehicle.getGearbox().getGearRatioOfGear(i)/(vehicle.getWheelSize()*0.5);
                torque = vehicle.getAcceleratorPedal().getTorqueByPercentageAndRPM(acceleration, w);

                double engineForce = torque*vehicle.getFinalDrive()*vehicle.getGearbox().getGearRatioOfGear(i)/(vehicle.getWheelSize()*0.5);

                if (!(engineForce-fa<0 && current.getAngle()>0))
                {
                    throttleAcc=acceleration;
                    break;
                }
            }
            if (throttleAcc!=0)
            {
                break;
            }
        }

        double power = 0.10472*w*torque;
        double elapsedTime = current.getLength()/vel;

        if (vehicle instanceof VehicleElectric && fa<=0)
        {
            energy+=fa*current.getLength(); //Will subtract the necessary energy gained.
        }
        else
        {
            energy=power*elapsedTime;
            timeLeft = elapsedTime*0.0166667;
        }
    }
    
    /**
     * Returns the time left for a vehicle to go to the next section.
     * @return (double) The time left to go to the next section.
     */
    public double getTimeLeft()
    {
        return timeLeft;
    }
    
    /**
     * Subtracts the time that a given vehicle is waiting to go to the next segment.
     * @param timeStep (int) Amount subtracted.
     */
    public void subtractWaitTime(int timeStep)
    {
        timeLeft-=timeStep;
    }
    
    /**
     * Adds the energy spent whilst waiting to move.
     * @param timeStep (int) The elapsed time.
     */
    public void addWaitingEnergy(int timeStep)
    {
        double angularVelocityWheel = vehicle.getRpmMinimum()
                / (vehicle.getGearbox().getGearRatioOfGear(1) * vehicle.getFinalDrive());

        int lowest=100;
        for (Map.Entry<Integer,Throttle> val :vehicle.getAcceleratorPedal().entrySet())
        {
            if (lowest>val.getKey())
            {
                lowest=val.getKey();
            }
        }
        
        double sumPower = angularVelocityWheel * vehicle.getAcceleratorPedal().getTorqueByPercentageAndRPM(lowest, vehicle.getRpmMinimum()) * 0.10472;
        this.energy+=sumPower*timeStep*60;
    }
    
    /**
     * Returns a list containing the results obtained in each segment during the simulation run.
     * @return (List&lt;{@link SegmentRunResult}&gt;) The list of segment results.
     */
    public List<SegmentRunResult> getResultsInSegment()
    {
        return results;
    }

    /**
     * Returns the starting node of this vehicle's path.
     * @return ({@link Junction}) The starting node.
     */
    public Junction getBeginNode() {
        return beginNode;
    }

    /**
     * Sets the starting node of this vehicle's path.
     * @param beginNode ({@link Junction}) The new starting node of this vehicle's path.
     */
    public void setBeginNode(Junction beginNode) {
        this.beginNode = beginNode;
    }

    /**
     * Returns the ending node of this vehicle's path.
     * @return ({@link Junction}) The ending node.
     */
    public Junction getEndNode() {
        return endNode;
    }

    /**
     * Sets the ending node of this vehicle's path.
     * @param endNode ({@link Junction}) The new ending node of this vehicle's path.
     */
    public void setEndNode(Junction endNode) {
        this.endNode = endNode;
    }

    /**
     * Returns the timestamp that this vehicle dropped out, in case it wasn't
     * successfully injected.
     * @return (int) The dropout time (in minutes).
     */
    public int getDropoutTime() {
        return dropoutTime;
    }

    /**
     * Sets the dropout time of this vehicle.
     * @param dropoutTime (int) The dropout time of this vehicle (in minutes).
     */
    public void setDropoutTime(int dropoutTime) {
        this.dropoutTime = dropoutTime;
    }

    /**
     * Returns this vehicle's unique id in the simulation run.
     * @return (int) The unique id.
     */
    public int getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Sets the inque ID of this vehicle in the simulation run.
     * @param uniqueID (int) The vehicle's unique ID.
     */
    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }
    
    /**
     * Checks if this vehicle run has ended its course.
     * @return (boolean) True if the vehicle has ended.
     */
    public boolean hasEnded()
    {
        return ended;
    }
    
    /**
     * Loads a vehicle from the information retrieved from a database.
     * @param v ({@link Vehicle}) The vehicle that this vehicle run represents.
     * @param begin ({@link Junction}) The starting node.
     * @param end ({@link Junction}) The ending node.
     * @param dropoutTime (int) The time at which this instance was dropped out, in case it wasn't successfully
     * injected.
     * @return ({@link VehicleRun}) The loaded vehicle run.
     */
    public static VehicleRun loadFromDatabase(Vehicle v,Junction begin,Junction end,int dropoutTime)
    {
        VehicleRun vh = new VehicleRun();
        vh.vehicle=v;
        vh.beginNode=begin;
        vh.endNode=end;
        vh.results=new ArrayList<>();
        vh.dropoutTime=dropoutTime;
        return vh;
    }
    
    @Override
    public String toString()
    {
        return "VR ["+uniqueID+"]";
    }
}
