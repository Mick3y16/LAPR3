package model;

/**
 * Represents an junction, a place where sections meet, an intersection.
 *
 * @author G11
 */
public class Junction implements HTMLExportable {

    /**
     * Name of the junction
     */
    private String name;

    /**
     * Creates an instance of junction with an empty name.
     */
    public Junction() {
        this.name = "";
    }

    /**
     * Returns the name of the junction
     *
     * @return Name of the junction.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Alters the name of the junction
     *
     * @param name New name of the junction.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The junction's name cannot be"
                    + "empty.");
        }

        this.name = name;
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

        Junction anotherJunction = (Junction) anotherObject;

        return this.name.equals(anotherJunction.name);
    }

    /**
     * Validates a junction in order to determine if it is valid, or there is
     * something missing.
     *
     * @return True if the junction is valid and false if not.
     */
    public boolean validate() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("The junction's name cannot be"
                    + "empty.");
        }

        return true;
    }

    @Override
    public String toStringHTML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
