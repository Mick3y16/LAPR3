package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Project;
import model.SegmentRunResult;
import model.Simulation;
import model.SimulationRun;
import model.SimulationTraffic;
import model.Traffic;
import model.Vehicle;
import model.VehicleRun;
import oracle.jdbc.OracleTypes;

/**
 * Represents an instance of SimulationDAO.
 *
 * @author G11
 */
public class SimulationDAO {

    /**
     * An oracle SQL database connection.
     */
    private Connection connection;

    /**
     * Sets the oracle SQL connection of this {@link ProjectDAO}.
     *
     * @param connection (Connection) The new connection to the database.
     */
    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Checks whether the connection to the database is closed or not.
     *
     * @return (boolean) True if connection is still alive.
     * @throws SQLException
     */
    public boolean isConnectionAlive() throws SQLException {
        boolean result = connection != null;
        if (result) {
            result = !connection.isClosed();
        }
        return result;
    }

    /**
     * Closes the connection to the database of this {@link ProjectDAO}.
     */
    protected void closeConnection() throws SQLException {
        if (connection != null) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * Returns the list of existing simulations.
     *
     * @param name_project
     * @return [list of simulations].
     */
    public List<String> getSimulationList(String name_project) {
        List<String> simulationList = new ArrayList<>();

        try {
            CallableStatement stmt = connection.prepareCall("{? = call getsimulationlist (?)}");
            stmt.setString(2, name_project);
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                simulationList.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "there are no simulations to display");
        }

        return simulationList;

    }

    /**
     * Delete the simulation received by parameter
     *
     * @param simulation simulation to delete
     * @return true if the simulation is successfully deleted and false if not
     */
    public boolean deleteSimulation(String simulation) {
//verificar se funciona
        try {
            //CallableStatement stmt = connection.prepareCall("{? = call deletesimulation (?)}");
            //  stmt.setString(1, simulation.getName());
            //stmt.registerOutParameter(1, OracleTypes.NUMBER);
            //stmt.execute();
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(simulation, String.class));
            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "INTEGER", "deletesimulation(?)", bundle);

