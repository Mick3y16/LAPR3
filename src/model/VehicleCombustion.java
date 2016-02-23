package model;

/**
 * Represents an instance of a combustion vehicle.
 *
 * @author G11
 */
public class VehicleCombustion extends Vehicle {

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
     * Gear box of the vehicle
     */
    private Gearbox gearbox;

    /**
     * List of possible throtlles of the vehicle.
     */
    private AcceleratorPedal acceleratorPedal;

    /**
     * Creates an instance of a combustion vehicle.
     */
    public VehicleCombustion() {
        super();
        this.rpmMinimum = 0;
        this.rpmMaximum = 0;
        this.finalDrive = 0.0;
        this.gearbox = new Gearbox();
        this.acceleratorPedal = new AcceleratorPedal();
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
     * @return
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
     * Returns the textual description of the object in the following format:
     * super.toString() RPM Minimum: 1000 RPM Maximum: 5500 Final Drive: 2.6
     * gearbox.toString() acceleratorPedal.toString()
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
        sb.append(this.gearbox.toString());
        sb.append(this.acceleratorPedal.toString());

        return sb.toString();
    }

    /**
     * Returns the maximum possible velocity for this vehicle.
     *
     * To determine the angular velocity of the wheels of the car, we look into
     * the maximum rpm of the engine and apply a division based on the lowest
     * gear ratio, times the final drive.
     *
     * ω = rpm / (gr * fd)
     *
     * Where: rpm - Maximum rounds per minute of the engine. gr - Gear ratio fd
     * - Final drive
     *
     * Afterwards, the maximum velocity of the vehicle can be determine by the
     * angular velocity multiplied by the radius of the wheel and a constant of
     * 0.10472
     *
     * v = ω * r × 0.10472
     *
     * Where: ω = Angular velocity (RPM) r = Wheel radius (meters)
     *
     * @return The maximum possible velocity of the vehicle.
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
        sb.append(this.gearbox.toStringHTML());
        sb.append(this.acceleratorPedal.toStringHTML());

        return sb.toString();
    }

    /**
     * Validate the vehicle combustion.
     *
     * @return True if vehicle combustion is valid else returns false.
     */
    public boolean validateVehicleCombustion() {

        if (rpmMinimum < 0) {
            throw new IllegalArgumentException("The mimimum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        if (rpmMaximum <= 0) {
            throw new IllegalArgumentException("The maximum rounds per minute "
                    + "of the vehicle should be positive.");
        }

        if (rpmMinimum > rpmMaximum) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (finalDrive <= 0) {
            throw new IllegalArgumentException("The final drive of the vehicle "
                    + "should be positive.");
        }

        return true;
    }
}
