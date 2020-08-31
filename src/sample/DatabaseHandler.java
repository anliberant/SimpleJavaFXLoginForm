package sample;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConn;

    public Connection getDbConn() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConn = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConn;
    }

    public void signUpUser(User user) {
        String sql = "INSERT INTO " + Utils.USER_TABLE + " (" +
                Utils.USERS_FIRST_NAME + "," + Utils.USERS_LAST_NAME + "," +
                Utils.USERS_USERNAME + "," + Utils.USERS_PASSWORD + "," +
                Utils.USERS_LOCATION + "," + Utils.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConn().prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + Utils.USER_TABLE + " WHERE " +
                Utils.USERS_USERNAME + "=? AND " + Utils.USERS_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = getDbConn().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
