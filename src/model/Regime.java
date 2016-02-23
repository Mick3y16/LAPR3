package model;

/**
 * Represents a regime in the throttle percentage of a vehicle.
 *
 * @author G11
 */
public class Regime implements HTMLExportable {

    /**
     * Torque generated in the given range of rpms.
     */
    private int torque;

    /**
     * Lowest value in the given range of rpms.
     */
    private int rpmLow;

    /**
     * Higher value in the given range of rpms.
     */
    private int rpmHigh;

    /**
     * Specific fuel consumption in the given range of rpms.
     */
    private double sfc;

    /**
     * Creates an instance of a regime in the throttle percentage of a vehicle
     * for an interval of rpms.
     */
    public Regime() {
        this.torque = 0;
        this.rpmLow = 0;
        this.rpmHigh = 0;
        this.sfc = 0.0;
    }

    /**
     * Returns the torque generated in the given range of rpms.
     *
     * @return The torque generated in the given range of rpms.
     */
    public int getTorque() {
        return this.torque;
    }

    /**
     * Returns the lowest value in the given range of rpms.
     *
     * @return The lowest value in the given range of rpms.
     */
    public int getRpmLow() {
        return this.rpmLow;
    }

    /**
     * Returns the highest value in the given range of rpms.
     *
     * @return The highest value in the given range of rpms.
     */
    public int getRpmHigh() {
        return this.rpmHigh;
    }

    /**
     * Returns the specific fuel consumption in the given range of rpms.
     *
     * @return The specific fuel consumption in the given range of rpms.
     */
    public double getSfc() {
        return this.sfc;
    }

    /**
     * Sets the torque generated in the given range of rpms.
     *
     * @param torque The new torque generated in the given range of rpms.
     */
    public void setTorque(int torque) {
        if (torque <= 0) {
            throw new IllegalArgumentException("Torque must be positive.");
        }

        this.torque = torque;
    }

    /**
     * Sets the lowest value in the given range of rpms.
     *
     * @param rpmLow The new lowest value in the given range of rpms.
     */
    public void setRpmLow(int rpmLow) {
        if (rpmLow < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        this.rpmLow = rpmLow;
    }

    /**
     * Sets the highest value in the given range of rpms.
     *
     * @param rpmHigh The new highest value in the given range of rpms.
     */
    public void setRpmHigh(int rpmHigh) {
        if (rpmHigh <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        this.rpmHigh = rpmHigh;
    }

    /**
     * Sets the specific fuel consumption in the given range of rpms.
     *
     * @param sfc The new specific fuel consumption in the given range of rpms.
     */
    public void setSfc(double sfc) {
        if (sfc < 0) {
            throw new IllegalArgumentException("SFC must be positive.");
        }

        this.sfc = sfc;
    }

    /**
     * Returns the textual description of the object in the following format:
     * Torque: 250 RPM lower interval: 1000 RPM higher interval: 2499 SFC: 10.2
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\tTorque: ").append(this.torque).append("\n");
        sb.append("\t\t\tRPM lower interval: ").append(this.rpmLow).append("\n");
        sb.append("\t\t\tRPM higher interval: ").append(this.rpmHigh).append("\n");
        sb.append("\t\t\tSFC: ").append(this.sfc).append("\n");

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

        Regime otherRegime = (Regime) otherObject;

        return ((otherRegime.rpmLow >= this.rpmLow
                && otherRegime.rpmLow <= this.rpmHigh)
                || (otherRegime.rpmHigh >= this.rpmLow
                && otherRegime.rpmHigh <= this.rpmHigh));

    }

    /**
     * Returns the textual description of the object in the HTML format.
     *
     * @return Textual description of the object in HTML.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t<li>Torque: ").append(this.torque).append("</li>\n");
        sb.append("\t\t\t<li>RPM lower interval: ").append(this.rpmLow).append("</li>\n");
        sb.append("\t\t\t<li>RPM higher interval: ").append(this.rpmHigh).append("</li>\n");
        sb.append("\t\t\t<li>SFC: ").append(this.sfc).append("</li>\n");

        return sb.toString();
    }

    /**
     * Validate the regime.
     *
     * @return True if regime is valid else returns false.
     */
    public boolean validateRegime() {

        if (torque <= 0) {
            throw new IllegalArgumentException("Torque must be positive.");
        }

        if (rpmLow < 0) {
            throw new IllegalArgumentException("RPM low must be positive.");
        }

        if (rpmHigh <= 0) {
            throw new IllegalArgumentException("RPM high must be positive.");
        }

        if (rpmLow > rpmHigh) {
            throw new IllegalArgumentException("RPM low should be less than RPM high.");
        }

        if (sfc < 0) {
            throw new IllegalArgumentException("SFC must be positive.");
        }

        return true;
    }

}
