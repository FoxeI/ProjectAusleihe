package ausleihe.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ausleihe.controller.Controller;

public class DataBase {
    private Controller controller;
    
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    private ArrayList<String> readList = new ArrayList<String>();
    private ArrayList<String> writeList = new ArrayList<String>();
    private ArrayList<String> deleteList = new ArrayList<String>();
    
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
        
        // Testing call of the permission 
        controller.accessRead(controller.getLoggedUser());
        getAccessInf();
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
    
    public boolean execute(String string){
        System.out.println(string);
        try {
            return statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public ArrayList<String> getReadAccess()  {
		DatabaseMetaData dbmd;
		
		try {
			dbmd = connection.getMetaData();
         ResultSet rs = dbmd.getTablePrivileges(connection.getCatalog(), "Leser", "Computer");
      
         while (rs.next()) {
             String catalog = rs.getString("TABLE_CAT");
             String schema = rs.getString("TABLE_SCHEM");
             String tableName = rs.getString("TABLE_NAME");
             String privilege = rs.getString("PRIVILEGE");
             String grantor = rs.getString("GRANTOR");
             String grantee = rs.getString("GRANTEE");
             String isGrantable = rs.getString("IS_GRANTABLE");

             System.out.println("table name:" + tableName);
             System.out.println("catalog:"+catalog);
             System.out.println("schema:"+ schema);
             System.out.println("privilege:"+privilege);
             System.out.println("grantor:"+grantor);
             System.out.println("isGrantable:"+isGrantable);
             System.out.println("grantee:"+grantee);
             System.out.println("Ich war mal hier :D");
           }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler");
			e.printStackTrace();
		}
		System.out.println("Ich war mal hier :D");

    	return null;
    }
    

    
    public ResultSet executeQuery(String query) throws SQLException{
        System.out.println(query);
        result = statement.executeQuery(query);
        return result;
    }
    
    public ResultSet getResult() {
        return result;
    }
    
    public void  addReadList(String s) {
    	readList.add(s);
    }
    
    public void  addWriteList(String s) {
    	addReadList(s);
    	writeList.add(s);
    }
    
    public void  addDeleteList(String s) {
    	addWriteList(s);
    	deleteList.add(s);
    }
    
    public ArrayList<String> getReadList() {
		return readList;
    	
    }
    
    public ArrayList<String> getWriteList() {
		return writeList;
    	
    }
    
    public ArrayList<String> getDeleteList() {
		return deleteList;
    	
    }
    
    public void getAccessInf() {
    	System.out.print("Der Benutzer darf in den Rubriken Lesen:");
    	for (int i = 0; i < readList.size(); i++) {
			System.out.print(readList.get(i));
			System.out.print(" & ");
		}
    	System.out.print("\nDer Benutzer darf in den Rubriken Schreiben:");
    	for (int i = 0; i < writeList.size(); i++) {
			System.out.print(writeList.get(i));
			System.out.print(" & ");
		}
    	System.out.print("\nDer Benutzer darf in den Rubriken Löschen:");
    	for (int i = 0; i < deleteList.size(); i++) {
			System.out.print(deleteList.get(i));
			System.out.print(" & ");
		}
    }
}
