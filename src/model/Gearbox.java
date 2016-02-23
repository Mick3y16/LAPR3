package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents a gear box of a vehicle with a set of gears.
 *
 * @author G11
 */
public class Gearbox implements HTMLExportable {

    /**
     * Map of gear of the gearbox.
     */
    private Map<Integer, Double> gearbox;

    /**
     * Creates an instance of a gearbox with an empty map of gears.
     */
    public Gearbox() {
        this.gearbox = new LinkedHashMap();
    }

    /**
     * Returns an entry set that allows going through the list of gears and their
     * respective ratio.
     * 
     * @return Entry set.
     */
    public Set<Entry<Integer, Double>> entrySet() {
        return this.gearbox.entrySet();
    }

    /**
     * Returns the textual description of the object in the following format:
     * Gearbox: 1 - Ratio: 3.2
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tGearbox:\n");
        for (Entry<Integer, Double> entry : this.gearbox.entrySet()) {
            sb.append("\t\t").append(entry.getKey()).append(" - Ratio: ")
                    .append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Inserts a gear in the gearbox.
     *
     * @param gear Gear number.
     * @param ratio Gear ratio.
     */
    public void insertGearAndGearRatio(int gear, double ratio) {
        this.gearbox.put(gear, ratio);
    }

    /**
     * Checks if a gear exists in the gearbox
     *
     * @param gear Gear number.
     * @return True if the gear exists and false if not.
     */
    public boolean hasGear(int gear) {
        return this.gearbox.containsKey(gear);
    }

    /**
     * Returns the lowest gear ratio available, typically this ratio is found on
     * the highest gear of the vehicle, usually the fifth.
     *
     * @return The loewst gear ratio.
     */
    public double getLowestGearRatio() {
        double gearRatio = 10.0;

        for (Entry<Integer, Double> gear : this.gearbox.entrySet()) {
            if (gear.getValue() < gearRatio) {
                gearRatio = gear.getValue();
            }
        }

        return gearRatio;
    }

    /**
     * Returns the ratio of a gear inside the gearbox.
     *
     * @param gear Gear number.
     * @return Returns the ratio of a gear.
     */
    public double getGearRatioOfGear(int gear) {
        return this.gearbox.get(gear);
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t<li>Gearbox:<ul>\n");
        for (Entry<Integer, Double> entry : this.gearbox.entrySet()) {
            sb.append("\t\t<li>").append(entry.getKey()).append(" - Ratio: ")
                    .append(entry.getValue()).append("</li>\n");
        }
        sb.append("\t</ul></li>\n");

        return sb.toString();
    }

    /**
     * Validate the gear box.
     *
     * @return True if gear box is valid else returns false.
     */
    public boolean validateGearBox() {

        if (this.gearbox.entrySet().size() <= 0) {
            throw new IllegalArgumentException(""
                    + "The gearbox must have at least one gear");
        }

        for (Entry<Integer, Double> entry : this.gearbox.entrySet()) {
            if (entry.getValue() == 0) {
                throw new IllegalArgumentException("The ratio cannot be 0.");
            }
        }

        return true;
    }
}
