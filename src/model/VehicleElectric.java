package model;

/**
 * Represents an electric vehicle.
 *
 * @author G11
 */
public class VehicleElectric extends Vehicle {

    /**
     * Minimum RPM of the engine of the vehicle.
     */
    private int rpmMinimum;

    /**
     * Maximum RPM of the engine of the vehicle.
     */
    private int rpmMaximum;

    /**
     * Final drive ratio of the vehicle.
     */
    private double finalDrive;

    /**
     * Gear box of the vehicle.
     */
    private Gearbox gearbox;

    /**
     * List of possible throtlles of the vehicle.
     */
    private AcceleratorPedal acceleratorPedal;
    /**
     * The energy regeneration ratio when braking.
     */
    private double energyRegenerationRatio;

    /**
     * Creates an instance of an electric vehicle.
     */
    public VehicleElectric() {
        super();
        this.rpmMinimum = 0;
        this.rpmMaximum = 0;
        this.finalDrive = 0.0;
        this.gearbox = new Gearbox();
        this.acceleratorPedal = new AcceleratorPedal();
        this.energyRegenerationRatio = 0;

    }

    /**
     * Returns the RPM minimum of the vehicle.
     *
     * @return The RPM minimum of the vehicle.
     */
    @Override
    public int getRpmMinimum() {
        return this.rpmMinimum;
    }

    /**
     * Returns the RPM maximum of the vehicle.
     *
     * @return The RPM maximum of the vehicle.
     */
    public int getRpmMaximum() {
        return this.rpmMaximum;
    }

    /**
     * Returns the final drive
     *
     * @return Final Drive
     */
    @Override
    public double getFinalDrive() {
        return this.finalDrive;
    }

    /**
     * Returns the Gearbox of the vehicle.
     *
     * @return The Gearbox of the vehicle.
     */
    @Override
    public Gearbox getGearbox() {
        return this.gearbox;
    }

    /**
     * Returns the accelerator pedal of the vehicle.
     *
     * @return The accelerator pedal list of the vehicle.
     */
    @Override
    public AcceleratorPedal getAcceleratorPedal() {
        return this.acceleratorPedal;
    }

    /**
     * Returns the maximum velocity of the vehicle.
     *
     * @return The maximum velocity of the vehicle.
     */
    @Override
    public double getMaximumVelocity() {
        double angularVelocityWheel = this.rpmMaximum
                / (this.gearbox.getLowestGearRatio() * this.finalDrive);

        return angularVelocityWheel * super.getWheelSize() * 0.05236;
    }
    
    @Override
    public double getMaximumVelocityInGear(int gearIndex) {
        double result = -1;
        if (gearbox.hasGear(gearIndex))
        {
            double angularVelocityWheel = this.rpmMaximum
                / (this.gearbox.getGearRatioOfGear(gearIndex) * this.finalDrive);
            result = angularVelocityWheel * super.getWheelSize() * 0.05236;
        }
        return result;
    }

    /**
     * Returns the energy regeneration ratio of this {@link VehicleElectric}.
     *
     * @return (double) The energy regeneration ratio.
     */
    public double getEnergyRegenerationRatio() {
        return this.energyRegenerationRatio;
    }

    /**
     * Sets the minimum RPM of the vehicle's engine.
     *
     * @param rpmMinimum The new minimum RPM of the vehicle's engine.
     */
    public void setRpmMinimum(int rpmMinimum) {
        if (rpmMinimum < 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                                + "of the vehicle should be positive.");
        }

        this.rpmMinimum = rpmMinimum;
    }

    /**
     * Sets the maximum RPM of the vehicle's engine.
     *
     * @param rpmMaximum The new maximum RPM of the vehicle's engine.
     */
    public void setRpmMaximum(int rpmMaximum) {
        if (rpmMaximum <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                                + "of the vehicle should be positive.");
        }

        this.rpmMaximum = rpmMaximum;
    }

    /**
     * Sets the final drive of the vehicle.
     *
     * @param finalDrive The new final drive of the vehicle.
     */
    public void setFinalDrive(double finalDrive) {
        if (finalDrive <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                                + "should be positive.");
        }

        this.finalDrive = finalDrive;
    }

    /**
     * Sets the energy regeneration ratio of this {@link VehicleElectric}.
     * <p>
     * The ratio must lie between 0 and 1.
     *
     * @param energyRegenerationRatio (double) The new value for the energy
     * regeneration ratio.
     */
    public void setEnergyRegenerationRatio(double energyRegenerationRatio) {
        if (energyRegenerationRatio < 0 || energyRegenerationRatio > 1) {
            throw new IllegalArgumentException("Energy regeneration ratio must lie between 0 and 1!");
        }
        this.energyRegenerationRatio = energyRegenerationRatio;
    }

    /**
     * Returns the textual description of the object in the following format:
     * super.toString() RPM Minimum: 1000 RPM Maximum: 5500 Final Drive: 2.6
     * Energy Regeneration Ratio: 0.9 gearbox.toString()
     * acceleratorPedal.toString()
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("\tRPM Minimum: ").append(this.rpmMinimum).append("\n");
        sb.append("\tRPM Maximum: ").append(this.rpmMaximum).append("\n");
        sb.append("\tFinal Drive: ").append(this.finalDrive).append(" m\n");
        sb.append("\tEnergy Regeneration Ratio: ").append(this.energyRegenerationRatio).append(" \n");
        sb.append(this.gearbox.toString());
        sb.append(this.acceleratorPedal.toString());

        return sb.toString();
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toStringHTML());
        sb.append("\t<li>RPM Minimum: ").append(this.rpmMinimum).append("</li>\n");
        sb.append("\t<li>RPM Maximum: ").append(this.rpmMaximum).append("</li>\n");
        sb.append("\t<li>Final Drive: ").append(this.finalDrive).append(" m</li>\n");
        sb.append("\t<li>Energy Regeneration Ratio: ").append(this.energyRegenerationRatio).append("</li>\n");
        sb.append(this.gearbox.toStringHTML());
        sb.append(this.acceleratorPedal.toStringHTML());

        return sb.toString();
    }

    /**
     * Validate the vehicle Electric.
     *
     * @return True if vehicle electric is valid else returns false.
     */
    public boolean validateVehicleElectric() {

        if (this.rpmMinimum <= 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                                + "of the vehicle should be positive.");
        }

        if (this.rpmMaximum <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                                + "of the vehicle should be positive.");
        }

        if (this.rpmMinimum > rpmMaximum) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (this.finalDrive <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                                + "should be positive.");
        }
        if (this.energyRegenerationRatio < 0 || this.energyRegenerationRatio > 1) {
            throw new IllegalArgumentException("The energy regeneration ratio "
                                + "of the vehicle should be ]0-1[.");
        }

        return true;
    }
}
