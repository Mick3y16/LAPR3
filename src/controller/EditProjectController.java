/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Importable;
import model.Project;
import model.ProjectHandler;
import model.Simulator;

/**
 * Controls the edition of a project. Using this class, a user interface can
 * easilly allow the edition of a project.
 * @author G11
 */
public class EditProjectController {
    
    /**
     * The simulator.
     */
    private Simulator sim;
    
    /**
     * The project handler.
     */
    private ProjectHandler ph;
    
    /**
     * The clone of the opened project.
     */
    private Project clone;
    
    /**
     * Creates an instance of {@link EditProjectController} with the specified
     * parameters.
     * @param simulator ({@link model.Simulator}) The simulator to use.
     * @param projectHandler ({@link model.ProjectHandler}) The handler of projects.
     */
    public EditProjectController(Simulator simulator,ProjectHandler projectHandler)
    {
        sim=simulator;
        ph=projectHandler;
        if (sim.getOpenProject()==null)
        {
            throw new IllegalArgumentException("You must open a project before you can edit it!");
        }
        clone = new Project(sim.getOpenProject());
    }
    
    /**
     * Returns the project's name.
     * @return (String) The project's name.
     */
    public String getProjectName()
    {
        return clone.getName();
    }
    
    /**
     * Returns the project's description.
     * @return (String) The project's description.
     */
    public String getProjectDescription()
    {
        return clone.getDescription();
    }
    
    /**
     * Sets the project's name.
     * @param name (String) The new name of the project.
     */
    public void setProjectName(String name)
    {
        clone.setName(name);
    }
    
    /**
     * Sets the project's description.
     * @param desc (String) The new description of the project.
     */
    public void setProjectDescription(String desc)
    {
        clone.setDescription(desc);
    }
    
    /**
     * Imports the roads in the specified filepath.
     * @param filePath (String) The target filepath of the file.
     * @return (boolean) True if successfully imported.
     */
    public boolean importRoads(String filePath)
    {
        boolean result;
        Importable imp=sim.getImportableRegistry().getImportableOfType(filePath);
        result = imp.importRoads(clone,filePath);
        return result;
    }
    
    /**
     * Returns a list of the roads of the project.
     * @return (List&lt;String&gt;) The list of roads.
     */
    public List<String> getRoadsList()
    {
        return clone.getRoadNames();
    }
    
    /**
     * Returns a list of the vehicles available in the project.
     * @return (List&lt;String&gt;) The list of vehicles.
     */
    public List<String> getVehiclesList()
    {
        return clone.getVehicleNamesList();
    }
    
    /**
     * Imports the vehicles in the specified filepath.
     * @param filePath (String) The target filepath of the file.
     * @return (boolean) True if successfully imported.
     */
    public boolean importVehicles(String filePath)
    {
        boolean result;
        Importable imp=sim.getImportableRegistry().getImportableOfType(filePath);
        result = imp.importVehicles(clone,filePath);
        return result;
    }
    
    /**
     * Returns a list with all the extensions currently supported.
     * @return (List&lt;String&gt;) The list of import mechanisms.
     */
    public List<String> getListImportMechanisms()
    {
        return sim.getImportableRegistry().getListOfImportables();
    }
    
    /**
     * Registers the changes made to the project.
     * @return (boolean) Returns true if successfully changed.
     */
    public boolean registerChanges()
    {
        return ph.registerChanges(clone,sim.getOpenProject());
    }
}
