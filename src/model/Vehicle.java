package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents a vehicle.
 *
 * @author G11
 */
public abstract class Vehicle implements HTMLExportable {

    /**
     * Name of the vehicle.
     */
    private String name;

    /**
     * Description of the vehicle.
     */
    private String description;

    /**
     * Type of the vehicle.
     */
    private String type;

    /**
     * Fuel of the vehicle.
     */
    private String fuel;

    /**
     * Mass of the vehicle.
     */
    private double mass;

    /**
     * Load of the vehicle.
     */
    private double load;

    /**
     * Drag Coefficient of the vehicle.
     */
    private double dragCoefficient;

    /**
     * Frontal area of the vehicle.
     */
    private double frontalArea;

    /**
     * Rolling resistance coefficient of the vehicle.
     */
    private double rollingResistanceCoefficient;

    /**
     * Wheel size of the vehicle.
     */
    private double wheelSize;

    /**
     * The velocity limits per road of a vehicle.
     */
    private Map<String, Double> velocityLimitPerRoad;

    /**
     * The function of the Energy Efficiency of the vehicle.
     */
    private Function energyEfficiency;

    /**
     * Creates an instance of vehicle with default parameters.
     */
    public Vehicle() {
        this.name = "";
        this.description = "";
        this.type = "";
        this.fuel = "";
        this.mass = 0.0;
        this.load = 0.0;
        this.dragCoefficient = 0.0;
        this.frontalArea = 0.0;
        this.rollingResistanceCoefficient = 0.0;
        this.wheelSize = 0.0;
        this.velocityLimitPerRoad = new LinkedHashMap();
        this.energyEfficiency = new Function();
    }

    /**
     * Returns the name of the vehicle.
     *
     * @return The name of the vehicle.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the vehicle.
     *
     * @return The description of the vehicle.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of the vehicle.
     *
     * @return The type of the vehicle.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the fuel of the vehicle.
     *
     * @return The fuel of the vehicle.
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * Return the mass of the vehicle.
     *
     * @return The mass of the vehicle (kg).
     */
    public double getMass() {
        return this.mass;
    }
    
    /**
     * Returns the total mass of the vehicle.
     * <p>
     * The total mass of the vehicle is equal to the base mass and the extra load.
     * @return (double) The total mass of the vehicle (in kg).
     */
    public double getTotalMass()
    {
        return this.mass+this.load;
    }

    /**
     * Returns the load of the vehicle.
     *
     * @return The load of the vehicle.
     */
    public double getLoad() {
        return this.load;
    }

    /**
     * Returns the drag coefficient of the vehicle.
     *
     * @return The drag coefficient of the vehicle.
     */
    public double getDragCoefficient() {
        return this.dragCoefficient;
    }

    /**
     * Returns the frontal area of the vehicle.
     *
     * @return The frontal area of the vehicle.
     */
    public double getFrontalArea() {
        return this.frontalArea;
    }

    /**
     * Returns the rolling resistance coefficient of the vehicle.
     *
     * @return The rolling resistance coefficient of the vehicle.
     */
    public double getRollingResistanceCoefficient() {
        return this.rollingResistanceCoefficient;
    }

    /**
     * Returns the wheel size function of the vehicle.
     *
     * @return The wheel size function of the vehicle.
     */
    public double getWheelSize() {
        return wheelSize;
    }

    /**
     * Returns the energy efficiency function of the vehicle.
     *
     * @return The energy efficiency function of the vehicle.
     */
    public Function getEnergyEfficiency() {
        return this.energyEfficiency;
    }

    /**
     * Returns the velocities per road of the vehicle.
     * 
     * @return The velocities per road of the vehicle.
     */
    public Set<Entry<String, Double>> getvelocityLimitPerRoad() {
        return this.velocityLimitPerRoad.entrySet();
    }

