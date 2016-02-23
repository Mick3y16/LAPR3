package model;

/**
 * Represents traffic through a begin node, an end node, a vehicle and its
 * arrival rate.
 *
 * @author G11
 */
public class Traffic implements HTMLExportable {

    /**
     * Begin Node.
     */
    private Junction beginNode;

    /**
     * End Node.
     */
    private Junction endNode;

    /**
     * Vehicle.
     */
    private Vehicle vehicle;

    /**
     * Arrival Rate.
     */
    private int arrivalRate;

    /**
     * Creates an instance of {@link Traffic} with null parameters.
     */ 
    public Traffic() {
        this.beginNode = null;
        this.endNode = null;
        this.vehicle = null;
        this.arrivalRate = 0;
    }

    /**
     * Creates an instance of {@link Traffic} with the specified parameters.
     *
     * @param beginNode ({@link model.Junction}) The start junction.
     * @param endNode ({@link model.Junction}) The finish junction.
     * @param vh ({@link model.Vehicle}) The vehicle injected in this vehicle.
     * @param arrival_rate (int) The arrival rate of the traffic (in minutes).
     */
    public Traffic(Junction beginNode, Junction endNode, Vehicle vh, int arrival_rate) {
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.vehicle = vh;
        this.arrivalRate = arrival_rate;
    }

    /**
     * Returns the begin node of the traffic.
     *
     * @return The begin node of the traffic.
     */
    public Junction getBeginNode() {
        return this.beginNode;
    }

    /**
     * Returns the end node of the traffic.
     *
     * @return The end node of the traffic.
     */
    public Junction getEndNode() {
        return this.endNode;
    }

    /**
     * Returns the vehicle of the traffic.
     *
     * @return The vehicle of the traffic.
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Returns the arrival rate of the traffic.
     *
     * @return The arrival rate of the traffic.
     */
    public int getArrivalRate() {
        return this.arrivalRate;
    }

    /**
     * Sets the begin node of the traffic.
     *
     * @param beginNode The new begin node of the traffic.
     */
    public void setBeginNode(Junction beginNode) {
        if (beginNode == null) {
            throw new IllegalArgumentException("The begin node can't be null.");
        }

        this.beginNode = beginNode;
    }

    /**
     * Sets the end node of the traffic.
     *
     * @param endNode The new end node of the traffic.
     */
    public void setEndNode(Junction endNode) {
        if (endNode == null) {
            throw new IllegalArgumentException("The end node can't be null.");
        }

        this.endNode = endNode;
    }

    /**
     * Sets the vehicle of the traffic.
     *
     * @param vehicle The new vehicle of the traffic.
     */
    public void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("The vehicle can't be null.");
        }

        this.vehicle = vehicle;
    }

    /**
     * Sets the arrival rate of the traffic.
     *
     * @param arrivalRate The new arrival rate of the traffic.
     */
    public void setArrivalRate(int arrivalRate) {
        if (arrivalRate <= 0) {
            throw new IllegalArgumentException("The arrival rate should be "
                    + "positive.");
        }

        this.arrivalRate = arrivalRate;
    }

    /**
     * Returns the textual description of the object in the following format:
     * Begin node: begin node End node: end node Vehicle: vehicle Arrival Rate:
     * arrival rate
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\tBegin node: ").append(this.beginNode.getName()).append("\n");
        sb.append("\tEnd node: ").append(this.endNode.getName()).append("\n");
        sb.append("\tVehicle: ").append(this.vehicle.getName()).append("\n");
        sb.append("\tArrival Rate: ").append(this.arrivalRate).append("\n");
                
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

        sb.append("\t<li>Begin node: ").append(this.beginNode.getName()).append("</li>\n");
        sb.append("\t<li>End node: ").append(this.endNode.getName()).append("</li>\n");
        sb.append("\t<li>Vehicle: ").append(this.vehicle.getName()).append("</li>\n");
        sb.append("\t<li>Arrival Rate: ").append(this.arrivalRate).append("</li>\n");
                
        return sb.toString();
    }

    /**
     * Validates the traffic in order to determine if all attributes are in
     * place.
     * 
     * @return True if the traffic is valid else it throws an exception. 
     * 
     */
    public boolean validate() {
        if (this.beginNode == null) {
            throw new IllegalArgumentException("The begin node can't be null.");
        }

        if (this.endNode == null) {
            throw new IllegalArgumentException("The end node can't be null.");
        }

        if (this.vehicle == null) {
            throw new IllegalArgumentException("The vehicle can't be null");
        }

        if (this.arrivalRate <= 0) {
            throw new IllegalArgumentException("The arrival rate should be "
                    + "positive");
        }
        
        if (this.beginNode.equals(this.endNode)) {
            throw new IllegalArgumentException("Both the begin and end node "
                    + "can't be the same.");
        }

        return true;
    }

}
