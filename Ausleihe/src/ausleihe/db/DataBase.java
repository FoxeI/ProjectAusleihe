package ausleihe.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ausleihe.controller.Controller;

public class DataBase {
    private Controller controller;
    
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    
    public DataBase(Controller controller){
        this.controller = controller;
    }
    
    public boolean connect(String user, String password){   
        try {
            // Load Driver
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e1) {
            System.err.println("Driver failed to load!");
            e1.printStackTrace();
            return false;
        }
            
        try {
            // Connect to Server
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost/ProjectAusleihe",user,password);
            System.out.println("Connected.");
        } catch (SQLException e2) {
            System.err.println("Connection failed ...");
            e2.printStackTrace();
            return false;
        }
            
        try {
            // Create Statement
            statement = connection.createStatement();
        } catch (SQLException e3) {
            System.out.println("Statement creation failed!");
            return false;
        }
        return true;
    }
    
    public void disconnect(){
        try {
            if (statement != null)
                statement.close();
            
            if (connection != null)
                connection.close();
            
            System.out.println("Disconnected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String query) throws SQLException{
        return statement.executeQuery(query);
    }
    
}
