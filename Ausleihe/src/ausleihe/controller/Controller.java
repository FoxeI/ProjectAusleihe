package ausleihe.controller;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TabExpander;

import ausleihe.db.DataBase;
import ausleihe.view.View;

public class Controller {
    DataBase database;
    View view;
    String loggedUser;
    
    public Controller(){
        database = new DataBase(this);
        view = new View(this);
    }
    
    public void connect(String username, String password){
        if(database.connect(username, password)){
            view.setConnected(username);
        } else {
            view.setConnectionFailed();
        }
    }
    

    
    
    public void updateValue(String primary_name, String column_name, String key ,String value){
        database.execute("UPDATE " + view.getCurrentTableName() + " SET " + column_name + " = \'" + value + "\' WHERE " + primary_name + " = " + key);
        loadTable(view.getCurrentTableName());
    }
    
    public void createTupel(String string1, String string2){
			database.execute("INSERT INTO " + view.getCurrentTableName() + string1 + " VALUES " + string2 + ";");
    		loadTable(view.getCurrentTableName());
    }
    
    public void deleteTupel(String primary_name, String key){
        database.execute("DELETE FROM " + view.getCurrentTableName() + " WHERE " + primary_name + " = " + key);
    }
    
    
    /**
     * Get the Access Permission of the Table and store it in ArrayList's in Database.java
     * @param userName The Logged User
     */
    public void accessRead(String userName) {
    	try {
			ResultSet result = database.executeQuery("SELECT * FROM permission WHERE Name=\'"+ userName +"\';");
                    
            int colum_count = result.getMetaData().getColumnCount();
            String[] labels = new String[colum_count];
            
            for(int i = 1; i <= colum_count; ++i){
                labels[i-1] = result.getMetaData().getColumnLabel(i);
            }
            
            while(result.next()){
            	for(int i = 1; i < labels.length; i++){
                   //System.out.println(result.getString(labels[i])); 
                   if(result.getString(labels[i]).equals("d")) {
                	   //System.out.println(result.getMetaData().getColumnLabel(i+1));
                	   database.addDeleteList(result.getMetaData().getColumnLabel(i+1));
                   }
                   if(result.getString(labels[i]).equals("w")) {
                	   database.addWriteList(result.getMetaData().getColumnLabel(i+1));
                   }
                   if(result.getString(labels[i]).equals("r")) {
                	   database.addReadList(result.getMetaData().getColumnLabel(i+1));
                   }
                }
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void loadTable(String name){
        try {
            ResultSet result = database.executeQuery("SELECT * FROM " + name);
            
            
            int colum_count = result.getMetaData().getColumnCount();
            String[] labels = new String[colum_count];
            for(int i = 1; i <= colum_count; ++i){
                labels[i-1] = result.getMetaData().getColumnLabel(i);
            }
            
            TableModel tableModle = new DefaultTableModel(labels, 100);
            
            int y = 0;
            while(result.next()){
                for(int i = 0; i < colum_count; i++){
                    tableModle.setValueAt(result.getString(labels[i]), y, i);
                    
                }
                ++y;
            }
            
            view.setCurrentTableName(name);
            view.showTableModel(tableModle);
            
        } catch (SQLException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }
    
    public String getLoggedUser() {
		return loggedUser;
	}
    
    public void setLoggedUser(String s) {
    	loggedUser = s;
    }
  
    public DataBase getDatabase() {
    	return database;
    }
}
