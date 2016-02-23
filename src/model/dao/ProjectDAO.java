package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.AcceleratorPedal;
import model.Junction;
import model.Project;
import model.Regime;
import model.Section;
import model.Segment;
import model.SegmentList;
import model.Throttle;
import model.Vehicle;
import model.VehicleCombustion;
import model.VehicleElectric;
import model.VehicleList;
import oracle.jdbc.OracleTypes;

/**
 * Represents an instance of ProjectDAO.
 *
 * @author G11
 */
public class ProjectDAO {

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
     * The inserts proposed in the database.
     *
     * @param project project to insert
     * @return
     */
    public boolean insertProject(Project project) {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(project.getName(), String.class));
        bundle.addData(new SQLData(project.getDescription(), String.class));
        try {
            DaoManager.getInstance().enableDBMSOutput(connection, 20000);
            DaoManager.getInstance().callVoidProcedure(connection, "insertProject2(?, ?)", bundle);

        } catch (SQLException ex) {
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }

        Iterator<Junction> it2 = project.getJunctions().iterator();
        while (it2.hasNext()) {
            Junction jun = it2.next();
            insertJunction(jun, project);

        }

        Iterator<Section> it3 = project.getSections().iterator();
        while (it3.hasNext()) {
            Section sec = it3.next();
            insertSection(sec, project);

        }

        Iterator<Vehicle> it5 = project.getVehicleList().iterator();
        while (it5.hasNext()) {
            Vehicle veh = it5.next();
            insertVehicle(veh, project);

        }
        try {
            this.connection.commit();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    /**
     * Updates the target {@link model.Project} on the database.
     *
     * @param project ({@link model.Project}) The project that contains the
     * information to update.
     * @param oldProject The project that contains all the original information.
     * @return (boolean) True if successfully updated.
     */
    public boolean updateProject(Project project, Project oldProject) {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(oldProject.getName(), String.class));
        bundle.addData(new SQLData(project.getName(), String.class));
        bundle.addData(new SQLData(project.getDescription(), String.class));
        try {
            DaoManager.getInstance().enableDBMSOutput(connection, 20000);
            DaoManager.getInstance().callVoidProcedure(connection, "updateProject2(?, ?, ?)", bundle);

        } catch (SQLException ex) {
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }

        for (Section sec : project.getSections()) {
            if (!oldProject.getRoadNames().contains(sec.getRoadName())) {
                insertSection(sec, project);
            }
        }

        for (String vehicleName : project.getVehicleNamesList()) {
            if (!oldProject.getVehicleNamesList().contains(vehicleName)) {
                Vehicle veh = project.getVehicleByName(vehicleName);
                insertVehicle(veh, project);
            }
        }

        try {
            this.connection.commit();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    /**
     * Returns the list of existing projects in the database.
     *
     * @return list of existing projects
     */
    public List<String> getProjectList() {

        List<String> projectList = new ArrayList<>();

        try {
            CallableStatement stmt = connection.prepareCall("{? = call getprojectlist()}");

            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            //SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getProjectList()", new SQLDataBundle());

            ResultSet rs = (ResultSet) stmt.getObject(1); //result.getData(0);

            while (rs.next()) {
                projectList.add(rs.getString("name"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(
                    "there are no projects to display");
        }

        return projectList;

    }

    /**
     * Returns the project whose name is received as parameter.
     *
     * @param name name project
     * @return project
     */
    public Project getProject(String name) {
        Project project = new Project();
        project.setName(name);
        getDescriptionProject(project);
        getJunctionsProject(project);
        getSectionsProject(project);
        getSegmentList(project);
        getVehicleList(project);
        return project;
    }

    /**
     * Adds the description to the project.
     *
     * @param project project
     */
    private void getDescriptionProject(Project project) {
        String name = project.getName();
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(name, String.class));
        try {
            DaoManager.getInstance().enableDBMSOutput(connection, 20000);
            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "STRING", "getdescriptionproject(?)", bundle);
            project.setDescription(String.class.cast(result.getData(0).getValue()));

        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "There are no project with the specified name");
        }
    }

    /**
     * Adds a list of junctions to the project.
     *
     * @param project project
     */
    private void getJunctionsProject(Project project) {
        String name = project.getName();
        try {
            SQLDataBundle bundle2 = new SQLDataBundle();
            bundle2.addData(new SQLData(name, String.class));
            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getjunctionlist(?)", bundle2);

            for (int i = 0; i < result.size() - 1; i++) {

                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    Junction j = new Junction();
                    j.setName(rs.getString("name"));
                    project.addJunction(j);
                }

            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "There are no junctions with the specified project's name");
        }
    }

    /**
     * Adds a list of sections to the project.
     *
     * @param project project
     */
    private void getSectionsProject(Project project) {
        String name = project.getName();
        try {
            SQLDataBundle bundle3 = new SQLDataBundle();
            bundle3.addData(new SQLData(name, String.class));
            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getsectionlist(?)", bundle3);

            for (int i = 0; i < result.size() - 1; i++) {

                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    Section sect = project.newSection();
                    String roadName = rs.getString("road_name");
                    sect.setRoadName(roadName);
                    sect.setToll(rs.getInt("toll"));

                    try {
                        SQLDataBundle bundle4 = new SQLDataBundle();
                        bundle4.addData(new SQLData(rs.getInt("road_typology_id"), Integer.class));
                        DaoManager.getInstance().enableDBMSOutput(connection, 20000);
                        SQLDataBundle result3 = DaoManager.getInstance().callFunction(connection, "STRING", "getroadtypology(?)", bundle4);
                        sect.setTypology(String.class.cast(result3.getData(0).getValue()));

                    } catch (SQLException ex) {
                        throw new IllegalArgumentException(
                                "There are no road_typology with the specified road_typology_id");
                    }
                    sect.setWindOrientation(rs.getDouble("wind_orientation"));
                    sect.setWindSpeed(rs.getDouble("wind_speed"));
                    String[] junctions = roadName.split("-");
                    project.addSection(junctions[1], junctions[2], sect);
                }

            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "There are no sections with the specified project's name ");
        }
    }

