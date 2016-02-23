package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utils.Tuple;

/**
 * Class responsible to perform the static analysis of a road network.
 * <p>
 * Several algorithms can be used to evaluate a vehicle's expected behaviour in
 * the road network.
 *
 * @author G11
 */
public class NetworkAnalysis {

    /**
     * Map with all the results (Fastest Path). The value stored is a tuple
     * where the first element is the results per section and the second element
     * consists of another tuple. The second tuple holds the elapsed time as the
     * first value and the energy consumption as the second value.
     */
    private Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> resultsFastest;

    /**
     * Map with all the results (Efficient Path). The value stored is a tuple
     * where the first element is the results per section and the second element
     * consists of another tuple. The second tuple holds the elapsed time as the
     * first value and the energy consumption as the second value.
     */
    private Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> resultsShortest;

    /**
     * Map with all the results (Efficient Path). The value stored is a tuple
     * where the first element is the results per section and the second element
     * consists of an array of Doubles with the size 3. The data stored in the
     * array follows the pattern below:
     * <p>
     * [0] - Time elapsed (s);
     * <p>
     * [1] - Energy (J);
     * <p>
     * [2] - Fuel Consumption (g);
     */
    private Map<String, Tuple<LinkedList<SectionResults>, Double[]>> resultsRealest;

    /**
     * The density of the air
     */
    private static final double roAr = 1.225;

    /**
     * List of all the vehicles selected to be analised.
     */
    private ArrayList<Vehicle> vehicle;

    /**
     * The origin node.
     */
    private Junction originNode;

    /**
     * The destiny node.
     */
    private Junction destinyNode;

    /**
     * Project with all the data.
     */
    private Project project;

