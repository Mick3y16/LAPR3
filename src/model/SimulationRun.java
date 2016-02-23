/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static model.NetworkAnalysis.getForceInSegment;
import utils.Tuple;

/**
 * This class represents a simulation's run.
 * @author G11
 */
public class SimulationRun
{
    /**
     * The time when the simulation's run starts.
     */
    private int startTime;
    
    /**
     * The time when the simulation's run finishes.
     */
    private int finishTime;
    
    /**
     * The simulation run's time step.
     */
    private int stepTime;
    
    /**
     * The current time of the simulation run.
     */
    private int currentTime;
    
    /**
     * States whether the simulation run is currently active or not.
     */
    private boolean running;
    
    /**
     * The name of this simulation's run.
     */
    private String name;
    
    /**
     * The {@link Simulation} that this {@link SimulationRun} belongs too.
     */
    private Simulation simulation;
    
    /**
     * The best path method to use to generate the path.
     */
    private int methodType;
    
    /**
     * A map containing the available space.
     */
    private Map<String,Integer> carsLeft;
    
    /**
     * The list of priorities of cars that are waiting in a segment.
     */
    private Map<String,LinkedList<VehicleRun>> heapCars;
    
    /**
     * A map containing the paths for each vehicle.
     */
    private Map<String,LinkedList<Section>> vehiclePaths;
    
    /**
     * The time when each traffic must arrive.
     * <p>
     * The key corresponds to the index of the traffic in the simulations' traffic list.
     * The value corresponds a tuple where the first element corresponds
     * to the timestamp when the last vehicle arrived (in minutes) and the second value
     * corresponds to the delta time of arrival (in minutes).
     */
    private Map<Integer,Tuple<Integer,Integer>> trafficArrival;
    
    /**
     * The list of vehicles that completed the path.
     */
    private List<VehicleRun> completedVehicles;
    
    /**
     * The list of vehicles that did not complete the path.
     */
    private List<VehicleRun> abortedVehicles;
    
    /**
     * Random generation mechanism.
     */
    private Random rand;
    
    /**
     * Vehicle run ID generation.
     */
    private int idGenerator;
    
    /**
     * Whether the simulation has ended or not.
     */
    private boolean completed;
    
    /**
     * Selectable algorithms to obtain the path.
     */
    public static final int METHOD_FASTEST=0,METHOD_THEORETICAL=1,METHOD_REAL=2;
    
    /**
     * Creates an instance of {@link SimulationRun} with null parameters.
     */
    public SimulationRun()
    {
        name="";
        methodType=-1;
        rand = new Random();
        idGenerator=1;
    }
    
    /**
     * Creates an instance of {@link SimulationRun} with the specified parameters.
     * @param name (String) The name of this simulation run.
     * @param sim ({@link Simulation}) The simulation that this run belongs to.
     */
    public SimulationRun(String name,Simulation sim)
    {
        setName(name);
        setSimulation(simulation);
        methodType=-1;
        heapCars=new LinkedHashMap();
        carsLeft = new LinkedHashMap();
        idGenerator=1;
    }

    /**
     * Returns the starting time of this simulation's run.
     * <p>
     * The starting time is measured in minutes.
     * @return (int) The start time.
     */
    public int getStartTime()
    {
        return startTime;
    }

    /**
     * Sets the starting time of this simulation's run.
     * <p>
     * The starting time is measured in minutes.
     * @param startTime (int) The new value of this simulation run's start time.
     */
    public void setStartTime(int startTime)
    {
        this.startTime = startTime;
    }

    /**
     * Returns the finish time of this simulation's run.
     * <p>
     * The finish time is measured in minutes.
     * @return (int) The finish time.
     */
    public int getFinishTime()
    {
        return finishTime;
    }

    /**
     * Sets the finish time of this simulation's run.
     * <p>
     * The finish time is measured in minutes.
     * @param finishTime (int) The new value of this simulation run's finish time.
     */
    public void setFinishTime(int finishTime)
    {
        this.finishTime = finishTime;
    }

    /**
     * Returns the time step of this simulation's run.
     * <p>
     * The time step is measured in minutes.
     * @return (int) The step time.
     */
    public int getStepTime()
    {
        return stepTime;
    }