    /**
     * Adds a list of segments to each project's section.
     *
     * @param project project
     */
    private void getSegmentList(Project project) {
        String name = project.getName();
        for (Section sec : project.getSections()) {
            SegmentList segList = sec.getSegmentList();
            String section_roadName = sec.getRoadName();
            try {
                SQLDataBundle bundle = new SQLDataBundle();

                bundle.addData(new SQLData(section_roadName, String.class));
                bundle.addData(new SQLData(project.getName(), String.class));

                SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getsegmentlist(?,?)", bundle);

                for (int i = 0; i < result.size() - 1; i++) {
                    ResultSet rs = (ResultSet) result.getData(i).getValue();
                    while (rs.next()) {
                        Segment seg = segList.newSegment();
                        seg.setAngle(rs.getDouble("Angle"));
                        seg.setIndex(rs.getInt("S_index"));
                        seg.setInitialHeight(rs.getDouble("Initial_height"));
                        seg.setLength(rs.getDouble("S_length"));
                        seg.setMaximumNumberVehicles(rs.getInt("MAXIMUM_NUMBER_VEHICLES"));
                        seg.setMaximumVelocity(rs.getDouble("MAXIMUM_VELOCITY"));
                        seg.setMinimumVelocity(rs.getDouble("MINIMUM_VELOCITY"));
                        segList.addSegment(seg);
                    }
                }
                ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
            } catch (SQLException ex) {
                throw new IllegalArgumentException(
                        "There are no segments with the specified name");
            }
        }
    }