    /**
     * Creates an instance of NetworkAnalysis not getting parameters.
     *
     * @param project
     */
    public NetworkAnalysis(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project hasn't been opened yet.");
        }
        this.resultsFastest = new LinkedHashMap<>();
        this.resultsShortest = new LinkedHashMap();
        this.resultsRealest = new LinkedHashMap();
        this.vehicle = new ArrayList();
        this.project = project;
    }

    /**
     * Add a vehicle to be analised.
     *
     * @param vehicle Vehicle to be analised.
     */
    public void addVehicle(Vehicle vehicle) {
        this.vehicle.add(vehicle);
    }

    /**
     * Sets the origin and destiny nodes and gets the shortest path between the
     * two nodes.
     *
     * @param originNode Origin node;
     * @param destinyNode Destiny node;
     */
    public void setNodes(Junction originNode, Junction destinyNode) {
        this.originNode = originNode;
        this.destinyNode = destinyNode;
    }

    /**
     * Calculates the desired paths.
     * <p>
     * <b>This method does not clear any results or vehicles of previous
     * runs!</b>
     *
     * @param fastestPath True if the user wants to calculate the fastest path;
     * @param efficientPath True if the user wants to calculate the theoretical
     * most efficient path;
     * @param realEfficient True if the user wants to calculate the most
     * efficient path under real condition;
     */
    public void calculate(boolean fastestPath, boolean efficientPath, boolean realEfficient) {
        for (Vehicle veh : this.vehicle) {
            if (fastestPath) {
                calculateFastestPath(veh);
            }
            if (efficientPath) {
                calculateEfficientPath(veh);
            }
            if (realEfficient) {
                if (veh instanceof VehicleCombustion) {
                    calculateRealEfficientPath((VehicleCombustion) veh);
                } else if (veh instanceof VehicleElectric) {
                    calculateRealEfficientPath((VehicleElectric) veh);
                }
            }
        }
    }

    /**
     * Calculates the fastest path for a target vehicle between the two nodes.
     * <p>
     * The fastest path will also calculate the energy required to perform the
     * trip. Traffic is not considered.
     *
     * @param vehicle Vehicle to be analised.
     */
    protected void calculateFastestPath(Vehicle vehicle) {
        List<LinkedList<Section>> paths = this.project.allPaths(this.originNode, this.destinyNode);
        double lowest = Double.POSITIVE_INFINITY;
        LinkedList<Section> fastestPath = new LinkedList();
        for (LinkedList<Section> path : paths) {
            double result = 0;
            for (Section section : path) {
                Iterator<Segment> it = section.getSegmentList().iterator();
                while (it.hasNext()) {

                    Segment segment = it.next();

                    double vel = getMaximumVelocityIn(segment, vehicle, section);

                    result += segment.getLength() / vel;
                }
            }

            if (lowest > result) {
                lowest = result;
                fastestPath = path;
            }
        }

        double finalEnergy = 0;

        LinkedList<SectionResults> sectionResults = new LinkedList<>();
        Segment firstLast = null, secondLast = null;
        double dFirstLast = -1, dSecondLast = -1;
        for (Section section : fastestPath) {
            SectionResults s = new SectionResults();
            s.setName(section.getRoadName());
            double energySec = 0;
            double timeTravel = 0;
            Iterator<Segment> it = section.getSegmentList().iterator();
            while (it.hasNext()) {
                Segment segment = it.next();
                dSecondLast = dFirstLast;
                secondLast = firstLast;

                double vel = getMaximumVelocityIn(segment, vehicle, section);

                firstLast = segment;
                dFirstLast = vel;

                if (firstLast != null && secondLast != null) {
                    double amount = 0.5 * vehicle.getTotalMass() * ((dFirstLast * dFirstLast) - (dSecondLast * dSecondLast));
                    //If it slowed down, we need to check if the electric vehicle will regenerate the braking energy.
                    if (amount < 0) {
                        if (vehicle.getClass().equals(VehicleElectric.class)) {
                            energySec += amount;
                        }
                    } else {
                        energySec += amount;
                    }
                }

                double fa = getForceInSegment(section, vehicle, segment);
                energySec += fa * segment.getLength();
                timeTravel += segment.getLength() / vel;
            }

            finalEnergy += energySec;
            s.setTimeTravel(timeTravel);
            s.setEnergy(energySec);
            s.setToll(section.getToll());
            s.setType(SectionResults.FASTEST);
            sectionResults.add(s);
        }

        if (lowest == Double.POSITIVE_INFINITY) {
            this.resultsFastest.put(vehicle.getName(), null);
        } else {
            this.resultsFastest.put(vehicle.getName(), new Tuple(sectionResults, new Tuple(lowest, finalEnergy)));
        }
    }

    /**
     * Calculates the theoretical most efficient path of a vehicle between two
     * nodes.
     * <p>
     * Traffic is not considered.
     *
     * @param vehicle Vehicle to be analised
     */
    protected void calculateEfficientPath(Vehicle vehicle) {
        List<LinkedList<Section>> paths = this.project.allPaths(
                this.originNode, this.destinyNode);

        LinkedList<Section> shortestPath = new LinkedList();
        double lowest = Double.POSITIVE_INFINITY;
        Segment firstLast = null, secondLast = null;
        double dFirstLast = -1, dSecondLast = -1;
        for (LinkedList<Section> path : paths) {
            double result = 0;

            for (Section section : path) {
                Iterator<Segment> it = section.getSegmentList().iterator();
                while (it.hasNext()) {
                    Segment segment = it.next();
                    dSecondLast = dFirstLast;
                    secondLast = firstLast;

                    double fa = getForceInSegment(section, vehicle, segment);
                    double vel = getMaximumVelocityIn(segment, vehicle, section);
                    firstLast = segment;
                    dFirstLast = vel;
                    double energy = fa * segment.getLength();

                    if (firstLast != null && secondLast != null) {
                        double amount = 0.5 * vehicle.getTotalMass() * ((dFirstLast * dFirstLast) - (dSecondLast * dSecondLast));
                        //If it slowed down, we need to check if the electric vehicle will regenerate the braking energy.
                        if (amount < 0) {
                            if (vehicle.getClass().equals(VehicleElectric.class)) {
                                energy += amount;
                            }
                        } else {
                            energy += amount;
                        }
                    }
                    result += energy;
                }
            }

            if (result < lowest) {
                lowest = result;
                shortestPath = path;
            }
        }
        double finalTimeTravel = 0;
        LinkedList<SectionResults> sectionResults = new LinkedList<>();

        for (Section section : shortestPath) {
            SectionResults s = new SectionResults();
            s.setName(section.getRoadName());
            double energySec = 0;
            double timeTravel = 0;
            Iterator<Segment> it = section.getSegmentList().iterator();

            while (it.hasNext()) {
                Segment segment = it.next();
                dSecondLast = dFirstLast;
                secondLast = firstLast;

                double vel = getMaximumVelocityIn(segment, vehicle, section);

                firstLast = segment;
                dFirstLast = vel;

                if (firstLast != null && secondLast != null) {
                    double amount = 0.5 * vehicle.getTotalMass() * ((dFirstLast * dFirstLast) - (dSecondLast * dSecondLast));
                    //If it slowed down, we need to check if the electric vehicle will regenerate the braking energy.
                    if (amount < 0) {
                        if (vehicle.getClass().equals(VehicleElectric.class)) {
                            energySec += amount;
                        }
                    } else {
                        energySec += amount;
                    }
                }

                double fa = getForceInSegment(section, vehicle, segment);
                energySec += fa * segment.getLength();
                timeTravel += segment.getLength() / vel;
            }
            finalTimeTravel += timeTravel;
            s.setTimeTravel(timeTravel);
            s.setEnergy(energySec);
            s.setToll(section.getToll());
            s.setType(SectionResults.THEORETICAL_EFFICIENT);
            sectionResults.add(s);
        }

        if (lowest == Double.POSITIVE_INFINITY) {
            this.resultsShortest.put(vehicle.getName(), null);
        } else {
            this.resultsShortest.put(vehicle.getName(), new Tuple(sectionResults, new Tuple(finalTimeTravel, lowest)));
        }
    }

    /**
     * Calculates the most efficient path under, real conditions, of a
     * combustion vehicle between two nodes.
     * <p>
     * In this scenario, the vehicle travels at the highest gear whenever
     * possible, respecting the road's speed imit. Traffic is not considered.
     *
     * @param vehicle Vehicle to be analised.
     */
    protected void calculateRealEfficientPath(VehicleCombustion vehicle) {
        List<LinkedList<Section>> paths = this.project.allPaths(
                this.originNode, this.destinyNode);

        //VehicleCombustion vc = (VehicleCombustion) vehicle;
        LinkedList<Section> shortestPath = new LinkedList();
        double finalTimeTravel = 0;
        LinkedList<SectionResults> sectionResults = new LinkedList();

        double lowest = Double.POSITIVE_INFINITY;
        
        double resultTimeTravel=0;
        double resultEnergy=0;

        List<Integer> throttles = new ArrayList();
        for (Entry<Integer, Throttle> val : vehicle.getAcceleratorPedal().entrySet()) {
            throttles.add(val.getKey());
        }
        Collections.sort(throttles);

        int highestGear = -1;
        for (Entry<Integer, Double> val : vehicle.getGearbox().entrySet()) {
            if (highestGear < val.getKey()) {
                highestGear = val.getKey();
            }
        }

        double totalEnergy = 0;
        for (LinkedList<Section> path : paths) {
            LinkedList<SectionResults> sResults = new LinkedList();
            double result = 0;
            boolean abort = false;
            finalTimeTravel = 0;
            totalEnergy = 0;

            for (Section section : path) {
                double energy = 0;
                SectionResults s = new SectionResults();
                s.setName(section.getRoadName());
                Iterator<Segment> it = section.getSegmentList().iterator();
                double fuel = 0;
                double timeTravel = 0;
                while (it.hasNext()) {
                    Segment segment = it.next();

                    double fa = 0; //= getForceInSegment(section, vehicle, segment);
                    double vel = 0;// = getMaximumVelocityIn(segment, vehicle, section);

                    double w = 0, torque = 0;
                    int i = 0;
                    int targetThrottle = 0;

                    for (Integer acceleration : throttles) {
                        for (i = highestGear; i > 0; i--) {
                            fa = getForceInSegment(section, vehicle, segment, i);
                            vel = getMaximumVelocityIn(segment, vehicle, section, i);
                            w = vel * 9.54930 * vehicle.getFinalDrive() * vehicle.getGearbox().getGearRatioOfGear(i) / (vehicle.getWheelSize() * 0.5);
                            torque = vehicle.getAcceleratorPedal().getTorqueByPercentageAndRPM(acceleration, w);

                            double engineForce = torque * vehicle.getFinalDrive() * vehicle.getGearbox().getGearRatioOfGear(i) / (vehicle.getWheelSize() * 0.5);

                            if (!(engineForce - fa < 0 && segment.getAngle() > 0)) {
                                targetThrottle = acceleration;
                                break;
                            }
                        }
                        if (targetThrottle!=0)
                        {
                            break;
                        }
                    }

                    if (i == 0) {
                        abort = true;
                        break;
                    }

                    double power = 0.10472 * w * torque;

                    if (vel < segment.getMinimumVelocity()) {
                        abort = true;
                        break;
                    }

                    double elapsedTime = segment.getLength() / vel;

                    if (fa != 0) {
                        double fuelConsumption = vehicle.getAcceleratorPedal().getSFCByPercentageAndRPM(targetThrottle, w) * ((power * elapsedTime * 0.0000002778));

                        energy += power * elapsedTime;
                        result += fuelConsumption;
                        fuel += fuelConsumption;
                    }

                    timeTravel += elapsedTime;
                }

                if (abort) {
                    break;
                }

                finalTimeTravel += timeTravel;
                totalEnergy += energy;
                s.setTimeTravel(timeTravel);
                s.setEnergy(energy);
                s.setFuel(fuel);
                s.setToll(section.getToll());
                s.setType(SectionResults.REAL_EFFICIENT);
                sResults.add(s);
            }

            if (result < lowest && !abort) {
                lowest = result;
                resultTimeTravel = finalTimeTravel;
                resultEnergy = totalEnergy;
                shortestPath = path;
                sectionResults = sResults;
            }
        }

        if (lowest == Double.POSITIVE_INFINITY) {
            this.resultsRealest.put(vehicle.getName(), null);
        } else {
            this.resultsRealest.put(vehicle.getName(), new Tuple(sectionResults, new Double[]{resultTimeTravel, resultEnergy, lowest}));
        }
    }

    /**
     * Calculates the most efficient path under, real conditions, of a electric
     * vehicle between two nodes.
     * <p>
     * In this scenario, the vehicle travels at the highest gear whenever
     * possible, respecting the road's speed imit. Traffic is not considered.
     *
     * @param vehicle Vehicle to be analised.
     */
    protected void calculateRealEfficientPath(VehicleElectric vehicle) {
        List<LinkedList<Section>> paths = this.project.allPaths(
                this.originNode, this.destinyNode);

        double finalTimeTravel = 0;
        LinkedList<SectionResults> sectionResults = new LinkedList();

        double lowest = Double.POSITIVE_INFINITY;
        double resultTimeTravel=0;
        
        List<Integer> throttles = new ArrayList();
        for (Entry<Integer, Throttle> val : vehicle.getAcceleratorPedal().entrySet()) {
            throttles.add(val.getKey());
        }
        Collections.sort(throttles);

        int highestGear = -1;
        for (Entry<Integer, Double> val : vehicle.getGearbox().entrySet()) {
            if (highestGear < val.getKey()) {
                highestGear = val.getKey();
            }
        }

        for (LinkedList<Section> path : paths) {
            LinkedList<SectionResults> sResults = new LinkedList();
            double result = 0;
            finalTimeTravel = 0;
            boolean abort = false;

            for (Section section : path) {
                double sectionEnergy = 0;
                SectionResults s = new SectionResults();
                s.setName(section.getRoadName());
                Iterator<Segment> it = section.getSegmentList().iterator();
                double fuel = 0;
                double timeTravel = 0;

                while (it.hasNext()) {
                    Segment segment = it.next();

                    double fa = 0; //= getForceInSegment(section, vehicle, segment);
                    double vel = 0;// = getMaximumVelocityIn(segment, vehicle, section);

                    double w = 0, torque = 0;
                    int i = 0;

                    for (Integer acceleration : throttles) {
                        for (i = highestGear; i > 0; i--) {
                            fa = getForceInSegment(section, vehicle, segment, i);
                            vel = getMaximumVelocityIn(segment, vehicle, section, i);
                            w = vel * 9.54930 * vehicle.getFinalDrive() * vehicle.getGearbox().getGearRatioOfGear(i) / (vehicle.getWheelSize() * 0.5);
                            torque = vehicle.getAcceleratorPedal().getTorqueByPercentageAndRPM(acceleration, w);

                            double engineForce = torque * vehicle.getFinalDrive() * vehicle.getGearbox().getGearRatioOfGear(i) / (vehicle.getWheelSize() * 0.5);

                            if (!(engineForce - fa < 0 && segment.getAngle() > 0)) {
                                break;
                            }
                        }
                    }

                    if (i == 0) {
                        abort = true;
                        break;
                    }

                    double power = 0.10472 * w * torque;

                    if (vel < segment.getMinimumVelocity()) {
                        abort = true;
                        break;
                    }

                    double elapsedTime = segment.getLength() / vel;
                    timeTravel += elapsedTime;

                    double val;

                    if (fa <= 0) {
                        val = fa * segment.getLength();
                        result += val;
                        sectionEnergy += val;
                    } else {
                        val = power * elapsedTime;
                        result += val;
                        sectionEnergy += val;
                    }

                }

                if (abort) {
                    break;
                }

                finalTimeTravel += timeTravel;
                s.setTimeTravel(timeTravel);
                s.setEnergy(sectionEnergy);
                s.setFuel(fuel);
                s.setToll(section.getToll());
                s.setType(SectionResults.REAL_EFFICIENT);
                sResults.add(s);
            }

            if (result < lowest && !abort) {
                lowest = result;
                resultTimeTravel = finalTimeTravel;
                sectionResults = sResults;
            }
        }

        if (lowest == Double.POSITIVE_INFINITY) {
            this.resultsRealest.put(vehicle.getName(), null);
        } else {
            this.resultsRealest.put(vehicle.getName(), new Tuple(sectionResults, new Double[]{resultTimeTravel, lowest, 0d}));
        }
    }

    /**
     * Returns the force that the specified vehicle's engine has to output in
     * order to traverse a target segment.
     * <p>
     * In the calculations, the particle model is used to represent the vehicle.
     *
     * @param section ({@link Section}) The parent section of the segment.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param segment ({@link Segment}) The target segment to evaluate.
     * @param gearIndex (int) The gear index.
     * @return (double) The value of the force.
     */
    protected static final double getForceInSegment(Section section, Vehicle vehicle, Segment segment, int gearIndex) {
        double vel = getMaximumVelocityIn(segment, vehicle, section, gearIndex);
        double fa;
        if (segment.getAngle() == 0) {
            fa = (0.5 * vehicle.getFrontalArea() * vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81);
        } else if (segment.getAngle() > 0) {
            fa = (0.5*vehicle.getFrontalArea()*vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81 * Math.cos(Math.toRadians(segment.getAngle())));
            fa += Math.sin(Math.toRadians(segment.getAngle())) * vehicle.getTotalMass() * 9.81;
        } else {
            fa = (0.5*vehicle.getFrontalArea()*vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81 * Math.cos(Math.toRadians(segment.getAngle())));
            double gravAmount = Math.abs(Math.sin(Math.toRadians(segment.getAngle())) * vehicle.getTotalMass() * 9.81);
            if (gravAmount <= fa || vehicle.getClass().equals(VehicleElectric.class)) {
                fa -= gravAmount;
                if (vehicle instanceof VehicleElectric)
                {
                    fa *= ((VehicleElectric) vehicle).getEnergyRegenerationRatio();
                }
            } else {
                fa = 0;
            }
        }
        return fa;
    }

    /**
     * Returns the maximum speed that a vehicle can traverse in the specified
     * segment, given the wind conditions of the parent segment's section.
     * <p>
     * The gear index must be specified.
     *
     * @param section ({@link Section}) The parent section of the segment.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param segment ({@link Segment}) The target segment to evaluate.
     * @param gearIndex (int) The gear index.
     * @return (double) The maximum velocity.
     */
    protected static final double getMaximumVelocityIn(Segment segment, Vehicle vehicle, Section section, int gearIndex) {
        double vel = vehicle.getMaximumVelocityInGear(gearIndex);
        if (vel == -1) {
            throw new IllegalArgumentException("Gear index does not exist.");
        }
        double allowedVelSegment = vehicle.getVelocityLimitOfRoad(section.getTypology());
        if (allowedVelSegment < vel) {
            vel = allowedVelSegment;
        }
        double windSpeed = section.getWindSpeed() * Math.cos(Math.toRadians(section.getWindOrientation()));

        //Check favouring wind.
        if (windSpeed < 0) {
            double windVal = Math.abs(windSpeed);
            if (vel - windVal < 8.33) //8.33 m/s~= 30km/h
            {
                windSpeed = -8.33;
            }
        }
        vel -= windSpeed;
        double maxVel = segment.getMaximumVelocity();
        vel = Math.min(vel, (maxVel > 0) ? maxVel : vel);
        return vel;
    }

    /**
     * Returns the force that the specified vehicle's engine has to output in
     * order to traverse a target segment.
     * <p>
     * In the calculations, the particle model is used to represent the vehicle.
     *
     * @param section ({@link Section}) The parent section of the segment.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param segment ({@link Segment}) The target segment to evaluate.
     * @return (double) The value of the force.
     */
    protected static final double getForceInSegment(Section section, Vehicle vehicle, Segment segment) {
        double vel = getMaximumVelocityIn(segment, vehicle, section);
        double fa;
        if (segment.getAngle() == 0) {
            fa = (0.5 * vehicle.getFrontalArea() * vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81);
        } else if (segment.getAngle() > 0) {
            fa = (0.5* vehicle.getFrontalArea()*vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81 * Math.cos(Math.toRadians(segment.getAngle())));
            fa += Math.sin(Math.toRadians(segment.getAngle())) * vehicle.getTotalMass() * 9.81;
        } else {
            fa = (0.5*vehicle.getFrontalArea()*vehicle.getDragCoefficient() * roAr * (vel * vel)) + (vehicle.getRollingResistanceCoefficient() * vehicle.getTotalMass() * 9.81 * Math.cos(Math.toRadians(segment.getAngle())));
            double gravAmount = Math.abs(Math.sin(Math.toRadians(segment.getAngle())) * vehicle.getTotalMass() * 9.81);
            if (gravAmount <= fa || vehicle.getClass().equals(VehicleElectric.class)) {
                fa -= gravAmount;
                if (vehicle instanceof VehicleElectric)
                {
                    fa *= ((VehicleElectric) vehicle).getEnergyRegenerationRatio();
                }
            } else {
                fa = 0;
            }
        }
        return fa;
    }

    /**
     * Returns the maximum speed that a vehicle can traverse in the specified
     * segment, given the wind conditions of the parent segment's section.
     *
     * @param section ({@link Section}) The parent section of the segment.
     * @param vehicle ({@link Vehicle}) The target vehicle.
     * @param segment ({@link Segment}) The target segment to evaluate.
     * @return (double) The maximum velocity.
     */
    protected static final double getMaximumVelocityIn(Segment segment, Vehicle vehicle, Section section) {
        double vel = vehicle.getVelocityLimitOfRoad(section.getTypology());
        double windSpeed = section.getWindSpeed() * Math.cos(Math.toRadians(section.getWindOrientation()));

        //Check favouring wind.
        if (windSpeed < 0) {
            double windVal = Math.abs(windSpeed);
            if (vel - windVal < 8.33) //8.33 m/s~= 30km/h
            {
                windSpeed = -8.33;
            }
        }

        vel -= windSpeed;
        double maxVel = segment.getMaximumVelocity();
        vel = Math.min(vel, (maxVel > 0) ? maxVel : vel);
        return vel;
    }

    /**
     * Returns the results of the fastest path algorithm.
     *
     * @return The results of the fastest path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> getResultsFastest() {
        return this.resultsFastest;
    }

    /**
     * Returns the results of the theoretical efficient path algorithm.
     *
     * @return The results of the theoretical efficient path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double, Double>>> getResultsShortest() {
        return this.resultsShortest;
    }

    /**
     * Returns the results of the real efficient path algorithm.
     *
     * @return The results of the real efficient path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Double[]>> getResultsReal() {
        return this.resultsRealest;
    }

    /**
     * Clears the results and list of vehicles of this {@link NetworkAnalysis}.
     */
    public void clearResults() {
        this.vehicle.clear();
        this.resultsFastest.clear();
        this.resultsShortest.clear();
        this.resultsRealest.clear();
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @param vehicles List of vehicle's names.
     * @return Textual description of the object.
     */
    public String toStringHTML(List<String> vehicles) {
        StringBuilder sb = new StringBuilder();

        sb.append("<h1>Network Static Analysis Results</h1>");
        sb.append("<br/>");
        sb.append("<h3>Fastest Path Algorithm Results</h3>\n");

        if (!this.resultsFastest.isEmpty()) {
            sb.append("<table>\n");
            sb.append("\t<tr><th>Vehicle Name</th><th>Vehicle Path</th><th>Workload Energy</th><th>Time Spent</th></tr>\n");

            for (String vehicleName : vehicles) {
                if (this.resultsFastest.containsKey(vehicleName)) {
                    Tuple<LinkedList<SectionResults>, Tuple<Double, Double>> results = this.resultsFastest.get(vehicleName);

                    // Build the string with the full path the vehicle did
                    StringBuilder vehiclePath = new StringBuilder();
                    Iterator<SectionResults> iterator = results.getFirstElement().iterator();

                    while (iterator.hasNext()) {
                        vehiclePath.append(iterator.next().getName()).append("; ");
                    }

                    // Energy and time spent
                    Tuple<Double, Double> totalResults = results.getSecondElement();

                    sb.append("\t<tr>"
                            + "<td>").append(vehicleName).append("</td>"
                            + "<td>").append(vehiclePath.toString()).append("</td>"
                            + "<td>").append(String.format("%.3e", totalResults.getSecondElement())).append(" J</td>"
                            + "<td>").append(totalResults.getFirstElement()).append(" s</td>"
                            + "</tr>\n");
                }
            }

            sb.append("</table>\n");
        } else {
            sb.append("<ul><li>No vehicle was analised under the fastest path algorithm.</li></ul>\n");
        }

        sb.append("\n<h3>Theoretical Most Efficient Algorithm Results</h3>\n");

        if (!this.resultsShortest.isEmpty()) {
            sb.append("<table>\n");
            sb.append("\t<tr><th>Vehicle Name</th><th>Vehicle Path</th><th>Workload Energy</th><th>Time Spent</th></tr>\n");

            for (String vehicleName : vehicles) {
                if (this.resultsShortest.containsKey(vehicleName)) {
                    Tuple<LinkedList<SectionResults>, Tuple<Double, Double>> results = this.resultsFastest.get(vehicleName);

                    // Build the string with the full path the vehicle did
                    StringBuilder vehiclePath = new StringBuilder();
                    Iterator<SectionResults> iterator = results.getFirstElement().iterator();

                    while (iterator.hasNext()) {
                        vehiclePath.append(iterator.next().getName()).append("; ");
                    }

                    // Energy and time spent
                    Tuple<Double, Double> totalResults = results.getSecondElement();

                    sb.append("\t<tr>"
                            + "<td>").append(vehicleName).append("</td>"
                            + "<td>").append(vehiclePath.toString()).append("</td>"
                            + "<td>").append(String.format("%.3e", totalResults.getSecondElement())).append(" J</td>"
                            + "<td>").append(totalResults.getFirstElement()).append(" s</td>"
                            + "</tr>\n");// String.format("%.3e", totalResults.getSecondElement())
                }
            }

            sb.append("</table>\n");
        } else {
            sb.append("<ul><li>No vehicle was analised under the theoretical most efficient path algorithm.</li></ul>\n");
        }

        sb.append("\n<h3>Real Most Efficient Algorithm Results</h3>\n");

        if (!this.resultsRealest.isEmpty()) {
            sb.append("<table>\n");
            sb.append("\t<tr><th>Vehicle Name</th><th>Vehicle Path</th><th>Workload Energy</th><th>Fuel Consumption</th><th>Time Spent</th></tr>\n");

            for (String vehicleName : vehicles) {
                if (this.resultsRealest.containsKey(vehicleName)) {
                    Tuple<LinkedList<SectionResults>, Double[]> results = this.resultsRealest.get(vehicleName);

                    // Build the string with the full path the vehicle did
                    StringBuilder vehiclePath = new StringBuilder();
                    Iterator<SectionResults> iterator = results.getFirstElement().iterator();

                    while (iterator.hasNext()) {
                        vehiclePath.append(iterator.next().getName()).append("; ");
                    }

                    // Array containing the energy spent, fuel consumption and time spent of the vehicle during its run.
                    Double[] totalResults = results.getSecondElement();

                    sb.append("\t<tr>"
                            + "<td>").append(vehicleName).append("</td>"
                            + "<td>").append(vehiclePath.toString()).append("</td>"
                            + "<td>").append(String.format("%.3e", totalResults[1])).append(" J</td>"
                            + "<td>").append(String.format("%.3e", totalResults[2])).append(" g</td>"
                            + "<td>").append(totalResults[0]).append(" s</td>"
                            + "</tr>\n");
                }
            }

            sb.append("</table>\n");
        } else {
            sb.append("<ul><li>No vehicle was analised under the real most efficient path algorithm.</li></ul>\n");
        }

        return sb.toString();
    }
}