    /**
     * Sets the time step of this simulation's run.
     * <p>
     * The time step of the simulation is the time passed between each 'tick' of
     * the run's events. 
     * The time step is measured in minutes.
     * @param stepTime(int) The new value of this simulation run's step time.
     */
    public void setStepTime(int stepTime)
    {
        if (stepTime<=0)
        {
            throw new IllegalArgumentException("Time step must be higher than zero!");
        }
        this.stepTime = stepTime;
    }

    /**
     * Returns the simulation run's name.
     * <p>
     * The run's name is unique.
     * @return (String) The simulation run's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the simulation run's name.
     * @param name (String) The simulation run's new name.
     */
    public void setName(String name)
    {
        if (name==null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null!");
        }
        this.name = name;
    }
    
    /**
     * Returns the run's parent simulation.
     * @return ({@link Simulation}) The simulation that this run belongs to.
     */
    public Simulation getSimulation()
    {
        return simulation;
    }

    /**
     * Sets the simulation this run belongs to.
     * @param simulation {@link Simulation} The simulation that this run belongs to.
     */
    public void setSimulation(Simulation simulation)
    {
        this.simulation = simulation;
    }
    
    /**
     * Sets the best path method to use in this simulation run.
     * @param methodType (int) The best path method.
     */
    public void setMethodType(int methodType) {
        if (0<=methodType && methodType<=2)
        {
            this.methodType = methodType;
        }
        else
        {
            throw new IllegalArgumentException("Best path method invalid.");
        }
    }
    
    /**
     * Returns name of the best path method used.
     * @return (String) The name of the method. Returns an empty string
     * if the type hasn't been set yet.
     */
    public String getMethodType()
    {
        String result="";
        
        switch (methodType)
        {
            case SimulationRun.METHOD_FASTEST:
                result = "fastest";
                break;
            case SimulationRun.METHOD_REAL:
                result = "real";
                break;
            case SimulationRun.METHOD_THEORETICAL:
                result = "theoretical";
                break;
        }
        
        return result;
    }
    
    /**
     * Returns the simulation run's current time.
     * <p>
     * The current time represents the elapsed time of the simulation run
     * @return (int) The current elapsed time of the simulation run.
     */
    public int getCurrentTime()
    {
        return currentTime;
    }
    
    /**
     * Checks whether the simulation run is currently running or not.
     * @return (boolean) True if simulation is being ran.
     */
    public boolean isRunning()
    {
        return running;
    }
    
    /**
     * Checks whether the simulation has completed or not.
     * @return (boolean) True if the simulation's run is complete.
     */
    public boolean hasCompleted()
    {
        return completed;
    }
    
