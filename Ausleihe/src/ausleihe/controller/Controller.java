package ausleihe.controller;

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
    
    public Controller(){
        database = new DataBase(this);
        view = new View(this);
    }
    
    public boolean connect(String username, String password){
        return database.connect(username, password);
    }
    
    
    public void updateValue(String primary_name, String column_name, String key ,String value){
        database.execute("UPDATE " + view.getCurrentTableName() + " SET " + column_name + " = \'" + value + "\' WHERE " + primary_name + " = " + key);
    }
    
    public void loadTable(String name){
        try {
            ResultSet result = database.executeQuery("SELECT * FROM " + name);
            
            int colum_count = result.getMetaData().getColumnCount();
            String[] labels = new String[colum_count];
            for(int i = 1; i <= colum_count; ++i){
                labels[i-1] = result.getMetaData().getColumnLabel(i);
            }
            
            TableModel tableModle = new DefaultTableModel(labels, 10);
            
            
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
}
