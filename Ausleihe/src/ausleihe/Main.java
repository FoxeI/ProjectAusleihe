package ausleihe;

import java.sql.*;

public class Main {
  
    /**
     * @param args
     */
	public static void main(String[] args){ 
		DataBase db = new DataBase();
		
		db.connect("Foxel", "123");
		db.disconnect();
	}	

}
