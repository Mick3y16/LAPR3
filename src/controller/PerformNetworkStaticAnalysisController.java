package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.ExportHTML;
import model.Junction;
import model.NetworkAnalysis;
import model.SectionResults;
import model.Simulator;
import model.Vehicle;
import utils.Tuple;

/**
 *
 * @author G11
 */
public class PerformNetworkStaticAnalysisController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Begin node.
     */
    private Junction beginNode;

    /**
     * End node.
     */
    private Junction endNode;

    /**
     * NetWork Analysis.
     */
    private NetworkAnalysis na;
    
    

    /**
     * Creates an instance of PerformNetworkStaticAnalysisController receiving
     * simulator.
     *
     * @param simulator
     */
    public PerformNetworkStaticAnalysisController(Simulator simulator) {
        this.simulator = simulator;
        this.beginNode = null;
        this.endNode = null;
        this.na = new NetworkAnalysis(simulator.getOpenProject());
    }

    /**
     * Returns the list of vehicles.
     *
     * @return List of vehicles.
     */
    public List<String> getVehicleList() {
        return this.simulator.getOpenProject().getVehicleNamesList();
    }

    /**
     * Alters the begin node.
     *
     * @param junctionName
     */
    public void setBeginNode(String junctionName) {
        this.beginNode = this.simulator.getOpenProject().getJunctionByName(junctionName);
    }

    /**
     * Alters the end node.
     * 
     * @param junctionName The name of the end node.
     */
    public void setEndNode(String junctionName) {
        this.endNode = this.simulator.getOpenProject().getJunctionByName(junctionName);
    }

    /**
     * Gets the results of the fastest path algorithm.
     * 
     * @return The results of the fastest path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double,Double>>> getFastestResults() {
        return this.na.getResultsFastest();
    }

    /**
     * Gets the results of the efficient path algorithm.
     * 
     * @return The results of the efficient path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Tuple<Double,Double>>> getTheoreticalEfficientResults() {
        return this.na.getResultsShortest();
    }
    
    /**
     * Gets the results of the efficient path algorithm.
     * 
     * @return The results of the efficient path algorithm.
     */
    public Map<String, Tuple<LinkedList<SectionResults>, Double[]>> getRealEfficientResults() {
        return this.na.getResultsReal();
    }

    /**
     * Gets the list of nodes. 
     * 
     * @return The list of nodes of the project.
     */
    public List<String> getNodesList() {
        List<String> result = new ArrayList<>();

        for (Junction junction : this.simulator.getOpenProject().getJunctions()) {
            result.add(junction.getName());
        }

        return result;
    }
    
    /**
     * Performs the analysis over the road network.
     * @param vehicleNames (List&lt;{@link Vehicle}&gt;) A list containing the name of vehicles to use.
     * @param fastest (boolean) Whether the fastest path should be analysed.
     * @param theoretical (boolean) Whether the theoretical most efficient path should be analysed.
     * @param real (boolean) Whether the most efficient path under real conditions should be analysed.
     */
    public void calculate(List<String> vehicleNames,boolean fastest,boolean theoretical, boolean real)
    {
        Iterator<Vehicle> vehicles = simulator.getOpenProject().getVehicleList().iterator();
        na.clearResults();
        while (vehicles.hasNext())
        {
            Vehicle v = vehicles.next();
            for (String focus:vehicleNames)
            {
                if (v.getName().equals(focus))
                {
                    na.addVehicle(v);
                    vehicleNames.remove(focus);
                    break;
                }
            }
        }
        na.setNodes(beginNode, endNode);
        na.calculate(fastest, theoretical, real);
    }
    
    /**
     * Exports the results of the selected vehicles into the specified path.
     * @param vehicles (List&lt;String&gt;) The list of vehicle names to export.
     * @param path (String) The file path.
     */
    public void exportHTML(List<String> vehicles,String path)
    {
        ExportHTML p = new ExportHTML();
        p.exportNetworkAnalysis(na, vehicles, path);
    }
}
