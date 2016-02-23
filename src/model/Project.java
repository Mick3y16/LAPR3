package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Vertex;

/**
 * Represents a project through a name, a description a road map and a list of
 * vehicles.
 *
 * @author G11
 */
public class Project {

    /**
     * Name of project.
     */
    private String name;

    /**
     * Description of project.
     */
    private String description;

    /**
     * Graph representation of the roadmap of the project.
     */
    private Graph<Junction, Section> roadMap;

    /**
     * List of vehicles of the project.
     */
    private VehicleList vehicleList;

    /**
     * Creates an instance of project with null parameters.
     */
    public Project() {
        this.name = "";
        this.description = "";
        this.roadMap = new Graph<>(true);
        this.vehicleList = new VehicleList();
    }

    /**
     * Creates an instance of {@link Project} with the same characteristics as
     * the target {@link Project}, creating a clone.
     *
     * @param source ({@link Project}) The target project to copy.
     */
    public Project(Project source) {
        name = source.getName();
        description = source.getDescription();
        roadMap = source.roadMap.clone();
        vehicleList = new VehicleList(source.getVehicleList());
    }

    /**
     * Returns the name of the project.
     *
     * @return Name of the project.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the project.
     *
     * @return Description of the project.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the list of junctions.
     *
     * @return List of junctions.
     */
    public List<Junction> getJunctions() {
        List<Junction> result = new ArrayList();
        Iterator<Vertex<Junction, Section>> iterator
                = this.roadMap.getVertices().iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next().getElement());
        }

        return result;
    }

    /**
     * Returns the list of sections.
     *
     * @return List of sections.
     */
    public List<Section> getSections() {
        List<Section> result = new ArrayList();
        Iterator<Edge<Junction, Section>> iterator = this.roadMap.getEdges().iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next().getElement());
        }

        return result;
    }

    /**
     * Returns a list with all the names of the roads of this project.
     *
     * @return (List&lt;String&gt;) The list with the names of the roads.
     */
    public List<String> getRoadNames() {
        List<String> result = new ArrayList();

        for (Edge<Junction, Section> focus : roadMap.getEdges()) {
            String roadName = focus.getElement().getRoadName();
            if (!result.contains(roadName)) {
                result.add(roadName);
            }
        }

        return result;
    }

    /**
     * Returns the list of vehicles of the project.
     *
     * @return ({@link VehicleList}) List of vehicles
     */
    public VehicleList getVehicleList() {
        return this.vehicleList;
    }

    /**
     * Returns a list with the names of the vehicles of this {@link Project}.
     *
     * @return (List&lt;String&gt;) A list containing the names of the vehicles.
     */
    public List<String> getVehicleNamesList() {
        return vehicleList.getVehicleList();
    }

    /**
     * Returns the number of edges of the project.
     *
     * @return Number of edges.
     */
    public int getNumberEdges() {
        return this.roadMap.getNumEdges();
    }

    /**
     * Alters the name of the project.
     *
     * @param name New name of the project.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the project cannot "
                    + "be empty.");
        }

        this.name = name;
    }

    /**
     * Alters the description of the project.
     *
     * @param description New description of the project.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the project "
                    + "cannot be empty.");
        }

        this.description = description;
    }

    /**
     * Returns the textual description of the object in the following format:
     *
     * Name Description Road map (Graph.toString) List of vehicles
     * (VehiclesList.toString)
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(this.name).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append(this.roadMap);
        sb.append(this.vehicleList);

        return sb.toString();
    }

    /**
     * Creates a new junction that can be added to the road map.
     *
     * @return New junction.
     */
    public Junction newJunction() {
        return new Junction();
    }

    /**
     * Adds a junction to the road map to be used to link sections of roads.
     *
     * @param junction Junction to be added.
     * @return Returns true if the junction is added or false if the road map
     * already contains it.
     */
    public boolean addJunction(Junction junction) {
        return this.roadMap.insertVertex(junction) != null;
    }

    /**
     * Create a new section that can be added to the road map.
     *
     * @return New section.
     */
    public Section newSection() {
        return new Section();
    }

    /**
     * Adds a section to the road map between to given junctions.
     *
     * @param jOrigName Junction of origin.
     * @param jDestName Junction of destination.
     * @param section Section to be added between the junctions.
     * @return Returns true if the junction is added or false if the road map
     * already contains it or one of the junctions is not found.
     */
    public boolean addSection(String jOrigName, String jDestName, Section section) {
        Junction jOrig = new Junction();
        Junction jDest = new Junction();
        jOrig.setName(jOrigName);
        jDest.setName(jDestName);
        section.setKey(getNumberEdges());
        return this.roadMap.insertEdge(jOrig, jDest, section, 0) != null;
    }

    /**
     * Returns all paths from one Junction to another.
     *
     * @param sourceNode Junction that will be the source of the path.
     * @param finishNode Junction that will be the end of the path.
     * @return paths ArrayList with all paths from one Junction to the other
     * one.
     */
    public ArrayList<LinkedList<Section>> allPaths(Junction sourceNode,
            Junction finishNode) {
        ArrayList<LinkedList<Section>> paths = new ArrayList<>();
        Vertex<Junction, Section> vOrig = this.roadMap.getVertex(sourceNode);
        Vertex<Junction, Section> vDest = this.roadMap.getVertex(finishNode);

        allPaths(vOrig, vDest, new boolean[this.roadMap.getNumEdges()],
                new LinkedList(), paths);

        return paths;
    }

    /**
     * Returns all paths from one Junction(vOrig) to other (vDest).
     *
     * @param vOrig Junction that will be the source of the path.
     * @param vDest Junction that will be the end of the path.
     * @param visited set of discovered activities.
     * @param path stack with sections of the current path (the path is in
     * reverse order).
     * @param paths ArrayList with all the paths (in correct order).
     */
    private void allPaths(Vertex<Junction, Section> vOrig,
            Vertex<Junction, Section> vDest, boolean[] visited,
            LinkedList<Section> path, ArrayList<LinkedList<Section>> paths) {
        for (Edge<Junction, Section> edge : vOrig.getOutgoing().values()) {
            if (!visited[edge.getElement().getKey()] && verifySection(path, edge)) {
                visited[edge.getElement().getKey()] = true;
                path.add(edge.getElement());

                if (edge.getVDest().equals(vDest)) {
                    paths.add(new LinkedList(path));
                    path.removeLast();
                } else {
                    allPaths(edge.getVDest(), vDest, visited, path, paths);
                }
            }

            if (visited[edge.getElement().getKey()] && !edge.getVDest().equals(vDest)) {
                path.removeLast();
            }
            visited[edge.getElement().getKey()] = false;
        }

    }

    /**
     * Verifies if the section we want to visit is worth visiting given a list
     * of sections.
     *
     * @param path List of sections we already visited.
     * @param edge Section we want to visit.
     * @return True if worth visiting or false if not.
     */
    public boolean verifySection(LinkedList<Section> path, Edge<Junction, Section> edge) {
        for (Section section : path) {
            if (edge.getVDest().equals(getCorrespondentEdge(section).getVOrig())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the correspondent edge to the given section if found, else
     * returns null.
     *
     * @param section Section to which we want the edge.
     * @return The correspondent edge to the given section.
     */
    public Edge<Junction, Section> getCorrespondentEdge(Section section) {
        for (Edge<Junction, Section> edge : this.roadMap.getEdges()) {
            if (edge.getElement().equals(section)) {
                return edge;
            }
        }

        return null;
    }

    /**
     * Returns the textual description of the object in html format.
     *
     * @return Textual description of the object.
     */
    public String toStringHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(this.name).append("<br/>\n");
        sb.append("Description: ").append(this.description).append("<br/>\n");
        sb.append(this.roadMap.toStringHTML());
        sb.append(this.vehicleList.toStringHTML());

        return sb.toString();
    }

    /**
     * Returns the junction of the specified name.
     *
     * @param junctionName (String) The target junction's name
     * @return ({@link Junction}) The target junction. Returns null the junction
     * does not exist.
     */
    public Junction getJunctionByName(String junctionName) {
        Junction j = new Junction();
        j.setName(junctionName);

        return roadMap.getVertex(j).getElement();
    }

    /**
     * Returns the vehicle of the specified name.
     *
     * @param vehicleName The vehicle's name.
     * @return The vehicle if. Retuns null if the vehicle doesn't exist.
     */
    public Vehicle getVehicleByName(String vehicleName) {
        Iterator<Vehicle> vehicles = this.vehicleList.iterator();
        while (vehicles.hasNext()) {
            Vehicle vehicle = vehicles.next();
            if (vehicle.getName().compareToIgnoreCase(vehicleName) == 0) {
                return vehicle;
            }
        }
        return null;
    }

    /**
     * Validates a project in order to determine if it possess all the required
     * attributes present.
     *
     * @param validateRoads
     * @param validateVehicles
     * @return True if the project is valid and false if not.
     */
    public boolean validate(boolean validateRoads, boolean validateVehicles) {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the project cannot "
                    + "be empty.");
        }

        if (this.description == null || this.description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of the project "
                    + "cannot be empty.");
        }

        if ( validateRoads && this.roadMap.getNumVertices() < 2) {
            throw new IllegalArgumentException("The project must have at least "
                    + "two intersections.");
        }

        if (validateRoads && this.roadMap.getNumEdges() == 0) {
            throw new IllegalArgumentException("The project must have at least "
                    + "one road.");
        }

        if (validateVehicles && this.vehicleList.size() == 0) {
            throw new IllegalArgumentException("The project must have at least "
                    + "one vehicle.");
        }

        return true;
    }

}
