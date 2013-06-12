package ausleihe;

import ausleihe.db.DataBase;

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