            if (Integer.class.cast(result.getData(0).getValue()) == 0) {
                return false;
            }
            connection.commit();
            connection.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(
                    "There are no simulations with the specified name");
        }
    }

    /**
     * Register the simulation received by parameter
     *
     * @param simulation simulation to register
     * @return true if the simulation is successfully register and false if not
     */
    public boolean insertSimulation(Simulation simulation) {

        try {

            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(simulation.getOpenProject().getName(), String.class));
            bundle.addData(new SQLData(simulation.getName(), String.class));
            bundle.addData(new SQLData(simulation.getDescription(), String.class));

            DaoManager.getInstance().enableDBMSOutput(connection, 200000);
            DaoManager.getInstance().callVoidProcedure(connection, "insertSimulation(?,?,?)", bundle);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        try {

            Iterator<Traffic> it = simulation.getSimulationTraffic().iterator();

            while (it.hasNext()) {
                Traffic traffic = traffic = it.next();
                SQLDataBundle bundle2 = new SQLDataBundle();

                bundle2.addData(new SQLData(simulation.getName(), String.class));
                bundle2.addData(new SQLData(traffic.getVehicle().getName(), String.class));
                bundle2.addData(new SQLData(traffic.getArrivalRate(), Integer.class));
                bundle2.addData(new SQLData(traffic.getBeginNode().getName(), String.class));
                bundle2.addData(new SQLData(traffic.getEndNode().getName(), String.class));

                DaoManager.getInstance().callVoidProcedure(connection, "insertTraffic(?,?,?,?,?)", bundle2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());

        }

        try {
            this.connection.commit();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }

    }

//    /**
//     *
//     * @param name
//     * @param project
//     * @return
//     */
//    public Simulation getSimulationByName(String name, Project project) {
//        try {
//            SQLDataBundle bundle = new SQLDataBundle();
//            bundle.addData(new SQLData(name, String.class));
//
//            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationByName(?)", bundle);
//            ResultSet rs = (ResultSet) result.getData(0).getValue();
//            String v_name = "", desc = "";
//
//            if (rs.next()) {
//                v_name = rs.getString(1);
//                desc = rs.getString(2);
//            }
//
//            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
//
//            bundle = new SQLDataBundle();
//
//            bundle.addData(
//                    new SQLData(name, String.class
//                    ));
//            result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getsimulationtraffic(?)", bundle);
//
//            Simulation sim = new Simulation(project);
//            SimulationTraffic tf = sim.getSimulationTraffic();
//            sim.setName(name);
//            sim.setDescription(desc);
//
//            for (int i = 0; i < result.size() - 1; i++) {
//                rs = (ResultSet) result.getData(i).getValue();
//                while (rs.next()) {
//
//                    Traffic t = tf.newTraffic();
//                    t.setBeginNode(project.getJunctionByName(rs.getString("begin_node")));
//                    t.setEndNode(project.getJunctionByName(rs.getString("end_node")));
//                    t.setArrivalRate(rs.getInt("arrival_rate"));
//                    t.setVehicle(project.getVehicleByName(rs.getString("v_name")));
//                    tf.addTraffic(t);
//                }
//            }
//            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
//
//            /*while (rs.next()) {
//             Junction j = new Junction();
//             j.setName(rs.getString("name"));
//             project.addJunction(j);
//             }
//
//             }
//             ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
//             */
//            return sim;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException(e.getMessage());
//        }
//    }
    /**
     * Gets a simulation by the name.
     *
     * @param name Name of the simulation.
     * @param project Project with the simulation.
     * @return
     */
    public Simulation getSimulationByName(String name, Project project) {
        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(name, String.class));

            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationByName(?)", bundle);
            ResultSet rs = (ResultSet) result.getData(0).getValue();
            String v_name = "", desc = "";

            if (rs.next()) {
                v_name = rs.getString(1);
                desc = rs.getString(2);
            }

            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();

            bundle = new SQLDataBundle();

            bundle.addData(
                    new SQLData(name, String.class
                    ));
            result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getsimulationtraffic(?)", bundle);

            Simulation sim = new Simulation(project);
            SimulationTraffic tf = sim.getSimulationTraffic();
            sim.setName(v_name);
            sim.setDescription(desc);

            for (int i = 0; i < result.size() - 1; i++) {
                rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {

                    Traffic t = tf.newTraffic();
                    t.setBeginNode(project.getJunctionByName(rs.getString("begin_node")));
                    t.setEndNode(project.getJunctionByName(rs.getString("end_node")));
                    t.setArrivalRate(rs.getInt("arrival_rate"));
                    t.setVehicle(project.getVehicleByName(rs.getString("v_name")));
                    tf.addTraffic(t);
                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();

            result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationRunList(?)", bundle);
            for (int i = 0; i < result.size() - 1; i++) {
                rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    SQLDataBundle bundle2 = new SQLDataBundle();
                    bundle2.addData(new SQLData(rs.getString("name"), String.class));
                    SQLDataBundle result2 = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationRunByName(?)", bundle2);
                    ResultSet rs2 = (ResultSet) result2.getData(i).getValue();
                    while (rs2.next()) {
                        SimulationRun sR = new SimulationRun();
                        ArrayList<VehicleRun> completed = new ArrayList<>();
                        ArrayList<VehicleRun> aborted = new ArrayList<>();
                        getSimulationRunVehicle(sim, rs2.getString("name"), completed, aborted);
                        sR.loadFromDatabase(sim, rs2.getString("name"),
                                rs2.getInt("start_time"), rs2.getInt("end_time"),
                                rs2.getInt("time_step"), rs2.getInt("algorithm_id"),
                                completed, aborted);
                        sim.getRunList().addRun(sR);
                    }
                    ((CallableStatement) result2.getData(result.size() - 1).getValue()).close();
                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();

            return sim;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Gets the simulation run vehicles.
     *
     * @param sim Simulation.
     * @param sRName Simulation Run.
     */
    private void getSimulationRunVehicle(Simulation sim, String sRName, ArrayList<VehicleRun> completed, ArrayList<VehicleRun> aborted) {
        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(sim.getName(), String.class));
            bundle.addData(new SQLData(sRName, String.class));
            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationRunVehicle(?, ?)", bundle);
            for (int i = 0; i < result.size() - 1; i++) {
                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    VehicleRun vehicleRun = VehicleRun.loadFromDatabase(sim.getOpenProject().
                            getVehicleByName(rs.getString("v_name")),
                            sim.getOpenProject().getJunctionByName(rs.getString("begin_node")),
                            sim.getOpenProject().getJunctionByName(rs.getString("end_node")), rs.getInt("instant_dropped_out"));
                            
                    SQLDataBundle bundle1 = new SQLDataBundle();
                    bundle1.addData(new SQLData(sim.getName(), String.class));
                    bundle1.addData(new SQLData(sRName, String.class));
                    bundle1.addData(new SQLData(vehicleRun.getUniqueID(), Integer.class));

                    SQLDataBundle result2 = DaoManager.getInstance().callFunction(connection, "CURSOR", "getSimulationRunVehicleResults(?, ?, ?)", bundle1);
                    for (int j = 0; j < result2.size() - 1; j++) {
                        ResultSet rs2 = (ResultSet) result2.getData(j).getValue();
                        while (rs2.next()) {
                            SegmentRunResult srr = new SegmentRunResult();
                            srr.setEnergySpend(rs2.getDouble("ENERGY"));
                            srr.setInstantIn(rs2.getInt("INSTANT_IN"));
                            srr.setInstantOut(rs2.getInt("INSTANT_OUT"));
                            vehicleRun.getResultsInSegment().add(srr);
                        }
                    }
                    ((CallableStatement) result2.getData(result.size() - 1).getValue()).close();

                    if (rs.getInt("instant_dropped_out") == -1) {
                        aborted.add(vehicleRun);
                    } else {
                        completed.add(vehicleRun);
                    }
                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    /**
     * Updates the target {@link model.Simulation} on the database.
     *
     * @param simulation ({@link model.Simulation}) The simulation that contains
     * the information to update.
     *
     * @param originName (String) The original name of the simulation.
     * @return (boolean) True if successfully updated.
     */
    public boolean updateSimulation(Simulation simulation, String originName) {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(originName, String.class));
        bundle.addData(new SQLData(simulation.getName(), String.class));
        bundle.addData(new SQLData(simulation.getDescription(), String.class));

        try {
            DaoManager.getInstance().enableDBMSOutput(connection, 20000);
            DaoManager.getInstance().callVoidProcedure(connection, "updateSimulation(?, ?, ?)", bundle);

        } catch (SQLException ex) {
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }

        try {
            this.connection.commit();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public void insertSimulationRun(SimulationRun simulationRun) {

        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(simulationRun.getSimulation().getName(), String.class));
            bundle.addData(new SQLData(simulationRun.getName(), String.class));
            bundle.addData(new SQLData(simulationRun.getStartTime(), Integer.class));
            bundle.addData(new SQLData(simulationRun.getFinishTime(), Integer.class));
            bundle.addData(new SQLData(simulationRun.getStepTime(), Integer.class));
            bundle.addData(new SQLData(simulationRun.getMethodType().toUpperCase(), String.class));

            DaoManager.getInstance().callVoidProcedure(connection, "insertsimulationrundata(?, ?, ?, ?, ?, ?)", bundle);

            for (VehicleRun v : simulationRun.getListAbortedVehicleRuns()) {
                SQLDataBundle bundle1 = new SQLDataBundle();
                bundle1.addData(new SQLData(v.getBeginNode().getName(), String.class));
                bundle1.addData(new SQLData(v.getEndNode().getName(), String.class));
                bundle1.addData(new SQLData(simulationRun.getSimulation().getName(), String.class));
                bundle1.addData(new SQLData(v.getVehicleName(), String.class));
                bundle1.addData(new SQLData(simulationRun.getName(), String.class));
                bundle1.addData(new SQLData(-1, Integer.class));
                bundle1.addData(new SQLData(v.getUniqueID(), Integer.class));

                DaoManager.getInstance().callVoidProcedure(connection, "insertsimulationrunvehicle(?, ?, ?, ?, ?, ?, ?)", bundle1);

            }

            for (VehicleRun v : simulationRun.getListCompletedVehicleRuns()) {
                SQLDataBundle bundle1 = new SQLDataBundle();
                bundle1.addData(new SQLData(v.getBeginNode().getName(), String.class));
                bundle1.addData(new SQLData(v.getEndNode().getName(), String.class));
                bundle1.addData(new SQLData(simulationRun.getSimulation().getName(), String.class));
                bundle1.addData(new SQLData(v.getVehicleName(), String.class));
                bundle1.addData(new SQLData(simulationRun.getName(), String.class));
                bundle1.addData(new SQLData(v.getDropoutTime(), Integer.class));
                bundle1.addData(new SQLData(v.getUniqueID(), Integer.class));

                DaoManager.getInstance().callVoidProcedure(connection, "insertsimulationrunvehicle(?, ?, ?, ?, ?, ?, ?)", bundle1);

                for (SegmentRunResult s : v.getResultsInSegment()) {
                    SQLDataBundle bundle2 = new SQLDataBundle();
                    bundle2.addData(new SQLData(simulationRun.getSimulation().getOpenProject().getName(), String.class));
                    bundle2.addData(new SQLData(v.getVehicleName(), String.class));
                    bundle2.addData(new SQLData(v.getBeginNode().getName(), String.class));
                    bundle2.addData(new SQLData(v.getEndNode().getName(), String.class));
                    bundle2.addData(new SQLData(simulationRun.getSimulation().getName(), String.class));
                    bundle2.addData(new SQLData(simulationRun.getName(), String.class));
                    bundle2.addData(new SQLData(s.getInstantIn(), Integer.class));
                    bundle2.addData(new SQLData(s.getInstantOut(), Integer.class));
                    bundle2.addData(new SQLData((int) s.getEnergySpend(), Integer.class));

                    int indexOfType = (s.getName()).lastIndexOf("-");
                    String index = (s.getName()).substring(indexOfType + 1);
                    String roadName = (s.getName()).substring(0, indexOfType);

                    bundle2.addData(new SQLData(roadName, String.class));
                    bundle2.addData(new SQLData(Integer.parseInt(index), Integer.class));
                    bundle2.addData(new SQLData(v.getUniqueID(), Integer.class));
                    System.out.println(v.getUniqueID());

                    DaoManager.getInstance().callVoidProcedure(connection, "insertsimrunvehicleresults(?,?,?,?,?,?,?,?,?,?,?,?)", bundle2);
                }
            }
            connection.commit();
        } catch (SQLException ex1) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
            ex1.printStackTrace();
            throw new IllegalArgumentException(ex1.getMessage());
        }

    }

}
