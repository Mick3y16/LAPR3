package controller;

import java.util.List;
import model.Importable;
import model.ImportableRegistry;
import model.Project;
import model.Simulation;
import model.SimulationHandler;
import model.Simulator;

/**
 *
 * @author G11
 */
public class CreateSimulationController {

    /**
     * Simulator.
     */
    private Simulator simulator;

    /**
     * Simulation Handler.
     */
    private SimulationHandler simulationHandler;

    /**
     * Open project.
     */
    private Project openProject;

    /**
     * Simulation.
     */
    private Simulation simulation;

    /**
     * Creates an instance of CreateSimulationController.
     *
     * @param simulator Simulator.
     */
    public CreateSimulationController(Simulator simulator) {
        this.simulator = simulator;
        if (this.simulator.getOpenProject()==null)
        {
            throw new IllegalArgumentException("A project must be opened first!");
        }
    }

    /**
     * Creates a new simulation.
     */
    public void createSimulation() {
        this.simulationHandler = this.simulator.getSimulationHandler();
        this.openProject = this.simulator.getOpenProject();
        this.simulation = this.simulationHandler.newSimulation(this.openProject);
        //this.simulator.setOpenSimulation(this.simulation);
    }

    /**
     * Returns a list with all the extensions currently supported.
     *
     * @return (List&lt;String&gt;) The list of import mechanisms.
     */
    public List<String> getListImportMechanisms() {
        return simulator.getImportableRegistry().getListOfImportables();
    }

    /**
     * Loads the simulation contained in the file with the specified path.
     *
     * @param path (String) The path to the target file that contains the
     * simulation data to load.
     */
    public void loadSimulation(String path) {
        ImportableRegistry ir = this.simulator.getImportableRegistry();
        Importable importable = ir.getImportableOfType(path);
        importable.importSimulation(this.simulation, path);
        this.simulationHandler.addSimulation(this.simulation);
    }
}
