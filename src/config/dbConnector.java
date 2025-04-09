package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class dbConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental";  // Change DB name if necessary
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection connect;

    // Constructor to establish the database connection
    public dbConnector() {
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (SQLException ex) {
            System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }

    // Provide a public method to get the connection
    public Connection getConnection() {
        return connect;
    }

    // Function to retrieve data
    public ResultSet getData(String sql) throws SQLException {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    } catch (SQLException e) {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        throw e; // rethrow the exception
    }
}

    // Function to insert data
    public boolean insertData(String sql) {
        try (PreparedStatement pst = connect.prepareStatement(sql)) {
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            return true;
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex);
            return false;
        }
    }

    // Function to update data
    public boolean updateData(String sql) {
        try (PreparedStatement pst = connect.prepareStatement(sql)) {
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                return true;
            } else {
                System.out.println("Data Update Failed!");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex);
            return false;
        }
    }
}