    /**
     * Sets the name of the vehicle.
     *
     * @param name The new name of the vehicle.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the vehicle cannot "
                    + "be empty.");
        }

        this.name = name;
    }

    /**
     * Sets the description of the vehicle.
     *
     * @param description The new description of the vehicle.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the vehicle "
                    + "cannot be empty.");
        }

        this.description = description;
    }

    /**
     * Sets the type of the vehicle.
     *
     * @param type The new type of the vehicle.
     */
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("The type of the vehicle cannot "
                    + "be empty.");
        }

        this.type = type;
    }

    /**
     * Sets the fuel of the vehicle.
     *
     * @param fuel New motorization of the vehicle.
     */
    public void setFuel(String fuel) {
        if (fuel == null || fuel.trim().isEmpty()) {
            throw new IllegalArgumentException("The fuel of the vehicle cannot "
                    + "be empty.");
        }

        this.fuel = fuel;
    }

    /**
     * Sets the mass of the vehicle.
     *
     * The mass of the vehicle is expressed in the International System Units
     * kg.
     *
     * @param mass The new mass of the vehicle.
     */
    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("The mass of the vehicle must be"
                    + " greater than 0");
        }

        this.mass = mass;
    }

    /**
     * Sets the load of the vehicle.
     *
     * The load of the vehicle is expressed in the International System Units
     * kg.
     *
     * @param load The new load of the vehicle.
     */
    public void setLoad(double load) {
        if (load < 0) {
            throw new IllegalArgumentException("The load of the vehicle must be"
                    + " positive.");
        }

        this.load = load;
    }

    /**
     * Sets the drag coefficient of the vehicle.
     *
     * @param dragCoefficient The new drag coefficient of the vehicle.
     */
    public void setDragCoefficient(double dragCoefficient) {
        if (dragCoefficient <= 0) {
            throw new IllegalArgumentException("The drag coefficient of the "
                    + "vehicle must be positive.");
        }

        this.dragCoefficient = dragCoefficient;
    }

    /**
     * Sets the frontal area of the vehicle.
     *
     * @param frontalArea The frontal area of the vehicle.
     */
    public void setFrontalArea(double frontalArea) {
        if (frontalArea <= 0) {
            throw new IllegalArgumentException("The frontal area of the vehicle"
                    + " must be positive.");
        }

        this.frontalArea = frontalArea;
    }

    /**
     * Sets the rolling resistance coefficient of the vehicle.
     *
     * @param rollingResistanceCoefficient The new rolling resistance
     * coefficient of the vehicle.
     */
    public void setRollingResistanceCoefficient(
            double rollingResistanceCoefficient) {
        if (rollingResistanceCoefficient < 0) {
            throw new IllegalArgumentException("The rolling resistance "
                    + "coefficient of the vehicle must be positive");
        }

        this.rollingResistanceCoefficient = rollingResistanceCoefficient;
    }

    /**
     * Sets the wheel size of the vehicle.
     *
     * @param wheelSize The new wheel size of the vehicle.
     */
    public void setWheelSize(double wheelSize) {
        if (wheelSize <= 0) {
            throw new IllegalArgumentException("The wheel size of the "
                    + "vehicle must be positive");
        }

        this.wheelSize = wheelSize;
    }

    /**
     * Sets the energy efficiency function of the vehicle.
     *
     * @param energyEfficiency The energy efficiency function of the vehicle.
     */
    public void setEnergyEfficiency(Function energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }


    /**
     * Returns the textual description of the object in the following format:
     * Name: Nissan Skyline Description: Sports Car Type: Car Fuel: Gasoline
     * Mass: 2300kg Load: 80kg Drag Coefficient: 0.38 Frontal Area: 2.4m RRC:
     * 0.01 Wheel Size: 0.5m
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tName: ").append(this.name).append("\n");
        sb.append("\tDescription: ").append(this.description).append("\n");
        sb.append("\tType: ").append(this.type).append("\n");
        sb.append("\tFuel: ").append(this.fuel).append("\n");
        sb.append("\tMass: ").append(this.mass).append(" Kg\n");
        sb.append("\tLoad: ").append(this.load).append(" Kg\n");
        sb.append("\tDrag Coefficient: ").append(this.dragCoefficient).append("\n");
        sb.append("\tFrontal Area: ").append(this.frontalArea).append(" m\n");
        sb.append("\tRRC: ").append(this.rollingResistanceCoefficient).append("\n");
        sb.append("\tWheel Size: ").append(this.wheelSize).append(" m\n");

        if (!this.velocityLimitPerRoad.isEmpty()) {
            sb.append("\tVelocity Limits:\n");

            for (Entry<String, Double> entry : this.velocityLimitPerRoad.entrySet()) {
                sb.append("\t\t").append(entry.getKey()).append(": ")
                        .append(entry.getValue()).append(" m/s\n");
            }
        }

        return sb.toString();
    }

    /**
     * Compares two objects, first looking at their memory position, following
     * their content and class of origin and in the end compares a set of
     * attributes.
     *
     * @param otherObject used in the comparison.
     * @return True if both objects are equal and false if not.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Vehicle otherVehicle = (Vehicle) otherObject;

        return this.getName().equals(otherVehicle.getName())
                && this.getDescription().equals(otherVehicle.getDescription())
                && this.getType().equals(otherVehicle.getType())
                && this.getFuel().equals(otherVehicle.getFuel())
                && this.getMass() == otherVehicle.getMass()
                && this.getLoad() == otherVehicle.getLoad()
                && this.getDragCoefficient() == otherVehicle.getDragCoefficient()
                && this.getRollingResistanceCoefficient() == otherVehicle.getRollingResistanceCoefficient()
                && this.getWheelSize() == otherVehicle.getWheelSize();
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Name: ").append(this.name).append("</li>\n");
        sb.append("\t<li>Description: ").append(this.description).append("</li>\n");
        sb.append("\t<li>Type: ").append(this.type).append("</li>\n");
        sb.append("\t<li>Fuel: ").append(this.fuel).append("</li>\n");
        sb.append("\t<li>Mass: ").append(this.mass).append(" Kg</li>\n");
        sb.append("\t<li>Load: ").append(this.load).append(" Kg</li>\n");
        sb.append("\t<li>Drag Coefficient: ").append(this.dragCoefficient).append("</li>\n");
        sb.append("\t<li>Frontal Area: ").append(this.frontalArea).append(" m</li>\n");
        sb.append("\t<li>RRC: ").append(this.rollingResistanceCoefficient).append("</li>\n");
        sb.append("\t<li>Wheel Size: ").append(this.wheelSize).append(" m</li>\n");
        
        if (!this.velocityLimitPerRoad.isEmpty()) {
            sb.append("\t<li>Velocity Limits:<ul>\n");

            for (Entry<String, Double> entry : this.velocityLimitPerRoad.entrySet()) {
                sb.append("\t\t<li>").append(entry.getKey()).append(": ")
                        .append(entry.getValue()).append(" m/s</li>\n");
            }
            
            sb.append("\t</ul></li>\n");
        }

        return sb.toString();
    }

    /**
     * Inserts the velocity limit of a vehicle on a road type.
     *
     * @param roadTypology Type of road where the vehicle circulates.
     * @param velocityLimit Velocity limit on the road.
     */
    public void insertVelocityLimit(String roadTypology, double velocityLimit) {
        this.velocityLimitPerRoad.put(roadTypology, velocityLimit);
    }

    /**
     * Returns the velocity limit of the vehicle on a given road type, if the
     * type is not found the maximum velocity of the vehicle is returned.
     *
     * @param roadTypology Type of the road where the vehicle circulates.
     * @return The velocity limit of the vehicle on a given road type.
     */
    public double getVelocityLimitOfRoad(String roadTypology) {
        if (this.velocityLimitPerRoad.containsKey(roadTypology)) {
            return this.velocityLimitPerRoad.get(roadTypology);
        }

        return this.getMaximumVelocity();
    }

    /**
     * Returns the maximum velocity of the vehicle.
     *
     * @return The maximum velocity of the vehicle.
     */
    public abstract double getMaximumVelocity();

    /**
     * Returns the maximum velocity of the vehicle in the specified gear Index.
     * @param gearIndex (int) The gear index of the gearbox.
     * @return (double) The maxmimum velocity in the specified gear. Returns -1
     * if the gear index does not exist.
     */
    public abstract double getMaximumVelocityInGear(int gearIndex);
            
    /**
     * Returns the final drive
     *
     * @return
     */
    public abstract double getFinalDrive();
    
    /**
     * Returns the Gearbox of the vehicle.
     *
     * @return The Gearbox of the vehicle.
     */
    public abstract Gearbox getGearbox();
    
    /**
     * Returns the accelerator pedal of the vehicle.
     *
     * @return The accelerator pedal list of the vehicle.
     */
    public abstract AcceleratorPedal getAcceleratorPedal();
    
    /**
     * Returns the RPM minimum of the vehicle.
     *
     * @return The RPM minimum of the vehicle.
     */
    public abstract int getRpmMinimum();
    
    /**
     * Validate the vehicle.
     *
     * @return True if vehicle is valid else returns false.
     */
    public boolean validateVehicle() {

        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the vehicle cannot "
                    + "be empty.");
        }

        if (this.description == null || this.description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the vehicle "
                    + "cannot be empty.");
        }

        if (this.type == null || this.type.trim().isEmpty()) {
            throw new IllegalArgumentException("The type of the vehicle cannot "
                    + "be empty.");
        }

        if (this.fuel == null || this.fuel.trim().isEmpty()) {
            throw new IllegalArgumentException("The fuel of the vehicle cannot "
                    + "be empty.");
        }

        if (this.mass <= 0) {
            throw new IllegalArgumentException("The mass of the vehicle must be"
                    + " greater than 0");
        }

        if (this.load < 0) {
            throw new IllegalArgumentException("The load of the vehicle must be"
                    + " positive.");
        }

        if (this.dragCoefficient <= 0) {
            throw new IllegalArgumentException("The drag coefficient of the "
                    + "vehicle must be positive.");
        }

        if (this.frontalArea <= 0) {
            throw new IllegalArgumentException("The frontal area of the vehicle"
                    + " must be positive.");
        }

        if (this.rollingResistanceCoefficient < 0) {
            throw new IllegalArgumentException("The rolling resistance "
                    + "coefficient of the vehicle must be positive");
        }

        if (this.wheelSize <= 0) {
            throw new IllegalArgumentException("The wheel size of the "
                    + "vehicle must be positive");
        }

        return true;
    }
}
