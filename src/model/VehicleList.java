package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a list of vehicles.
 *
 * @author G11
 */
public class VehicleList implements HTMLExportable {

    /**
     * List of vehicles.
     */
    private List<Vehicle> vehiclesList;

    /**
     * List of vehicle names already on the list for id generation.
     */
    private Map<String, Integer> vehicleNames;

    /**
     * Creates a list of vehicles with an empty list.
     */
    public VehicleList() {
        this.vehiclesList = new ArrayList<>();
        this.vehicleNames = new LinkedHashMap<>();
    }

    /**
     * Creates a clone of the target {@link VehicleList}.
     *
     * @param source ({@link VehicleList}) The target vehicle list to clone.
     */
    public VehicleList(VehicleList source) {
        vehiclesList = new ArrayList(source.vehiclesList);
        Collections.copy(vehiclesList, source.vehiclesList);
    }

    /**
     * Creates a new vehicle.
     *
     * @return New vehicle.
     */
    public VehicleCombustion newVehicleCombustion() {
        return new VehicleCombustion();
    }

    /**
     * Creates a new vehicle.
     *
     * @return New vehicle.
     */
    public VehicleElectric newVehicleElectric() {
        return new VehicleElectric();
    }

    /**
     * Returns the number of vehicles in the list.
     *
     * @return Number of vehicles in the list.
     */
    public int size() {
        return this.vehiclesList.size();
    }

    /**
     * Add a vehicle to the vehicles list.
     *
     * @param vehicle Vehicle to be added.
     * @return True if the vehicle was added , otherwise returns false.
     */
    public boolean addVehicle(Vehicle vehicle) {
        if (!this.vehiclesList.contains(vehicle)) {
            while (this.vehicleNames.containsKey(vehicle.getName())) {
                int vehicleCount = this.vehicleNames.get(vehicle.getName());

                vehicle.setName(vehicle.getName() + ++vehicleCount);
            }

            this.vehicleNames.put(vehicle.getName(), 1);
            return this.vehiclesList.add(vehicle);
        }

        return false;
    }

    /**
     * Remove the vehicle received by parameter vehicles list
     *
     * @param vehicle Vehicle to be removed.
     * @return True if the vehicle was added , otherwise returns false.
     */
    public boolean removeVehicle(Vehicle vehicle) {
        return this.vehiclesList.remove(vehicle);
    }

    /**
     * Returns an iterator to allow running through the list of vehicles.
     *
     * @return Iterator.
     */
    public Iterator<Vehicle> iterator() {
        return this.vehiclesList.iterator();
    }

    /**
     * Returns a list containing the names of all the vehicles.
     *
     * @return (List&lt;String&gt;) The list with the name.
     */
    public List<String> getVehicleList() {
        List<String> vehicleList = new ArrayList();

        for (Vehicle vehicle : this.vehiclesList) {
            vehicleList.add(vehicle.getName());
        }

        return vehicleList;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * List of vehicles: foreach vehicle { vehicle.toString }
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("List of Vehicles:\n");
        for (Vehicle vehicle : this.vehiclesList) {
            sb.append(vehicle);
        }

        return sb.toString();
    }

    /**
     * Validate the vehicle list.
     *
     * @return True if vehicle list is valid else returns false.
     */
    public boolean validateVehicleList() {

        if (this.vehiclesList.isEmpty()) {
            throw new IllegalArgumentException("The project must be at least one vehicle.");
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

        sb.append("List of Vehicles:<ul>\n");
        for (Vehicle vehicle : this.vehiclesList) {
            sb.append(vehicle.toStringHTML());
        }
        sb.append("</ul>\n");

        return sb.toString();
    }

}
