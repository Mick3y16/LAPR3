package controller;

import model.Importable;
import model.ImportableRegistry;
import model.Project;
import model.ProjectHandler;
import model.Simulator;

/**
 * Represents an instance of CopyProjectController.
 *
 * @author G11
 */
public class CopyProjectController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Project Handler of project.
     */
    private ProjectHandler projectHandler;

    /**
     * Project selected.
     */
    private Project project;

    /**
     * Creates an instance of CopyProjectController.
     *
     * @param simulator Simulator.
     */
    public CopyProjectController(Simulator simulator) {
        this.simulator = simulator;
        this.projectHandler = this.simulator.getProjectHandler();
        if (this.simulator.getOpenProject() == null) {
            throw new IllegalArgumentException("You must open a project before you can edit it!");
        }
        this.project = new Project(this.simulator.getOpenProject());
    }

    /**
     * Returns the project's name.
     *
     * @return The project's name.
     */
    public String getProjectName() {
        return this.project.getName();
    }

    /**
     * Returns the project's description.
     *
     * @return The project's description.
     */
    public String getProjectDescription() {
        return this.project.getDescription();
    }

    /**
     * Sets the project's name.
     *
     * @param name The new name of the project.
     */
    public void setProjectName(String name) {
        this.project.setName(name);
    }

    /**
     * Sets the project's description.
     *
     * @param description The new description of the project.
     */
    public void setProjectDescription(String description) {
        this.project.setDescription(description);
    }

//    /**
//     * Enter the path where the file is located with the data about vehicles to
//     * import.
//     *
//     * @param path path to the file location to import
//     */
//    public void setPathVehicles(String path) {
//        ImportableRegistry ir = this.simulator.getImportableRegistry();
//        Importable importable = ir.getImportableOfType(path);
//        importable.importVehicles(this.project, path);
//    }
//
//    /**
//     * Enter the path where the file is located with the data about roads to
//     * import.
//     *
//     * @param path path to the file location to import
//     */
//    public void setPathRoads(String path) {
//        ImportableRegistry ir = this.simulator.getImportableRegistry();
//        Importable importable = ir.getImportableOfType(path);
//        importable.importRoads(this.project, path);
//    }

    /**
     * Registers the copy of the project.
     *
     * @return Returns true if successfully changed.
     */
    public boolean registerCopy() {
        return projectHandler.registerCopy(this.project);
    }
}
