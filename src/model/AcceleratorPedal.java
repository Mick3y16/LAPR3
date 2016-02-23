package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Represents an accelerator pedal through a list of throttles based on how much
 * the pedal is pressed (percentage).
 *
 * @author G11
 */
public class AcceleratorPedal implements HTMLExportable {

    /**
     * List of throttles.
     */
    private Map<Integer, Throttle> throttleList;

    /**
     * Creates an instance of an accelerator pedal through an empty map which
     * keeps the percentage of how much the pedal is pressed and throttle
     * itself.
     */
    public AcceleratorPedal() {
        this.throttleList = new LinkedHashMap<>();
    }

    /**
     * Creates a throttle.
     *
     * @return New throttle.
     */
    public Throttle newThrottle() {
        return new Throttle();
    }

    /**
     * Returns an entry set with all throttles and their respective percentage.
     * 
     * @return Entry set.
     */
    public Set<Entry<Integer, Throttle>> entrySet() {
        return this.throttleList.entrySet();
    }

    /**
     * Inserts a throttle in the accelerator pedal.
     *
     * @param percentage How much the pedal is pressed.
     * @param throttle Throttle.
     * @return True if the throttle is added and false if not.
     */
    public boolean insertThrottle(int percentage, Throttle throttle) {
        if (this.throttleList.containsKey(percentage)) {
            return false;
        }

        this.throttleList.put(percentage, throttle);

        return this.throttleList.containsKey(percentage);
    }

    /**
     * Returns the closest highest RPM to the specified torque.
     *
     * @param torque (int) The target torque.
     * @param throttle (int) The target throttle.
     * @return (int) The closest highest RPM.
     */
    public int getClosestHighestRPM(int throttle, int torque) {
        return throttleList.get(throttle).getClosestHighestRPM(torque);
    }

    /**
     * Returns the respective torque based on how much the pedal is pressed and
     * the given RPMs.
     *
     * @param percentage Percentage of how much the pedal is pressed.
     * @param rpms RPMs of the engine.
     * @return The respective torque based on the given percentage and rpms or
     * -1 if the torque is not found.
     */
    public int getTorqueByPercentageAndRPM(int percentage, double rpms) {
        if (this.throttleList.containsKey(percentage)) {
            Throttle throttle = this.throttleList.get(percentage);

            return throttle.getTorqueByRPM(rpms);
        }

        return -1;
    }

    /**
     * Returns the respective SFC based on how much the pedal is pressed and the
     * given RPMs.
     *
     * @param percentage Percentage of how much the pedal is pressed.
     * @param rpms RPMs of the engine.
     * @return The respective SFC based on the given percentage and rpms or -1
     * if the SFC is not found.
     */
    public double getSFCByPercentageAndRPM(int percentage, double rpms) {
        if (this.throttleList.containsKey(percentage)) {
            Throttle throttle = this.throttleList.get(percentage);

            return throttle.getSFCByRPM(rpms);
        }

        return -1;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * List of throttles: foreach throttle {Percentage: n% throttle.toString()}
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tList of throttles:\n");

        for (Entry<Integer, Throttle> entry : this.throttleList.entrySet()) {
            sb.append("\t\tPercentage: ").append(entry.getKey()).append("%\n");
            sb.append(entry.getValue().toString());
        }

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

        sb.append("\t<li>List of throttles:<ul>\n");
        for (Entry<Integer, Throttle> entry : this.throttleList.entrySet()) {
            sb.append("\t\t<li>Percentage: ").append(entry.getKey()).append("%</li>\n");
            sb.append(entry.getValue().toStringHTML());
        }
        sb.append("\t</ul></li>\n");

        return sb.toString();
    }

    /**
     * Validate the accelerator pedal.
     *
     * @return True if accelerator pedal is valid else returns false.
     */
    public boolean validateAcceleratorPedal() {

        if (this.throttleList.entrySet().isEmpty()) {
            throw new IllegalArgumentException(
                    "The accelerator pedal must have at least one throttle.");
        }

        return true;
    }
}
