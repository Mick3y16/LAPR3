package model;

import java.util.List;
import model.dao.DaoManager;
import model.dao.SimulationDAO;

/**
 * Represents an instance of SimulationHandler.
 *
 * @author G11
 */
public class SimulationHandler {

    /**
     * Creates an instance of SimulationHandler without parameters.
     *
     */
    public SimulationHandler() {
    }

    /**
     * Returns the list of existing simulations in the database.
     *
     * @return list of existing simulations
     */
    public List<String> getSimulationList(Project openProject) {
        String name_project = openProject.getName();
        return DaoManager.getInstance().getSimulationDAO().getSimulationList(name_project);
    }

    /**
     * Deletes the open simulation
     *
     * @return true to delete the successful simulation and false if not
     */
    public boolean deleteSimulation(String name) {
        return DaoManager.getInstance().getSimulationDAO().deleteSimulation(name);
    }

    /**
     * Creates a new simulation.
     *
     * @param openProject Open project.
     * @return Simulation created.
     */
    public Simulation newSimulation(Project openProject) {
        return new Simulation(openProject);
    }

    /**
     * Returns a simulationDao.
     *
     * @return simulationDao.
     */
    public SimulationDAO getSimulationDAO() {
        return DaoManager.getInstance().getSimulationDAO();
    }

    /**
     * Adds a simulation in the database.
     *
     * @param simulation Simulation to insert
     * @return True adding successfully and false if it does not insert
     */
    public boolean addSimulation(Simulation simulation) {
        DaoManager daoManager = DaoManager.getInstance();
        SimulationDAO simulationDAO = daoManager.getSimulationDAO();
        return simulationDAO.insertSimulation(simulation);
    }

    /**
     * Registers the target {@link model.Simulation}'s changes.
     *
     * @param simulation ({@link model.Simulation}) The simulaiton that contains
     * the information to update.
     * @param originName (String) The original name of the simulation.
     * @return (boolean) True if successfully registered.
     */
    public boolean registerChanges(Simulation simulation, String originName) {
        boolean result = simulation.validatesSimulation();
        if (result) {
            result = DaoManager.getInstance().getSimulationDAO().updateSimulation(simulation, originName);
        }

        return result;
    }

    /**
     * Copies a simulation.
     *
     * @param simulation Simulation to be copied.
     * @return True if syccessfully copied.
     */
    public boolean registerCopy(Simulation simulation) {
        boolean result = simulation.validatesSimulation();
        if (result) {
            result = DaoManager.getInstance().getSimulationDAO().insertSimulation(simulation);
        }

        return result;
    }
}
