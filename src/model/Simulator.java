package model;

import java.util.List;
import model.dao.DaoManager;
import model.dao.ProjectDAO;
import model.dao.SimulationDAO;

/**
 *
 * @author G11
 */
public class Simulator {

    /**
     * Project handler of the Simulator.
     */
    private ProjectHandler projectHandler;

    /**
     * Simulation handler of the system.
     */
    private SimulationHandler simulationHandler;

    /**
     * Importable registry of the Simulator.
     */
    private ImportableRegistry importableRegistry;

    /**
     * Exportable registry of the Simulator.
     */
    private ExportableRegistry exportableRegistry;

    /**
     * Project in use.
     */
    private Project openProject;

    /**
     * Simulation in use.
     */
    private Simulation openSimulation;

    /**
     * Creates an instance of Simulator.
     */
    public Simulator() {
        this.importableRegistry = new ImportableRegistry();
        this.exportableRegistry = new ExportableRegistry();
        this.projectHandler = new ProjectHandler();
        this.simulationHandler = new SimulationHandler();
        this.openSimulation = null;
        this.openProject = null;
    }

    /**
     * Returns the Project Handler.
     *
     * @return projectHandler.
     */
    public ProjectHandler getProjectHandler() {
        return this.projectHandler;
    }

    /**
     * Returns the Simulation Handler.
     *
     * @return simulationHandler.
     */
    public SimulationHandler getSimulationHandler() {
        return this.simulationHandler;
    }

    /**
     * Returns the open simulation.
     *
     * @return open simulation
     */
    public Simulation getOpenSimulation() {
        return this.openSimulation;
    }

    /**
     * Returns the Importable Registry.
     *
     * @return Importable Registry.
     */
    public ImportableRegistry getImportableRegistry() {
        return this.importableRegistry;
    }

    /**
     * Returns the Exportable Registry.
     * 
     * @return Exportable Registry.
     */
    public ExportableRegistry getExportableRegistry() {
        return this.exportableRegistry;
    }

    /**
     * Returns the project that is currently opened in the simulator.
     *
     * @return ({@link Project}) The opened project.
     */
    public Project getOpenProject() {
        return this.openProject;
    }

    /**
     * Returns the name of open project.
     *
     * @return name name of open project
     */
    public String getNameOpenProject() {
        return openProject.getName();
    }

    /**
     * Returns a list with the names of all available simulations.
     *
     * @return (List&lt;String&gt;) The list of names of all available
     * simulations.
     */
    public List<String> getSimulationList() {
        return this.simulationHandler.getSimulationDAO().getSimulationList(this.openProject.getName());
    }

    /**
     * Modifies the open simulation of the simulator.
     *
     * @param simulation New simulation
     */
    public void setOpenSimulation(Simulation simulation) {
        this.openSimulation = simulation;
    }

    /**
     * Changes the project in use by name.
     *
     * @param name name of new project in use
     */
    public void setProjectByName(String name) {
        ProjectDAO projectDao = DaoManager.getInstance().getProjectDAO();
        setOpenProject(projectDao.getProject(name));
    }

    /**
     * Modifies the project open the simulator.
     *
     * @param project New project
     */
    public void setOpenProject(Project project) {

        if (project == null) {
            throw new IllegalArgumentException("The project cannot be null.");
        } else {
            this.openProject = project;
            this.openSimulation = null;
        }
    }

    /**
     * Changes the simulaiton in use by name.
     *
     * @param name name of new simulaiton in use
     */
    public void getSimulationByName(String name) {
        SimulationDAO simulationDao = DaoManager.getInstance().getSimulationDAO();
        setOpenSimulation(simulationDao.getSimulationByName(name, openProject));
    }

}
