package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Represents an instance of DaoManager through projectDAO.
 *
 * @author G11
 */
public class DaoManager {

    /**
     * The username of the session in the oracle SQL database.
     */
    private String username;

    /**
     * The password of the session in the oracle SQL database.
     */
    private String password;

    /**
     * The host to connect to.
     */
    private String host;

    /**
     * Allows the manager to know if all connections should have DBMS_Output
     * enabled.
     */
    private boolean enableDBMSonAll;

    /**
     * The size of the DBMS_Output buffer.
     */
    private int bufferSize;

    /**
     * Instance projectDAO.
     */
    private ProjectDAO projectDAO;
    /**
     * Instance simulationDAO.
     */
    private SimulationDAO simulationDAO;

    /**
     * Single instance of the class DaoManager.
     */
    private static final DaoManager daoManager = new DaoManager();

    /**
     * Creates an instance of DaoManger without parameters.
     *
     */
    private DaoManager() {
        this.projectDAO = new ProjectDAO();
        this.simulationDAO = new SimulationDAO();
        enableDBMSonAll = false;
        bufferSize = 20000;
        initDatabaseConfig();
    }

    /**
     * Returns the unique instance od DaoManager.
     *
     * @return daoManager.
     */
    public static DaoManager getInstance() {
        return daoManager;
    }

    /**
     * Allows you to delete the information daoManager.
     */
    public void resetDaoManagerToTest() {
        this.projectDAO = new ProjectDAO();
    }

    /**
     * Checks if this {@link DaoManager} enables DBMS_Output on all connections
     * performed.
     * <p>
     * It is false by default.
     *
     * @return (boolean) True if DBMS_Output is enabled on all connections.
     */
    public boolean isEnableDBMSonAll() {
        return enableDBMSonAll;
    }

    /**
     * Sets whether this {@link DaoManager} should enable DBMS_Output on all
     * connections created.
     *
     * @param enableDBMSonAll (boolean) True if {@link DaoManager} should enable
     * DBMS_Output.
     */
    public void setEnableDBMSonAll(boolean enableDBMSonAll) {
        this.enableDBMSonAll = enableDBMSonAll;
    }

