package model;

/**
 * Interface implemented by all classes whose responsability is to import the
 * data of a project (roads and vehicles), from a file format.
 *
 * @author G11
 */
public interface Importable {

    /**
     * Imports the data contained in a file into a project, loading all the
     * roads and vehicles found.
     *
     * @param project Project that will contain the imported roads and vehicles.
     * @param filePath Path to the file which contains the data.
     * @return True if the import is successfull.
     */
    boolean importProject(Project project, String filePath);

    /**
     * Imports the data contained in a file into a project, loading all the
     * roads found.
     *
     * @param project ({@link Project}) Project that will contain the imported
     * roads.
     * @param filePath (String) Path to the file which contains the data.
     * @return True if the import is successfull.
     */
    boolean importRoads(Project project, String filePath);

    /**
     * Imports the data contained in a file into a project, loading all the
     * vehicles found.
     *
     * @param project ({@link Project}) Project that will contain the imported
     * vehicles.
     * @param filePath (String) Path to the file which contains the data.
     * @return True if the import is successfull.
     */
    boolean importVehicles(Project project, String filePath);

    /**
     * Imports the data contained in a file into a simulation, loading all the
     * traffic found.
     *
     * @param simulation ({@link Simulation}) Simulation that will contain the
     * imported traffic.
     * @param filePath (String) Path to the file which contains the data.
     * @return True if the import is successfull.
     */
    boolean importSimulation(Simulation simulation, String filePath);

}
