package ausleihe.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ausleihe.controller.Controller;

public class View extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private Controller controller;
    
    public View(Controller controller){ 
        super();
        this.controller = controller;
        
        Login login = new Login(controller);

        
        add(login);
        
        pack();
        setVisible(true);
    }

}