    /**
     * Adds a list of vehicles to each project's section.
     *
     * @param project project
     */
    private void getVehicleList(Project project) {
        String name = project.getName();
        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(name, String.class));

            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getvehiclelist(?)", bundle);
            VehicleList vehicleList = project.getVehicleList();
            for (int i = 0; i < result.size() - 1; i++) {
                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    Vehicle vehicle = null;
                    String name_vehicle = rs.getString("Name");
                    if (rs.getString("MOTORIZATION").equalsIgnoreCase("VEHICLECOMBUSTION")) {
                        vehicle = vehicleList.newVehicleCombustion();
                        vehicle.setName(name_vehicle);
                        getCombustionVehicle((VehicleCombustion) vehicle, project);

                    }
                    if (rs.getString("MOTORIZATION").equalsIgnoreCase("VEHICLEELECTRIC")) {
                        vehicle = vehicleList.newVehicleElectric();
                        vehicle.setName(name_vehicle);
                        getElectricVehicle((VehicleElectric) vehicle, project);

                    }
                    if (vehicle != null) {
                        vehicle.setDescription(rs.getString("Description"));
                        vehicle.setType(rs.getString("Type"));
                        vehicle.setFuel(rs.getString("Fuel"));
                        vehicle.setMass(rs.getDouble("Mass"));
                        vehicle.setLoad(rs.getDouble("Load"));
                        vehicle.setDragCoefficient(rs.getDouble("Drag_Coefficient"));
                        vehicle.setFrontalArea(rs.getDouble("Frontal_Area"));
                        vehicle.setRollingResistanceCoefficient(rs.getDouble("RRC"));
                        vehicle.setWheelSize(rs.getDouble("Wheel_Size"));

                        SQLDataBundle bundle2 = new SQLDataBundle();
                        bundle2.addData(new SQLData(vehicle.getName(), String.class));
                        bundle2.addData(new SQLData(project.getName(), String.class));
                        
                        SQLDataBundle result2 = DaoManager.getInstance().callFunction(connection, "CURSOR", "getvehiclevelocities(?, ?)", bundle2);
                        for (int j = 0; j < result2.size() - 1; j++) {
                            ResultSet rs2 = (ResultSet) result2.getData(i).getValue();
                            while (rs2.next()) {
                                vehicle.insertVelocityLimit(rs2.getString("TYPOLOGY"), rs2.getDouble("VELOCITY"));
                            }
                        }
                        ((CallableStatement)result2.getData(result2.size()-1).getValue()).close();
                        vehicleList.addVehicle(vehicle);
                    }
                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(
                    ex.getMessage());
        }
    }

    /**
     * Adds a information about vehicle combustion.
     *
     * @param VehicleCombustion Vehicle Combustion
     */
    private void getCombustionVehicle(VehicleCombustion vehicle, Project project) {

        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(project.getName(), String.class));
            bundle.addData(new SQLData(vehicle.getName(), String.class));

            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getvehiclecombustion(?, ?)", bundle);
            for (int i = 0; i < result.size() - 1; i++) {
                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    vehicle.setRpmMinimum(rs.getInt("RPM_LOW"));
                    vehicle.setRpmMaximum(rs.getInt("RPM_HIGH"));
                    vehicle.setFinalDrive(rs.getDouble("FINAL_DRIVE"));

                    getGearBox(vehicle, project);
                    getAcceleratorPedal(vehicle, project);

                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    /*"there is no information on the vehicle combustion"*/ex.getMessage());
        }
    }

    /**
     * Adds a information about vehicle electric.
     *
     * @param vehicle Vehicle Electric.
     */
    private void getElectricVehicle(VehicleElectric vehicle, Project project) {

        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(project.getName(), String.class));
            bundle.addData(new SQLData(vehicle.getName(), String.class));

            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getvehicleelectric(?, ?)", bundle);
            for (int i = 0; i < result.size() - 1; i++) {
                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {
                    vehicle.setRpmMinimum(rs.getInt("RPM_LOW"));
                    vehicle.setRpmMaximum(rs.getInt("RPM_HIGH"));
                    vehicle.setFinalDrive(rs.getDouble("FINAL_DRIVE"));

                    getGearBox(vehicle, project);
                    getAcceleratorPedal(vehicle, project);
                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    /*"there is no information on the vehicle combustion"*/ex.getMessage());
        }
    }

    /**
     * Adds the accelerator pedal of the vehicle.
     *
     * @param vehicle Vehicle.
     * @param project Project.
     * @throws java.sql.SQLException
     */
    public void getAcceleratorPedal(Vehicle vehicle, Project project) throws SQLException {
        AcceleratorPedal acceleratorPedal = vehicle.getAcceleratorPedal();

        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(project.getName(), String.class));
        bundle.addData(new SQLData(vehicle.getName(), String.class));

        SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "get_acc_pedal(?, ?)", bundle);

        for (int j = 0; j < result.size() - 1; j++) {
            ResultSet rs = (ResultSet) result.getData(j).getValue();

            while (rs.next()) {
                Throttle throttle = acceleratorPedal.newThrottle();

                int acceleratorPedalID = rs.getInt("ACC_PEDAL_ID");
                int acceleratorPedalPercentage = rs.getInt("ACCEL_PEDAL_PERCENTAGE");

                getThrottle(acceleratorPedalID, throttle);

                acceleratorPedal.insertThrottle(acceleratorPedalPercentage, throttle);
            }
        }
    }

    /**
     * Adds information throttle the vehicle.
     *
     * @param acceleratorPedalID ID of accelerator pedal.
     * @param throttle Throttle.
     * @throws java.sql.SQLException
     */
    public void getThrottle(int acceleratorPedalID, Throttle throttle) throws SQLException {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(acceleratorPedalID, Integer.class));
        SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getregimelist(?)", bundle);

        for (int i = 0; i < result.size() - 1; i++) {
            ResultSet rs = (ResultSet) result.getData(i).getValue();

            while (rs.next()) {
                Regime regime = throttle.newRegime();
                regime.setRpmLow(rs.getInt("RPM_LOW"));
                regime.setRpmHigh(rs.getInt("RPM_HIGH"));
                regime.setSfc(rs.getDouble("SFC"));
                regime.setTorque(rs.getInt("TORQUE"));
                throttle.insertRegime(regime);
            }
        }

        ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
    }

    /**
     * Adds information about the gear box.
     *
     * @param vehicle Vehicle.
     */
    private void getGearBox(Vehicle vehicle, Project project) {
        try {
            SQLDataBundle bundle = new SQLDataBundle();
            bundle.addData(new SQLData(project.getName(), String.class));
            bundle.addData(new SQLData(vehicle.getName(), String.class));

            SQLDataBundle result = DaoManager.getInstance().callFunction(connection, "CURSOR", "getgearbox(?, ?)", bundle);

            for (int i = 0; i < result.size() - 1; i++) {
                ResultSet rs = (ResultSet) result.getData(i).getValue();
                while (rs.next()) {

                    int gearNumber = rs.getInt("GEAR_NUMBER");
                    double ratio = rs.getDouble("RATIO");
                    if (vehicle instanceof VehicleCombustion) {
                        ((VehicleCombustion) vehicle).getGearbox().insertGearAndGearRatio(gearNumber, ratio);
                    } else if (vehicle instanceof VehicleElectric) {
                        ((VehicleElectric) vehicle).getGearbox().insertGearAndGearRatio(gearNumber, ratio);
                    }

                }
            }
            ((CallableStatement) result.getData(result.size() - 1).getValue()).close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "there is no information about gearbox vehicle");
        }
    }

    /**
     * Deletes a project.
     *
     * @param name The name of the project.
     */
    public boolean deleteProject(String name) {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(name, String.class));
        try {
            DaoManager.getInstance().enableDBMSOutput(connection, 20000);
            DaoManager.getInstance().callVoidProcedure(connection, "deleteproject(?)", bundle);

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

    private void insertJunction(Junction jun, Project project) {
        SQLDataBundle bundle2 = new SQLDataBundle();
        bundle2.addData(new SQLData(project.getName(), String.class));
        bundle2.addData(new SQLData(jun.getName(), String.class));
        try {
            DaoManager.getInstance().callVoidProcedure(connection, "insertJunction(?, ?)", bundle2);

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private void insertSection(Section sec, Project project) {
        SQLDataBundle bundle3 = new SQLDataBundle();
        bundle3.addData(new SQLData(project.getName(), String.class));
        bundle3.addData(new SQLData(sec.getRoadName(), String.class));
        bundle3.addData(new SQLData(sec.getTypology(), String.class));
        bundle3.addData(new SQLData(sec.getToll(), Double.class));
        bundle3.addData(new SQLData(sec.getWindSpeed(), Double.class));
        bundle3.addData(new SQLData(sec.getWindOrientation(), Double.class));
        try {
            DaoManager.getInstance().callVoidProcedure(connection, "insertSection(?, ?, ?, ?, ?, ?)", bundle3);

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }
        Iterator<Segment> it4 = sec.getSegmentList().iterator();
        while (it4.hasNext()) {
            Segment seg = it4.next();
            SQLDataBundle bundle4 = new SQLDataBundle();
            bundle4.addData(new SQLData(project.getName(), String.class));
            bundle4.addData(new SQLData(sec.getRoadName(), String.class));
            bundle4.addData(new SQLData(seg.getIndex(), Integer.class));
            bundle4.addData(new SQLData(seg.getInitialHeight(), Double.class));
            bundle4.addData(new SQLData(seg.getAngle(), Double.class));
            bundle4.addData(new SQLData(seg.getLength(), Double.class));
            bundle4.addData(new SQLData(seg.getMaximumVelocity(), Double.class));
            bundle4.addData(new SQLData(seg.getMinimumVelocity(), Double.class));
            bundle4.addData(new SQLData(seg.getMaximumNumberVehicles(), Integer.class));
            try {
                DaoManager.getInstance().callVoidProcedure(connection, "insertSegment(?, ?, ?, ?, ?, ?, ?, ?, ?)", bundle4);

            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    this.connection.rollback();
                } catch (SQLException ex1) {
                    throw new RuntimeException();
                }
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
    }

    private void insertVehicle(Vehicle veh, Project project) {
        SQLDataBundle bundle5 = new SQLDataBundle();
        bundle5.addData(new SQLData(project.getName(), String.class));
        bundle5.addData(new SQLData(veh.getName(), String.class));
        bundle5.addData(new SQLData(veh.getDescription(), String.class));
        bundle5.addData(new SQLData(veh.getType(), String.class));
        bundle5.addData(new SQLData(veh.getFuel(), String.class));
        bundle5.addData(new SQLData(veh.getMass(), Double.class));
        bundle5.addData(new SQLData(veh.getLoad(), Double.class));
        bundle5.addData(new SQLData(veh.getDragCoefficient(), Double.class));
        bundle5.addData(new SQLData(veh.getFrontalArea(), Double.class));
        bundle5.addData(new SQLData(veh.getRollingResistanceCoefficient(), Double.class));
        bundle5.addData(new SQLData(veh.getWheelSize(), Double.class));
        bundle5.addData(new SQLData(veh.getClass().getSimpleName(), String.class));
        try {
            DaoManager.getInstance().callVoidProcedure(connection, "insertVehicle(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", bundle5);

            for (Map.Entry<String, Double> entry : veh.getvelocityLimitPerRoad()) {
                SQLDataBundle bundle = new SQLDataBundle();
                bundle.addData(new SQLData(project.getName(), String.class));
                bundle.addData(new SQLData(veh.getName(), String.class));
                bundle.addData(new SQLData(entry.getKey(), String.class));
                bundle.addData(new SQLData(entry.getValue(), Double.class));

                DaoManager.getInstance().callVoidProcedure(connection, "insertVehicleVelocity(?, ?, ?, ?)", bundle);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }
        if (veh instanceof VehicleCombustion) {
            try {
                SQLDataBundle bundle9 = new SQLDataBundle();
                bundle9.addData(new SQLData(project.getName(), String.class));
                bundle9.addData(new SQLData(veh.getName(), String.class));
                bundle9.addData(new SQLData(((VehicleCombustion) veh).getRpmMinimum(), Integer.class));
                bundle9.addData(new SQLData(((VehicleCombustion) veh).getRpmMaximum(), Integer.class));
                bundle9.addData(new SQLData(((VehicleCombustion) veh).getFinalDrive(), Double.class));
                DaoManager.getInstance().callVoidProcedure(connection, "insertVehicleCombustion(?, ?, ?, ?, ?)", bundle9);

            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    this.connection.rollback();
                } catch (SQLException ex1) {
                    throw new RuntimeException();
                }
                throw new IllegalArgumentException(ex.getMessage());
            }
            Iterator<Map.Entry<Integer, Throttle>> it6 = ((VehicleCombustion) veh).getAcceleratorPedal().entrySet().iterator();
            while (it6.hasNext()) {
                Map.Entry<Integer, Throttle> entry = it6.next();
                SQLDataBundle bundle6 = new SQLDataBundle();
                bundle6.addData(new SQLData(project.getName(), String.class));
                bundle6.addData(new SQLData(veh.getName(), String.class));
                bundle6.addData(new SQLData(entry.getKey(), Integer.class));
                try {
                    DaoManager.getInstance().callVoidProcedure(connection, "insertACC_pedal_combustion(?, ?, ?)", bundle6);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    try {
                        this.connection.rollback();
                    } catch (SQLException ex1) {
                        throw new RuntimeException();
                    }
                    throw new IllegalArgumentException(ex.getMessage());
                }

                Iterator<Regime> it7 = entry.getValue().iterator();
                while (it7.hasNext()) {
                    Regime regime = it7.next();
                    SQLDataBundle bundle7 = new SQLDataBundle();
                    bundle7.addData(new SQLData(project.getName(), String.class));
                    bundle7.addData(new SQLData(veh.getName(), String.class));
                    bundle7.addData(new SQLData(entry.getKey(), Integer.class));
                    bundle7.addData(new SQLData(regime.getTorque(), Integer.class));
                    bundle7.addData(new SQLData(regime.getRpmLow(), Integer.class));
                    bundle7.addData(new SQLData(regime.getRpmHigh(), Integer.class));
                    bundle7.addData(new SQLData(regime.getSfc(), Double.class));
                    try {
                        DaoManager.getInstance().callVoidProcedure(connection, "insertRegime(?, ?, ?, ?, ?, ?, ?)", bundle7);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        try {
                            this.connection.rollback();
                        } catch (SQLException ex1) {
                            throw new RuntimeException();
                        }
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }

            }

            Iterator<Map.Entry<Integer, Double>> it8 = ((VehicleCombustion) veh).getGearbox().entrySet().iterator();
            while (it8.hasNext()) {
                Map.Entry<Integer, Double> entry = it8.next();
                SQLDataBundle bundle8 = new SQLDataBundle();
                bundle8.addData(new SQLData(project.getName(), String.class));
                bundle8.addData(new SQLData(veh.getName(), String.class));
                bundle8.addData(new SQLData(entry.getKey(), Integer.class));
                bundle8.addData(new SQLData(entry.getValue(), Double.class));
                try {
                    DaoManager.getInstance().callVoidProcedure(connection, "insertGearBox(?, ?, ?, ?)", bundle8);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    try {
                        this.connection.rollback();
                    } catch (SQLException ex1) {
                        throw new RuntimeException();
                    }
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        } else if (veh instanceof VehicleElectric) {
            try {
                SQLDataBundle bundle9 = new SQLDataBundle();
                bundle9.addData(new SQLData(project.getName(), String.class));
                bundle9.addData(new SQLData(veh.getName(), String.class));
                bundle9.addData(new SQLData(((VehicleElectric) veh).getRpmMinimum(), Integer.class));
                bundle9.addData(new SQLData(((VehicleElectric) veh).getRpmMaximum(), Integer.class));
                bundle9.addData(new SQLData(((VehicleElectric) veh).getEnergyRegenerationRatio(), Double.class));
                bundle9.addData(new SQLData(((VehicleElectric) veh).getFinalDrive(), Double.class));
                DaoManager.getInstance().callVoidProcedure(connection, "insertVehicleElectric(?, ?, ?, ?, ?, ?)", bundle9);

            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    this.connection.rollback();
                } catch (SQLException ex1) {
                    throw new RuntimeException();
                }
                throw new IllegalArgumentException(ex.getMessage());
            }
            Iterator<Map.Entry<Integer, Throttle>> it6 = ((VehicleElectric) veh).getAcceleratorPedal().entrySet().iterator();
            while (it6.hasNext()) {
                Map.Entry<Integer, Throttle> entry = it6.next();
                SQLDataBundle bundle6 = new SQLDataBundle();
                bundle6.addData(new SQLData(project.getName(), String.class));
                bundle6.addData(new SQLData(veh.getName(), String.class));
                bundle6.addData(new SQLData(entry.getKey(), Integer.class));
                try {
                    DaoManager.getInstance().callVoidProcedure(connection, "insertACC_pedal_combustion(?, ?, ?)", bundle6);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    try {
                        this.connection.rollback();
                    } catch (SQLException ex1) {
                        throw new RuntimeException();
                    }
                    throw new IllegalArgumentException(ex.getMessage());
                }

                Iterator<Regime> it7 = entry.getValue().iterator();
                while (it7.hasNext()) {
                    Regime regime = it7.next();
                    SQLDataBundle bundle7 = new SQLDataBundle();
                    bundle7.addData(new SQLData(project.getName(), String.class));
                    bundle7.addData(new SQLData(veh.getName(), String.class));
                    bundle7.addData(new SQLData(entry.getKey(), Integer.class));
                    bundle7.addData(new SQLData(regime.getTorque(), Integer.class));
                    bundle7.addData(new SQLData(regime.getRpmLow(), Integer.class));
                    bundle7.addData(new SQLData(regime.getRpmHigh(), Integer.class));
                    bundle7.addData(new SQLData(regime.getSfc(), Double.class));
                    try {
                        DaoManager.getInstance().callVoidProcedure(connection, "insertRegime(?, ?, ?, ?, ?, ?, ?)", bundle7);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        try {
                            this.connection.rollback();
                        } catch (SQLException ex1) {
                            throw new RuntimeException();
                        }
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }

            }

            Iterator<Map.Entry<Integer, Double>> it8 = ((VehicleElectric) veh).getGearbox().entrySet().iterator();
            while (it8.hasNext()) {
                Map.Entry<Integer, Double> entry = it8.next();
                SQLDataBundle bundle8 = new SQLDataBundle();
                bundle8.addData(new SQLData(project.getName(), String.class));
                bundle8.addData(new SQLData(veh.getName(), String.class));
                bundle8.addData(new SQLData(entry.getKey(), Integer.class));
                bundle8.addData(new SQLData(entry.getValue(), Double.class));
                try {
                    DaoManager.getInstance().callVoidProcedure(connection, "insertGearBox(?, ?, ?, ?)", bundle8);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    try {
                        this.connection.rollback();
                    } catch (SQLException ex1) {
                        throw new RuntimeException();
                    }
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }

        }
    }

    /**
     * Deletes a simulation run.
     *
     * @param name The name of the simualtion run.
     */
    public boolean deleteSimulationRun(String name) {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(name, String.class));
        try {
            DaoManager.getInstance().callVoidProcedure(connection, "deletesimulationrun(?)", bundle);

            this.connection.commit();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            try {
                this.connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                throw new RuntimeException();
            }
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

}
