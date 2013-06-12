package ausleihe.view.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import ausleihe.controller.Controller;

public class ToolBar extends JPanel{
    private static final long serialVersionUID = 1L;

    private Controller controller;
    
    public ToolBar(Controller controler){
        super();
        this.controller = controler;
        
        add(new JButton("Computer"));
        add(new JButton("Kunde"));
        add(new JButton("Lizenz"));
        add(new JButton("Ausleihe"));
        add(new JButton("Mitarbeiter"));
        
        
    }
}
