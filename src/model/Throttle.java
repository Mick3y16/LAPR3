package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a throttle of a vehicle on a given position through a list of
 * regimes, which state the torque generated and sfc on a given interval of
 * RPMs.
 *
 * @author G11
 */
public class Throttle implements HTMLExportable {

    /**
     * List of regimes of the throttle.
     */
    private List<Regime> regimeList;

    /**
     * Creates an instance of a throttle of a vehicle on a given position.
     */
    public Throttle() {
        this.regimeList = new ArrayList<>();
    }

    /**
     * Returns an iterator, that allows going trough the list of regimes of the
     * throttle.
     * 
     * @return Iterator.
     */
    public Iterator<Regime> iterator() {
        return this.regimeList.iterator();
    }

    /**
     * Creates an instance of a regime to be filled and saved on the throttle.
     *
     * @return New regime.
     */
    public Regime newRegime() {
        return new Regime();
    }

    /**
     * Adds a regime to the throttle.
     *
     * @param regime The regime which will be inserted.
     * @return True if the regime is added and false if not.
     */
    public boolean insertRegime(Regime regime) {
        if (this.regimeList.contains(regime)) {
            return false;
        }

        return this.regimeList.add(regime);
    }

    /**
     * Returns the closest highest RPM to the specified torque.
     *
     * @param torque (int) The target torque.
     * @return (int) The closest highest RPM.
     */
    public int getClosestHighestRPM(int torque) {
        int result = regimeList.get(0).getRpmHigh();
        int lowestError = Math.abs(torque - regimeList.get(0).getTorque());
        int error;
        for (int i = 1; i < regimeList.size(); i++) {
            error = Math.abs(torque - regimeList.get(i).getTorque());
            if (error < lowestError) {
                lowestError = error;
                result = regimeList.get(i).getRpmHigh();
            }
        }
        return result;
    }

    /**
     * Returns the respective torque based on the given RPMs.
     *
     * @param rpms RPMs of the engine.
     * @return The respective torque based on the given RPMs or -1 if the torque
     * is not found.
     */
    public int getTorqueByRPM(double rpms) {
        for (Regime regime : this.regimeList) {
            if (regime.getRpmLow() <= rpms && rpms <= regime.getRpmHigh()) {
                return regime.getTorque();
            }
        }

        return -1;
    }

    /**
     * Returns the respective SFC based on the given RPMs.
     *
     * @param rpms RPMs of the engine.
     * @return The respective SFC based on the given RPMs or -1 if the SFC is
     * not found.
     */
    public double getSFCByRPM(double rpms) {
        for (Regime regime : this.regimeList) {
            if (regime.getRpmLow() < rpms && rpms <= regime.getRpmHigh()) {
                return regime.getSfc();
            }
        }

        return -1;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * List of regimes: foreach regime { regime.toString() }
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\tList of regimes:\n");

        for (Regime regime : this.regimeList) {
            sb.append(regime.toString()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Compares two objects, first looking at their memory position, following
     * their content and class of origin and in the end compares a set of
     * attributes.
     *
     * @param anotherObject Object used in the comparison.
     * @return True if both objects are equal and false if not.
     */
    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || this.getClass() != anotherObject.getClass()) {
            return false;
        }

        Throttle anotherThrottle = (Throttle) anotherObject;

        // Checking the size of both collections.
        if (this.regimeList.size() != anotherThrottle.regimeList.size()) {
            return false;
        }

        // Looping trough one of the collections checking if the other one contains the element
        for (Regime regime : this.regimeList) {
            if (!anotherThrottle.regimeList.contains(regime)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    @Override
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t\t<li>List of regimes:<ul>\n");

        for (Regime regime : this.regimeList) {
            sb.append(regime.toStringHTML());
        }
        sb.append("\t\t</ul></li>\n");

        return sb.toString();
    }

    /**
     * Validate the throttle.
     *
     * @return True if throttle is valid else returns false.
     */
    public boolean validateThrottle() {

        if (this.regimeList.size() == 0) {
            throw new IllegalArgumentException(
                    "The throttle must have at least one regime");
        }

        return true;
    }

}