    /**
     * Loads a simulation run from the information retrieved from a database.
     * @param sim ({@link Simulation}) The simulation that this run represents.
     * @param name (String) The name of this simulation run.
     * @param startTime (int) The starting time of the simulation's run.
     * @param finishTime (int) The finish time of the simulation's run.
     * @param stepTime (int) The time step of the simulation.
     * @param method (int) The best path method used to generate the paths.
     * @param completed (List&lt;{@link VehicleRun}&gt;) The list of vehicle runs that 
     * successfully reached the end of their path.
     * @param aborted (List&lt;{@link VehicleRun}&gt;) The list of vehicle runs that
     * was not successful in being injected to the target nodes.
     */
    public void loadFromDatabase(Simulation sim,String name,int startTime,int finishTime,int stepTime,int method,List<VehicleRun> completed,List<VehicleRun> aborted)
    {
        this.name=name;
        simulation=sim;
        this.startTime=startTime;
        this.finishTime=finishTime;
        this.stepTime=stepTime;
        methodType=method;
        this.completedVehicles=completed;
        this.abortedVehicles=aborted;
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
    public String getSummaryResults()
    {
        String result="";
        if (completed)
        {
            String algorithm="";
            switch (methodType)
            {
                case METHOD_FASTEST:
                    algorithm = "Fastest Path";
                    break;
                case METHOD_THEORETICAL:
                    algorithm = "Most Effecient Theoretical Path";
                    break;
                case METHOD_REAL:
                    algorithm = "Most Efficient Path Real Conditions";
                    break;
            }
            
            result = name+","+(finishTime-startTime)+","+stepTime+","
                    +algorithm+","+completedVehicles.size()+","+abortedVehicles.size();
        }
        return result;
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
        List<VehicleRun> result = null;
        if (completed)
        {
            result = new ArrayList(completedVehicles);
        }
        return result;
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
        List<VehicleRun> result = null;
        if (completed)
        {
            result = new ArrayList(abortedVehicles);
        }
        return result;
    }
    
    /**
     * Returns the average energy spent for each traffic pattern.
     * @return (List&lt;List&lt;Double&gt;&gt;) The list containing the averages.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<Double> getAverageEnergy()
    {
        List<Double> result = null;
        if (completed)
        {
            result = new ArrayList();
            for (VehicleRun focus:completedVehicles)
            {
                List<Tuple<String,Tuple<Integer,Double>>> ltp = getAverageEnergyTimeTraffic(name);
                double res = 0;
                for (Tuple<String,Tuple<Integer,Double>> element:ltp)
                {
                    res+=element.getSecondElement().getSecondElement();
                }
                result.add(res);
            }
        }
        return result;
    }
    
    /**
     * Returns the average energy spent in each segment for each traffic pattern.
     * The first tuple connects the segment's id (first element) with the results (
     * second element).
     * @return The list containing the averages.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<List<Tuple<String,Double>>> getAverageEnergySegments()
    {
        List<List<Tuple<String,Double>>> result = null;
        if (completed)
        {
            result = new ArrayList();
            for (VehicleRun focus:completedVehicles)
            {
                List<Tuple<String,Tuple<Integer,Double>>> ltp = getAverageEnergyTimeTraffic(name);
                List<Tuple<String,Double>> l = new ArrayList();
                /*ltp.stream().forEach((element) -> {
                    l.add(new Tuple(element.getFirstElement(),element.getSecondElement().getSecondElement()));
                });*/
                for (Tuple<String,Tuple<Integer,Double>> element:ltp)
                {
                    l.add(new Tuple(element.getFirstElement(),element.getSecondElement().getSecondElement()));
                }
                result.add(l);
            }
        }
        return result;
    }
    
    /**
     * Returns a list containing the average time and energy spent in each segment.
     * The first tuple connects the segment's id (first element) with the results (
     * second element).
     * The first element of the tuple contains the average time spent and the
     * second element of the tuple contains the average energy spent.
     * @param vehName (String) The name of the vehicle to obtain the information from.
     * @return The list containing the tuples.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    public List<Tuple<String,Tuple<Integer,Double>>> getAverageTimeEnergySegments(String vehName)
    {
        return getAverageEnergyTimeTraffic(vehName);
    }
    
    /**
     * Returns a list containing the average time and energy spent in each segment.
     * The first tuple connects the segment's id (first element) with the results (
     * second element).
     * The first element of the tuple contains the average time spent and the
     * second element of the tuple contains the average energy spent.
     * @param vehName (String) The name of the vehicle to obtain the information from.
     * @return The list containing the tuples.
     * Returns null if the vehicle does not exist in this simulation run.
     */
    private List<Tuple<String,Tuple<Integer,Double>>> getAverageEnergyTimeTraffic(String vehName)
    {
        List<Tuple<String,Tuple<Integer,Double>>> result = null;
        boolean foundVehicle=false;
        int vehiclesFound=0;
        if (completed)
        {
            result = new ArrayList();
            for (VehicleRun element:completedVehicles)
            {
                if (element.getVehicleName().equals(vehName))
                {
                    vehiclesFound++;
                    if (!foundVehicle)
                    {
                        foundVehicle = true;
                        
                        for (SegmentRunResult element2:element.getResultsInSegment())
                        {
                            result.add(new Tuple(element2.getName(),new Tuple(element2.getElapsedTime(),element2.getEnergySpend())));
                        }
                    }
                    else
                    {
                        List<SegmentRunResult> list = element.getResultsInSegment();
                        
                        for (int i=0;i<list.size();i++)
                        {
                            Tuple<Integer,Double> tp=result.get(i).getSecondElement();
                            tp.setFirstElement(tp.getFirstElement()+list.get(i).getElapsedTime());
                            tp.setSecondElement(tp.getSecondElement()+list.get(i).getEnergySpend());
                        }
                    }
                }
            }
            if (result.isEmpty())
            {
                result=null;
            }
            else
            {
                for (int i=0;i<result.size();i++)
                {
                    Tuple<Integer,Double> tp=result.get(i).getSecondElement();
                    tp.setFirstElement(tp.getFirstElement()+vehiclesFound);
                    tp.setSecondElement(tp.getSecondElement()+vehiclesFound);
                }
            }
        }
        return result;
    }
    
    /**
     * Starts this simulation run.
     */
    public void startRun()
    {
        running=true;
        runForestRun();
    }
    
    /**
     * Private methods that runs the simulation from start to end.
     */
    private void runForestRun()
    {
        currentTime=startTime;
        initCarsLeft();
        vehiclePaths = new LinkedHashMap();
        trafficArrival = new LinkedHashMap();
        heapCars=new LinkedHashMap();
        carsLeft = new LinkedHashMap();
        completedVehicles=new ArrayList();
        abortedVehicles = new ArrayList();
        completed=false;
        
        initializeCarsLeft();
        initializeHeaps();
        initializeTimeStamp();
        switch (methodType)
        {
            case METHOD_FASTEST:
                Iterator<Traffic> it1 = simulation.getSimulationTraffic().iterator();
                while (it1.hasNext())
                {
                    Traffic t = it1.next();
                    Vehicle vc = t.getVehicle();
                    vehiclePaths.put(vc.getName(), this.getFastestPath(vc, simulation.getOpenProject(), 
                            t.getBeginNode(), t.getEndNode()));
                }
                break;
            case METHOD_THEORETICAL:
                Iterator<Traffic> it2 = simulation.getSimulationTraffic().iterator();
                while (it2.hasNext())
                {
                    Traffic t = it2.next();
                    Vehicle vc = t.getVehicle();
                    vehiclePaths.put(vc.getName(), this.getTheoreticalPath(vc, simulation.getOpenProject(), 
                            t.getBeginNode(), t.getEndNode()));
                }
                break;
            case METHOD_REAL:
                Iterator<Traffic> it3 = simulation.getSimulationTraffic().iterator();
                while (it3.hasNext())
                {
                    Traffic t = it3.next();
                    Vehicle vc = t.getVehicle();
                    vehiclePaths.put(vc.getName(), this.getRealPath(vc, simulation.getOpenProject(), 
                            t.getBeginNode(), t.getEndNode()));
                }
                break;
        }
        
        while (currentTime<finishTime)
        {
            injectTraffic(currentTime);
            for (LinkedList<VehicleRun> focus:heapCars.values())
            {
                for (int i=0;i<focus.size();i++)
                {
                    VehicleRun vr = focus.get(i);
                    if (!vr.hasReachedEnd())
                    {
                        String nextIndex = vr.canMoveToNext(carsLeft,currentTime);
                        if (!nextIndex.isEmpty())
                        {
                            insertionSort(heapCars.get(nextIndex), vr);
                            focus.remove(vr);
                        }
                        else
                        {
                            vr.subtractWaitTime(stepTime);
                            vr.addWaitingEnergy(stepTime);
                        }
                    }
                    else if (!vr.hasEnded())
                    {
                        vr.endVehicleRun(currentTime,carsLeft);
                        //Add to list of vehicles that ended.
                        completedVehicles.add(vr);
                        
                        focus.remove(vr);
                    }
                }
            }
            currentTime+=stepTime;
        }
        
        //Final iteration.
        for (LinkedList<VehicleRun> focus:heapCars.values())
        {
            for (int i=0;i<focus.size();i++)
            {
                VehicleRun vr = focus.get(i);
                if (vr.hasReachedEnd())
                {
                    vr.endVehicleRun(currentTime,carsLeft);
                    //Add to list of vehicles that ended.
                    completedVehicles.add(vr);
                    focus.remove(vr);
                }
            }
        }
        running=false;
        completed=true;
    }
    
    /**
     * Initializes the priority lists.
     */
    private void initializeHeaps()
    {
        for (Section focus:simulation.getOpenProject().getSections())
        {
            Iterator<Segment> it = focus.getSegmentList().iterator();
            while (it.hasNext())
            {
                Segment seg = it.next();
                heapCars.put(focus.getRoadName()+"-"+seg.getIndex(), new LinkedList());
            }
        }
    }
    
    /**
     * Initializes the amount of cars left in each segment.
     */
    private void initializeCarsLeft()
    {
        for (Section focus:simulation.getOpenProject().getSections())
        {
            Iterator<Segment> it = focus.getSegmentList().iterator();
            while (it.hasNext())
            {
                Segment seg = it.next();
                carsLeft.put(focus.getRoadName()+"-"+seg.getIndex(), seg.getMaximumNumberVehicles());
            }
        }
    }
    
    /**
     * Initializes the arrival times of the traffic with the timestamp initialTime.
     */
    private void initializeTimeStamp()
    {
        for (int i=0;i<simulation.getSimulationTraffic().size();i++)
        {
            Traffic tf = simulation.getSimulationTraffic().get(i);
            trafficArrival.put(i, new Tuple(startTime,getNextArrivalOf(tf)));
        }
    }
    
    /**
     * Returns the delta time of the next arrival of a specified {@link Traffic}.
     * @param tf ({@link Traffic}) The target traffic to assess.
     * @return (int) The delta time of the next arrival (in minutes).
     */
    private int getNextArrivalOf(Traffic tf)
    {
        double arrival = tf.getArrivalRate();
        double randValue = rand.nextDouble();
        //double res = Math.ceil(-Math.log(1-randValue)/arrival);
        double res = Math.ceil(-Math.log(1-randValue)*arrival);
        return (int)res;
    }
    
    
    
    /**
     * Injects traffic into the nodes of the road network.
     * @param currentTime (int) The current time of the simulation.
     */
    private void injectTraffic(int currentTime)
    {
        boolean stopInjecting=false;
        boolean trafficInjected;
        while (!stopInjecting)
        {
            trafficInjected=false;
            for (int i=0;i<simulation.getSimulationTraffic().size();i++)
            {
                Traffic tf = simulation.getSimulationTraffic().get(i);
                Tuple<Integer,Integer> tuple=trafficArrival.get(i);
                if (tuple.getFirstElement()+tuple.getSecondElement() <= currentTime)
                {
                    trafficInjected = true;
                    tuple.setFirstElement(tuple.getFirstElement()+tuple.getSecondElement());
                    tuple.setSecondElement(getNextArrivalOf(tf));
                    
                    Section sec=vehiclePaths.get(tf.getVehicle().getName()).get(0);
                    Segment seg = sec.getSegmentList().iterator().next(); //First index of the section.

                    String key = sec.getRoadName()+"-"+seg.getIndex();
                    int inAmount=tf.getArrivalRate();
                    
                    while (inAmount>0)
                    {
                        VehicleRun vr = new VehicleRun(tf.getVehicle(), 
                            vehiclePaths.get(tf.getVehicle().getName()).iterator(), currentTime);
                        vr.setBeginNode(tf.getBeginNode());
                        vr.setEndNode(tf.getEndNode());
                        vr.setUniqueID(idGenerator++);
                        
                        if (carsLeft.get(key)>0)
                        {
                            carsLeft.put(key, carsLeft.get(key)-1);
                            LinkedList<VehicleRun> list = heapCars.get(key);
                            insertionSort(list,vr);
                        }
                        else
                        {
                            //Add as dump.
                            vr.setDropoutTime(currentTime);
                            vr.setBeginNode(tf.getBeginNode());
                            vr.setEndNode(tf.getEndNode());
                            abortedVehicles.add(vr);
                        }
                        inAmount--;
                    }
                }
            }
            if (!trafficInjected)
            {
                stopInjecting=true;
            }
        }
    }
    
    /**
     * Inserts a vehicle into the list taking into the account the time necessary
     * to move towards the next segment.
     * @param list (List&lt;{@link VehicleRun}&gt;) The list of vehicle runs.
     * @param vr ({@link VehicleRun}) The vehicle run to insert.
     */
    private void insertionSort(LinkedList<VehicleRun> list,VehicleRun vr)
    {
        int i;
        for (i=0;i<list.size();i++)
        {
            if (list.get(i).getTimeLeft()>vr.getTimeLeft())
            {
               break;
            }
        }
        list.add(i,vr);
    }
    
    /**
     * Initializes the amount of cars left on each segment of the road network.
     */
    private void initCarsLeft()
    {
        carsLeft = new LinkedHashMap();
        for (Section focus:simulation.getOpenProject().getSections())
        {
            Iterator<Segment> it = focus.getSegmentList().iterator();
            while (it.hasNext())
            {
                Segment seg = it.next();
                carsLeft.put(focus.getRoadName()+"-"+seg.getIndex(),seg.getMaximumNumberVehicles());
            }
        }
    }
    
    /**
     * Checks if this simulation run's data is correctly form by validating it.
     * <p>
     * In case any data is not correctly formed, an IllegalArgumentException is thrown
     * indicating the error.
     * @return (boolean) True if simulation is valid.
     */
    public boolean validateIntegrity()
    {
        boolean result;
        
        if (startTime>=finishTime)
        {
            throw new IllegalArgumentException("Start time must be prior to finish time!");
        }
        
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name has not been specified.");
        }
        
        if (stepTime<=0)
        {
            throw new IllegalArgumentException("Time step must be higher than zero!");
        }
        
        if (simulation==null)
        {
            throw new IllegalArgumentException("This simulation run must belong to a simulation!");
        }
        
        if (methodType==-1)
        {
            throw new IllegalArgumentException("You must specify the best path method.");
        }
        
        result = true;
        
        return result;
    }
    
    /**
     * Returns the path that a vehicles has to follow.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param project ({@link Project}) The project containing the roads.
     * @param originNode ({@link Junction}) The origin node.
     * @param destinyNode ({@link Junction}) The destiny node.
     * @return (LinkedList&lt;{@link Section}&gt;) The path to follow. 
     */
    private LinkedList<Section> getFastestPath(Vehicle vehicle, Project project,Junction originNode,Junction destinyNode)
    {
        LinkedList<Section> result = null;
        List<LinkedList<Section>> paths = project.allPaths(originNode, destinyNode);
        double lowest = Double.POSITIVE_INFINITY;
        LinkedList<Section> fastestPath = new LinkedList();
        for (LinkedList<Section> path : paths) {
            double res = 0;
            for (Section section : path) {
                Iterator<Segment> it = section.getSegmentList().iterator();
                while (it.hasNext()) {
                    
                    Segment segment = it.next();
                    
                    double vel = NetworkAnalysis.getMaximumVelocityIn(segment, vehicle, section);
                    
                    res += segment.getLength()/ vel;
                }
            }
            if (lowest > res) {
                lowest = res;
                fastestPath = path;
            }
        }
        
        if (lowest!=Double.POSITIVE_INFINITY)
        {
            result=fastestPath;
        }
        return result;
    }
    
    /**
     * Returns the path that a vehicles has to follow.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param project ({@link Project}) The project containing the roads.
     * @param originNode ({@link Junction}) The origin node.
     * @param destinyNode ({@link Junction}) The destiny node.
     * @return (LinkedList&lt;{@link Section}&gt;) The path to follow. 
     */
    private LinkedList<Section> getTheoreticalPath(Vehicle vehicle, Project project,Junction originNode,Junction destinyNode)
    {
        LinkedList<Section> result = null;
        List<LinkedList<Section>> paths = project.allPaths(
                originNode, destinyNode);

        LinkedList<Section> shortestPath = new LinkedList();
        double lowest=Double.POSITIVE_INFINITY;
        Segment firstLast = null, secondLast = null;
        double dFirstLast = -1, dSecondLast = -1;
        for (LinkedList<Section> path : paths) {
            double res = 0;
            for (Section section : path) {
                Iterator<Segment> it = section.getSegmentList().iterator();
                while (it.hasNext()) {
                    Segment segment = it.next();
                    dSecondLast = dFirstLast;
                    secondLast = firstLast;

                    double fa = getForceInSegment(section, vehicle, segment);
                    double vel = NetworkAnalysis.getMaximumVelocityIn(segment, vehicle, section);
                    firstLast = segment;
                    dFirstLast = vel;
                    double energy = fa * segment.getLength();
                    if (firstLast != null && secondLast != null) 
                    {
                        double amount = 0.5 * vehicle.getTotalMass() * ((dFirstLast * dFirstLast) - (dSecondLast * dSecondLast));
                        //If it slowed down, we need to check if the electric vehicle will regenerate the braking energy.
                        if (amount<0)
                        {
                            if (vehicle.getClass().equals(VehicleElectric.class))
                            {
                                energy+=amount;
                            }
                        }
                        else
                        {
                            energy += amount;
                        }
                    }
                    res += energy;
                }
            }
            if (res < lowest) {
                lowest = res;
                shortestPath = path;
            }
        }
        
        if (lowest!=Double.POSITIVE_INFINITY)
        {
            result = shortestPath;
        }
        return result;
    }
    
    /**
     * Returns the path that a vehicles has to follow.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param project ({@link Project}) The project containing the roads.
     * @param originNode ({@link Junction}) The origin node.
     * @param destinyNode ({@link Junction}) The destiny node.
     * @return (LinkedList&lt;{@link Section}&gt;) The path to follow. 
     */
    private LinkedList<Section> getRealPath(Vehicle vehicle, Project project,Junction originNode,Junction destinyNode)
    {
        LinkedList<Section> result = null;
        List<LinkedList<Section>> paths = project.allPaths(
                originNode, destinyNode);
        
        LinkedList<Section> shortestPath = new LinkedList();
        
        double lowest=Double.POSITIVE_INFINITY;
        
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
        for (LinkedList<Section> path : paths) {
            
            double res = 0;
            boolean abort = false;
            for (Section section : path) {
                SectionResults s = new SectionResults();
                s.setName(section.getRoadName());
                Iterator<Segment> it = section.getSegmentList().iterator();
                while (it.hasNext()) {
                    Segment segment = it.next();

                    double fa=0;
                    double vel=0;
                    
                    double w=0,torque=0;
                    int i=0;
                    int targetThrottle=0;
                    
                    for (Integer acceleration:throttles)
                    {
                        for (i=highestGear;i>0;i--)
                        {
                            fa=getForceInSegment(section, vehicle, segment, i);
                            vel = NetworkAnalysis.getMaximumVelocityIn(segment, vehicle, section, i);
                            w = vel*9.54930*vehicle.getFinalDrive()*vehicle.getGearbox().getGearRatioOfGear(i)/(vehicle.getWheelSize()*0.5);
                            torque = vehicle.getAcceleratorPedal().getTorqueByPercentageAndRPM(acceleration, w);

                            double engineForce = torque*vehicle.getFinalDrive()*vehicle.getGearbox().getGearRatioOfGear(i)/(vehicle.getWheelSize()*0.5);

                            if (!(engineForce-fa<0 && segment.getAngle()>0))
                            {
                                targetThrottle=acceleration;
                                break;
                            }
                        }
                    }
                    
                    if (i==0)
                    {
                        abort=true;
                        break;
                    }
                    
                    double power = 0.10472*w*torque;
                    
                    if (vel<segment.getMinimumVelocity())
                    {
                        abort=true;
                        break;
                    }
                    
                    double elapsedTime = segment.getLength()/vel;
                    
                    
                    if (vehicle instanceof VehicleCombustion)
                    {
                        if (fa!=0)
                        {
                            double fuelConsumption = vehicle.getAcceleratorPedal().getSFCByPercentageAndRPM(targetThrottle,w)*((power*elapsedTime*0.0000002778));

                            res += fuelConsumption;
                        }
                    }
                    else if (vehicle instanceof VehicleElectric)
                    {
                        double val;
                        if (fa<=0)
                        {
                            val = fa*segment.getLength();
                            res+=val;
                        }
                        else
                        {
                            val  = power*elapsedTime;
                            res+=val;
                        }
                    }
                }
                if (abort)
                {
                    break;
                }
            }
            if (res < lowest && !abort) {   
                lowest = res;
                shortestPath = path;
            }
        }
        if (lowest!=Double.POSITIVE_INFINITY)
        {
            result = shortestPath;
        }
        return result;
    }
    
    @Override
    public boolean equals(Object other)
    {
        boolean result = other!=null && getClass().equals(other.getClass());
        if (result)
        {
            SimulationRun r = (SimulationRun) other;
            result = this==other || (r.getName().equals(name));
        }
        return result;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
