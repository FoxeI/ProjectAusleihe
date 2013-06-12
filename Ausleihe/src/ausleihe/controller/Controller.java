package ausleihe.controller;

import ausleihe.db.DataBase;
import ausleihe.view.View;

public class Controller {
    DataBase database;
    View view;
    
    public Controller(){
        database = new DataBase(this);
        view = new View(this);
    }
    
    public void connect(String username, String password){
        database.connect(username, password);
    }
}
