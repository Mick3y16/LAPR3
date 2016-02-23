package model;

/*
 * Represents an instance of Simulation.
 *
 * @author G11
 */
public class Simulation implements HTMLExportable {

    /**
     * Name of Simulation.
     */
    private String name;

    /**
     * Description of Simulation.
     */
    private String description;

    /**
     * Project in use.
     */
    private Project openProject;

    /**
     * Traffic of a simulation.
     */
    private SimulationTraffic simulationTraffic;
    
    /**
     * A list containing all of the simulation's runs.
     */
    private SimulationRunList runList;

    /**
     * Creates an instance of simulation with null parameters.
     *
     * @param openProject
     */
    public Simulation(Project openProject) {
        this.name = "";
        this.description = "";
        setOpenProject(openProject);
        this.simulationTraffic = new SimulationTraffic();
        this.runList=new SimulationRunList();
    }
    
    /**
     * Creates an instance of Simulation with the same characteristics as the
     * target Simulation, creating a clone.
     *
     * @param simulation The target Simulation to copy.
     */
    public Simulation(Simulation simulation) {
        this.name = simulation.getName();
        this.description = simulation.getDescription();
        this.openProject = simulation.getOpenProject();
        this.simulationTraffic= simulation.getSimulationTraffic();
        this.runList = new SimulationRunList();
    }

    /**
     * Returns the name of the simulation.
     *
     * @return Name of the simulation.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the simulation.
     *
     * @return Description of the simulation.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the project that is currently opened in the simulation.
     *
     * @return ({@link Project}) The opened project.
     */
    public Project getOpenProject() {
        return this.openProject;
    }

    /**
     * Returns the traffic of the simulation.
     *
     * @return Traffic of the simulation.
     */
    public SimulationTraffic getSimulationTraffic() {
        return simulationTraffic;
    }

    /**
     * Modifies the name of the simulation.
     *
     * @param name New name
     */
    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Modifies the description of the simulation.
     *
     * @param description New description
     */
    public void setDescription(String description) {
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description cannot be empty");
        }
        this.description = description;
    }

    /**
     * Modifies the project open the simulation.
     *
     * @param project New project
     */
    public final void setOpenProject(Project project) {

        if (project == null) {
            throw new IllegalArgumentException("The project cannot be null.");
        } else {
            this.openProject = project;
        }
    }

    /**
     * Validate the simulation.
     *
     * @return True if simulation is valid else returns false.
     */
    public boolean validatesSimulation() {
        if (this.openProject == null) {
            throw new IllegalArgumentException("The project cannot be null.");
        }
        
        if (this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty");
        }
        
        if (this.description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description cannot be empty");
        }

        
        return true;
    }
    
    /**
     * Gets the list of simulation runs of this simulation.
     * @return ({@link SimulationRun}) The list of runs of this simulation.
     */
    public SimulationRunList getRunList()
    {
        return runList;
    }

    /**
     * Returns the textual description of the object in the following format:
     * Name: name Description: description List of Traffic:
     *
     * @return Textual description of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(this.name).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append(this.simulationTraffic.toString());

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

        sb.append("Name: ").append(this.name).append("<br/>\n");
        sb.append("Description: ").append(this.description).append("<br/>\n");
        sb.append(this.simulationTraffic.toStringHTML());

        return sb.toString();
    }

}
