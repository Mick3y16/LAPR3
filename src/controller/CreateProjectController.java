package controller;

import model.Importable;
import model.ImportableRegistry;
import model.Project;
import model.ProjectHandler;
import model.Simulator;

/**
 * Represents an instance of CreateProjectController.
 *
 * @author G11
 */
public class CreateProjectController {

    /**
     * Simulator of project.
     */
    private Simulator simulator;

    /**
     * Project Handler of project.
     */
    private ProjectHandler projectHandler;

    /**
     * Project created.
     */
    private Project project;

    /**
     * Creates an instance of CreateProjectController without parameters.
     *
     * @param simulator simulator
     */
    public CreateProjectController(Simulator simulator) {
        this.simulator = simulator;
    }

    /**
     * Creates a new project.
     */
    public void newProject() {
        this.projectHandler = this.simulator.getProjectHandler();
        this.project = this.projectHandler.newProject();
    }

    /**
     * Insert the name and description in the newly created project.
     *
     * @param name name of project
     * @param description description of project
     */
    public void setData(String name, String description) {
        this.project.setName(name);
        this.project.setDescription(description);

    }

    /**
     * Enter the path where the file is located with the data to import.
     * @param path path to the file location to import
     */
    public void setPath(String path) {
        ImportableRegistry ir = this.simulator.getImportableRegistry();
        Importable importable = ir.getImportableOfType(path);
        importable.importProject(this.project, path);
        this.projectHandler.addProject(this.project);
    }
}
