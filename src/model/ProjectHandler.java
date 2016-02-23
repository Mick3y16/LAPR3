package model;

import java.util.List;
import model.dao.DaoManager;
import model.dao.ProjectDAO;

/**
 * Represents an instance of ProjectHandler through [variables].
 *
 * @author G11
 */
public class ProjectHandler {

    /**
     * Creates an instance of ProjectHandler without parameters.
     *
     */
    public ProjectHandler() {
    }

    /**
     * Creates a new project
     *
     * @return project
     */
    public Project newProject() {
        return new Project();
    }

    /**
     * Adds a project in the database.
     *
     * @param p project to insert
     * @return true adding successfully and false if it does not insert
     */
    public boolean addProject(Project p) {
        DaoManager daoManager = DaoManager.getInstance();
        ProjectDAO projectDao = daoManager.getProjectDAO();
        return projectDao.insertProject(p);
    }

    /**
     * Registers the target {@link model.Project}'s changes.
     *
     * @param p ({@link model.Project}) The project that contains the
     * information to update.
     * @param oldP The project that contains all the original information.
     * @return (boolean) True if successfully registered.
     */
    public boolean registerChanges(Project p, Project oldP) {
        boolean result = p.validate(true, true);
        if (result) {
            result = DaoManager.getInstance().getProjectDAO().updateProject(p, oldP);
        }
        return result;
    }

    /**
     * Copies the project.
     *
     * @param project The project that contains the information to update.
     *
     * @return True if successfully registered.
     */
    public boolean registerCopy(Project project) {
        boolean result = project.validate(true, true);
        if (result) {
            result = DaoManager.getInstance().getProjectDAO().insertProject(project);
        }
        return result;
    }

    /**
     * Returns the list of existing projects in the database.
     *
     * @return list of existing projects
     */
    public List<String> getProjectList() {
        return DaoManager.getInstance().getProjectDAO().getProjectList();
    }

}
