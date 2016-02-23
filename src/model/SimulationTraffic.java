package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of traffic of a simulation.
 *
 * @author G11
 */
public class SimulationTraffic implements HTMLExportable {

    /**
     * List of traffic of a simulation;
     */
    private List<Traffic> trafficList;

    /**
     * Create an instance of SimulationTraffic.
     */
    public SimulationTraffic() {
        this.trafficList = new ArrayList<>();
    }

    /**
     * Create an instance of SimulationTraffic with the same characteristics as
     * the target SimulationTraffic, creating a clone.
     * 
     * @param simulationTrafic The target SimulationTraffic to copy.
     */
    public SimulationTraffic(SimulationTraffic simulationTrafic) {
        this.trafficList = new ArrayList<>();
        
        Iterator<Traffic> iterator = simulationTrafic.iterator();
        
        while (iterator.hasNext()) {
            Traffic traffic = iterator.next();
            
            this.trafficList.add(traffic);
        }
    }

    /**
     * Returns the textual description of the object in the following format:
     * List of Traffic: foreach traffic { traffic.toString }
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("List of Traffic:\n");
        for(Traffic traffic : this.trafficList) {
            sb.append(traffic.toString()).append("\n");
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
        
        sb.append("List of Traffic:<ul>\n");
        for(Traffic traffic : this.trafficList) {
            sb.append(traffic.toStringHTML());
        }
        sb.append("</ul>");
        
        return sb.toString();
    }

    /**
     * Creates traffic that can be added to the traffic list.
     * 
     * @return New traffic.
     */
    public Traffic newTraffic() {
        return new Traffic();
    }

    /**
     * Adds traffic to the traffic list.
     *
     * @param traffic Traffic to be added.
     * @return True if successfully added.
     */
    public boolean addTraffic(Traffic traffic) {
        return this.trafficList.add(traffic);
    }
    
    /**
     * Returns the index of the specified {@link Traffic}.
     * @param t ({@link Traffic}) The desired traffic.
     * @return (int) The index of the traffic. Returns -1 if the traffic
     * is not in this list.
     */
    public int getIndexOf(Traffic t)
    {
        return trafficList.indexOf(t);
    }
    
    /**
     * Returns the index-th {@link Traffic} of this list.
     * @param index (int) The target index of the traffic.
     * @return ({@link Traffic}) The index-th {@link Traffic}.
     */
    public Traffic get(int index)
    {
        return trafficList.get(index);
    }
    
    /**
     * Returns the size of this list.
     * @return (int) The size of this list.
     */
    public int size()
    {
        return trafficList.size();
    }

    /**
     * Removes traffic from the traffic list.
     *
     * @param traffic Traffic to be removed.
     * @return True if successfully removed.
     */
    public boolean removeTraffic(Traffic traffic) {
        return this.trafficList.remove(traffic);
    }
    
    /**
     * Returns a list with the names of all the vehicles in this
     * simulation traffic.
     * @return (List&lt;String&gt;) The list of names of all the vehicles.
     */
    public List<String> getAllVehicles()
    {
        List<String> result = new ArrayList();
        for (Traffic element:trafficList)
        {
            if (!result.contains(element.getVehicle().getName()))
            {
                result.add(element.getVehicle().getName());
            }
        }
        return result;
    }

    /**
     * Returns an iterator to allow running through the list of traffic.
     *
     * @return Iterator.
     */
    public Iterator<Traffic> iterator() {
        return this.trafficList.iterator();
    }

    /**
     * Validates if the traffic of the simulation is valid, atleast 1 traffic
     * must exist.
     * 
     * @return True if valid, else it throws an exception.
     */
    public boolean validate() {
        if (this.trafficList.isEmpty()) {
            throw new IllegalArgumentException("TThere is no traffic in the "
                    + "imported simulation.");
        }
        
        return true;
    }

}
