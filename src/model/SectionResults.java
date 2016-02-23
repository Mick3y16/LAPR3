package model;

/**
 *
 * @author G11
 */
public class SectionResults implements HTMLExportable {

    /**
     * The name of the section.
     */
    private String name;

    /**
     * The energy consumption of the section.
     */
    private double energy;

    /**
     * The fuel consumption of the section.
     */
    private double fuel;

    /**
     * Toll costs of the section.
     */
    private double toll;

    /**
     * Traveling time of the section.
     */
    private double timeTravel;

    /**
     * Type of results witholded.
     * <p>
     * Can be either FASTEST,THEORETICAL_EFFICIENT or REAL_EFFICIENT
     */
    private int type;

    /**
     * Constants for type.
     */
    public static final int FASTEST = 0, THEORETICAL_EFFICIENT = 1, REAL_EFFICIENT = 2;

    /**
     * Creates an instance of SectionResults not getting parameters.
     */
    public SectionResults() {
        this.name = "";
        this.energy = 0;
        this.toll = 0;
        this.timeTravel = 0;
        this.type = -1;
    }

    /**
     * Returns the name of the section.
     *
     * @return The name of the section.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the energy consumption of the section.
     *
     * @return The energy consumption of the section.
     */
    public double getEnergy() {
        return this.energy;
    }

    /**
     * Returns the toll costs of the section.
     *
     * @return The toll costs of the section.
     */
    public double getToll() {
        return this.toll;
    }

    /**
     * Returns the traveling time of the section.
     *
     * @return The traveling time of the section.
     */
    public double getTimeTravel() {
        return this.timeTravel;
    }

    /**
     * Returns the type of analysis.
     * 
     * @return The type of analysis.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the name of the section.
     *
     * @param name The new name of the section.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the energy consumption of the section.
     *
     * @param energy The new energy consumption of the section.
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * Sets the toll costs of the section.
     *
     * @param toll The new toll costs of the section.
     */
    public void setToll(double toll) {
        this.toll = toll;
    }

    /**
     * Sets the traveling time of the section.
     *
     * @param timeTravel The new traveling time of the section.
     */
    public void setTimeTravel(double timeTravel) {
        this.timeTravel = timeTravel;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * Name
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Sets the type of the results of this {@link SectionResults}
     *
     * @param type (int) The type to set. If not defined, throws an exception.
     */
    public void setType(int type) {
        if (type != FASTEST && type != THEORETICAL_EFFICIENT
                && type != REAL_EFFICIENT) {
            throw new IllegalArgumentException("Inputted type is not defined.");
        }

        this.type = type;
    }

    /**
     * Returns the fuel consumed (in grams) on the section.
     *
     * @return (double) The fuel consumed.
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Sets the fuel consumed (in grams) on the section.
     *
     * @param fuel (double) The new fuel value.
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<tr>"
                + "<td>").append(this.name).append("</td>"
                + "<td>").append(this.timeTravel).append(" s</td>"
                + "<td>").append(this.energy).append(" J</td>"
                + "<td>").append(this.toll).append(" €</td>");

        // If the type of the analysis is Real we add the fuel consumption.
        if (this.type == REAL_EFFICIENT) {
            sb.append("<td>").append(this.fuel).append(" g</td>");
        }

        sb.append("</tr>\n");

        return sb.toString();
    }

}
