package model;

/**
 * Represents a segment (part of a section of a road), through an index, an
 * initial height, an angle, a length, the maximum and minimum velocities and
 * the max number of vehicles allowed in it.
 *
 * @author G11
 */
public class Segment {

    /**
     * Index of segment.
     */
    private int index;

    /**
     * Initial height of segment.
     *
     * When the height is 0, the road is at sea level, therefore negatives
     * heights are below sea level while positive heights are above it.
     */
    private double initialHeight;

    /**
     * Angle [45º, -45º] of segment.
     */
    private double angle;

    /**
     * Length of segment.
     */
    private double length;

    /**
     * Maximum velocity of segment.
     */
    private double maximumVelocity;

    /**
     * Minimum velocity of segment.
     */
    private double minimumVelocity;

    /**
     * Maximum number vehicles of segment.
     */
    private int maximumNumberVehicles;

    /**
     * Creates an instance of Segment with default attributes.
     */
    public Segment() {
        this.index = -1;
        this.initialHeight = 0.0;
        this.angle = 0.0;
        this.length = 0.0;
        this.maximumVelocity = 0.0;
        this.minimumVelocity = 0.0;
        this.maximumNumberVehicles = -1;
    }

    /**
     * Returns the index of the segment.
     *
     * @return Index of the segment.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the initial height of the segment.
     *
     * @return Initial height of the segment.
     */
    public double getInitialHeight() {
        return this.initialHeight;
    }

    /**
     * Returns the angle of the segment.
     *
     * @return Angle of the segment.
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Returns the length of the segment.
     *
     * @return Length of the segment.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Returns the maximum velocity of the segment.
     *
     * @return Maximum velocity of the segment.
     */
    public double getMaximumVelocity() {
        return this.maximumVelocity;
    }

    /**
     * Returns the minimum velocity of the segment.
     *
     * @return Minimum velocity of the segment.
     */
    public double getMinimumVelocity() {
        return this.minimumVelocity;
    }

    /**
     * Returns the maximum number vehicles of the segment.
     *
     * @return Maximum number vehicles of the segment.
     */
    public int getMaximumNumberVehicles() {
        return this.maximumNumberVehicles;
    }

    /**
     * Alters the index of the segment.
     *
     * @param index New index of the segment.
     */
    public void setIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("The index should be greater than"
                    + " or equal to 0");
        }

        this.index = index;
    }

    /**
     * Alters the initial height of the segment.
     *
     * @param initialHeight New initial height of the segment.
     */
    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    /**
     * Alters the angle angle the segment.
     *
     * @param angle New angle of the segment.
     */
    public void setAngle(double angle) {
        if (-45 > angle || angle > 45) {
            throw new IllegalArgumentException("The angle should be greater"
                    + " than -45º and less than 45º");
        }

        this.angle = angle;
    }

    /**
     * Alters the length of the segment.
     *
     * @param length New length of the segment.
     */
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("The length should be greater"
                    + " than 0");
        }

        this.length = length;
    }

    /**
     * Alters the maximum velocity of the segment.
     *
     * @param maximumVelocity New maximum velocity of the segment.
     */
    public void setMaximumVelocity(double maximumVelocity) {
        if (maximumVelocity < 0) {
            throw new IllegalArgumentException("The maximum velocity should be"
                    + " greater than 0");
        }

        this.maximumVelocity = maximumVelocity;
    }

    /**
     * Alters the minimum velocity of the segment.
     *
     * @param minimumVelocity New minimum velocity of the segment.
     */
    public void setMinimumVelocity(double minimumVelocity) {
        if (minimumVelocity < 0) {
            throw new IllegalArgumentException("The minimum velocity should be"
                    + " greater than or equal to 0");
        }

        this.minimumVelocity = minimumVelocity;
    }

    /**
     * Alters the maximum number vehicles of the segment.
     *
     * @param maximumNumberVehicles New maximum number vehicles of the segment.
     */
    public void setMaximumNumberVehicles(int maximumNumberVehicles) {
        if (maximumNumberVehicles <= 0) {
            throw new IllegalArgumentException("The maximum number of vehicles"
                    + " should be greater than 0");
        }

        this.maximumNumberVehicles = maximumNumberVehicles;
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

        Segment otherSegment = (Segment) otherObject;

        return this.getIndex() == otherSegment.getIndex();
    }

    /**
     * Validate the segment.
     *
     * @return True if segment is valid else returns false.
     */
    public boolean validateSegment() {
        if (this.index < 0) {
            throw new IllegalArgumentException("The index should be greater than"
                    + " or equal to 0");
        }

        if (-45 > this.angle || this.angle > 45) {
            throw new IllegalArgumentException("The angle shoulb be less than"
                    + " 50");
        }

        if (this.length <= 0) {
            throw new IllegalArgumentException("The length should be greater"
                    + " than 0");
        }

        if (this.maximumVelocity < 0) {
            throw new IllegalArgumentException("The maximum velocity should be"
                    + " greater than 0");
        }

        if (this.minimumVelocity < 0) {
            throw new IllegalArgumentException("The minimum velocity should be"
                    + " greater than or equal to 0");
        }

        if (this.maximumVelocity <= this.minimumVelocity) {
            throw new IllegalArgumentException("The maximum velocity should be"
                    + " greater than minimum velocity");
        }

        if (this.maximumNumberVehicles <= 0) {
            throw new IllegalArgumentException("The maximum number of vehicles"
                    + " should be greater than 0");
        }

        return true;
    }

    /**
     * Returns the textual description of the object in the following format:
     * index, initial height, angle, lenght, rolling resistence coeficiente,
     * minimum velocity, maximum velocity, maximum number vehicles.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\tSegment: ").append(this.index).append("\n");
        sb.append("\t\t\tInitial Height: ").append(this.initialHeight).append(" m\n");
        sb.append("\t\t\tAngle: ").append(String.format("%.3f", this.angle)).append(" º\n");
        sb.append("\t\t\tLenght: ").append(String.format("%.3f", this.length)).append(" m\n");
        sb.append("\t\t\tMinimum velocity: ").append(this.minimumVelocity).append(" m/s\n");
        sb.append("\t\t\tMaximum velocity: ").append(this.maximumVelocity).append(" m/s\n");
        sb.append("\t\t\tMaximum number of vehicles: ").append(this.maximumNumberVehicles).append(" vehicles\n");

        return sb.toString();
    }

    /**
     * Returns the textual description of the object in html format..
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t<li>Segment: ").append(this.index).append("<ul>\n");
        sb.append("\t\t\t<li>Initial Height: ").append(this.initialHeight).append("m</li>\n");
        sb.append("\t\t\t<li>Angle: ").append(String.format("%.3f", this.angle)).append("º</li>\n");
        sb.append("\t\t\t<li>Lenght: ").append(String.format("%.3f", this.length)).append(" m</li>\n");
        sb.append("\t\t\t<li>Minimum velocity: ").append(this.minimumVelocity).append(" m/s</li>\n");
        sb.append("\t\t\t<li>Maximum velocity: ").append(this.maximumVelocity).append(" m/s</li>\n");
        sb.append("\t\t\t<li>Maximum number of vehicles: ").append(this.maximumNumberVehicles).append(" vehicles</li>\n");
        sb.append("\t\t</li></ul>\n");

        return sb.toString();
    }
}