    /**
     * Returns a projectDao of DaoManager
     *
     * @return projectDao
     */
    public ProjectDAO getProjectDAO() {
        try {
            projectDAO.closeConnection();
            Connection connection;
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(host);
            connection = ds.getConnection(username, password);
            connection.setAutoCommit(false);
            if (isEnableDBMSonAll()) {
                enableDBMSOutput(connection, bufferSize);
            }
            projectDAO.setConnection(connection);
            return projectDAO;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage() + "(SQL State=" + e.getSQLState() + ")");
        }
    }

    /**
     * Returns a simulationDao of DaoManager
     *
     * @return simulationDao
     */
    public SimulationDAO getSimulationDAO() {
        try {
            this.simulationDAO.closeConnection();
            Connection connection;
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(host);
            connection = ds.getConnection(this.username, this.password);
            if (isEnableDBMSonAll()) {
                enableDBMSOutput(connection, bufferSize);
            }
            connection.setAutoCommit(false);
            this.simulationDAO.setConnection(connection);
            return this.simulationDAO;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage() + "(SQL State=" + e.getSQLState() + ")");
        }
    }

    /**
     * Enables DBMS_Output in the target Oracle SQL connection.
     *
     * @param connection (Connection) The connection where the DBMS_Output
     * should be enabled.
     * @param bufferSize (int) The buffer's size.
     * @throws SQLException
     */
    public void enableDBMSOutput(Connection connection, int bufferSize) throws SQLException {
        SQLDataBundle bundle = new SQLDataBundle();
        bundle.addData(new SQLData(bufferSize, Integer.class));
        callVoidProcedure(connection, "sys.dbms_output.enable(?)", bundle);
    }

    /**
     * Prints the contents of DBMS_Output to a string.
     *
     * @param connection (Connection) The DBMS_Output enabled connection.
     * @return (String) The dbms_output's content.
     * @throws java.sql.SQLException
     */
    protected String printDBMSOutput(Connection connection) throws SQLException {
        String result = "";
        CallableStatement stat = connection.prepareCall("{call sys.dbms_output.get_line(?,?)}");
        stat.registerOutParameter(1, java.sql.Types.VARCHAR);
        stat.registerOutParameter(2, java.sql.Types.NUMERIC);

        String cache;
        int status = 0;
        do {
            stat.execute();
            cache = stat.getString(1);
            result += ((cache == null) ? "" : cache) + "\n";

            status = stat.getInt(2);
        } while (status == 0);
        stat.close();
        return result;
    }

    /**
     * Calls a void procedure.
     * <p>
     * A void procedure is a procedure that has no out results.
     *
     * @param connection (Connection) The connection to the database.
     * @param command (String) The procedure's name and arguments to invoke. For
     * example, the following Oracle SQL procedure:
     * <p>
     * myprocedure(id Number,name String)
     * <p>
     * Would be called like:
     * <p>
     * myprocedure(?,?)
     * @param bundle ({@link SQLDataBundle}) The bundle of data to send in the
     * parameters.
     * @throws SQLException
     */
    protected void callVoidProcedure(Connection connection, String command, SQLDataBundle bundle) throws SQLException {
        CallableStatement stat = connection.prepareCall("{ call " + command + "}");

        for (int i = 0; i < bundle.size(); i++) {
            SQLData data = bundle.getData(i);
            Class<?> clazz = data.getClazz();
            if (clazz.equals(Integer.class)) {
                Integer val = Integer.class.cast(data.getValue());
                stat.setInt(i + 1, val);
            }
            else if (clazz.equals(Double.class)) {
                Double val = Double.class.cast(data.getValue());
                stat.setDouble(i + 1, val);
            }
            else if (clazz.equals(String.class)) {
                String val = String.class.cast(data.getValue());
                stat.setString(i + 1, val);
            }
        }

        stat.execute();
        stat.close();
    }

    /**
     * Calls a function where the output parameters' results are atomic.
     *
     * @param connection (Connection) The connection to the database.
     * @param outParameters (String) The output parameters of the function. If,
     * for example, the function were to return an integer and a string, the
     * following should be present: INTEGER STRING
     * @param command (String) The procedure's name and arguments to invoke. For
     * example, the following Oracle SQL procedure:
     * <p>
     * myprocedure(id Number,name String)
     * <p>
     * Would be called like:
     * <p>
     * myprocedure(?,?)
     * @param bundle ({@link SQLDataBundle}) The bundle of data to send in the
     * parameters.
     * @return ({@link SQLDataBundle}) A bundle of sql data that contains the
     * data received from the function.
     * @throws SQLException
     */
    public SQLDataBundle callFunction(Connection connection, String outParameters, String command, SQLDataBundle bundle) throws SQLException {
        SQLDataBundle result = new SQLDataBundle();

        boolean closeStatement = true;
        
        String qMarks = "";
        String[] cache = outParameters.trim().split(" ");
        for (int i = 0; i < cache.length; i++) {
            qMarks += "? ";
        }
        CallableStatement stat = connection.prepareCall("{" + qMarks + "= call " + command + "}");

        for (int i = 0; i < bundle.size(); i++) {
            SQLData data = bundle.getData(i);
            Class<?> clazz = data.getClazz();
            if (clazz.equals(Integer.class)) {
                Integer val = Integer.class.cast(data.getValue());
                stat.setInt(cache.length + i + 1, val);
            } else if (clazz.equals(String.class)) {
                String val = String.class.cast(data.getValue());
                stat.setString(cache.length + i + 1, val);
            }
        }
        for (int i = 0; i < cache.length; i++) {
            if (cache[i].equalsIgnoreCase("FLOAT")) {
                stat.registerOutParameter(i + 1, java.sql.Types.FLOAT);
            } else if (cache[i].equalsIgnoreCase("STRING")
                    || cache[i].equalsIgnoreCase("VARCHAR2")) {
                stat.registerOutParameter(i + 1, java.sql.Types.VARCHAR);
            } else if (cache[i].equalsIgnoreCase("INTEGER")
                    || cache[i].equalsIgnoreCase("INT")) {
                stat.registerOutParameter(i + 1, java.sql.Types.INTEGER);
            }
            else if (cache[i].equalsIgnoreCase("CURSOR"))
            {
                stat.registerOutParameter(i+1,OracleTypes.CURSOR,"SYS_REFCURSOR");
            }
        }

        stat.execute();

        
        SQLData temp;

        for (int i = 0; i < cache.length; i++) {
            temp = null;
            if (cache[i].equalsIgnoreCase("FLOAT")) {
                temp = new SQLData();
                temp.setValue(stat.getFloat(i + 1));
            } else if (cache[i].equalsIgnoreCase("STRING")
                    || cache[i].equalsIgnoreCase("VARCHAR2")) {
                temp = new SQLData();
                temp.setValue(stat.getString(i + 1));
            } else if (cache[i].equalsIgnoreCase("INTEGER")
                    || cache[i].equalsIgnoreCase("INT")) {
                temp = new SQLData();
                temp.setValue(stat.getInt(i + 1));
            }
            else if (cache[i].equalsIgnoreCase("CURSOR"))
            {
                temp = new SQLData();
                temp.setValue(stat.getObject(i+1));
                closeStatement=false;
            }
            if (temp != null) {
                result.addData(temp);
            }
        }
        
        if (!closeStatement)
        {
            result.addData(new SQLData(stat,CallableStatement.class));
        }
        if (closeStatement)
        {
            stat.close();
        }
        return result;
    }

    /**
     * Initializes the data required to connect to a database. This data is kept
     * on a config file called "connection.cfg".
     */
    private void initDatabaseConfig() {
        try {
            File file = new File("connection.cfg");
            if (!file.exists()) {
                BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
                buffer.write("host=jdbc:oracle:thin:@94.60.237.51:1521:xe");
                buffer.newLine();
                buffer.write("username=LAPR3");
                buffer.newLine();
                buffer.write("pass=ABC123");
                buffer.newLine();
                buffer.close();
            }
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String cache;
            String[] chunk;
            while ((cache = bf.readLine()) != null) {
                chunk = cache.split("=");
                if (chunk.length == 2) {
                    if (chunk[0].equals("host")) {
                        host = chunk[1];
                    } else if (chunk[0].equals("username")) {
                        username = chunk[1];
                    } else if (chunk[0].equals("pass")) {
                        password = chunk[1];
                    }
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
