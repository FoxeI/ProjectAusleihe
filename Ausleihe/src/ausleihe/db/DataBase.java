package ausleihe.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    
    public DataBase(){

    }
    
    public void connect(String user, String password){   
        try {
            // Load Driver
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e1) {
            System.err.println("Driver failed to load!");
            e1.printStackTrace();
            return;
        }
            
        try {
            // Connect to Server
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost/ProjectAusleihe",user,password);
            System.out.println("Connected.");
        } catch (SQLException e2) {
            System.err.println("Connection failed ...");
            e2.printStackTrace();
            return;
        }
            
        try {
            // Create Statement
            statement = connection.createStatement();
        } catch (SQLException e3) {
            System.out.println("Statement creation failed!");
        }
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
}
