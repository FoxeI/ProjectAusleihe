package ausleihe.view.panel;

import javax.swing.JPanel;

import ausleihe.controller.Controller;

public class CreatePanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private Controller controller;
    
    public CreatePanel(Controller controller){
        super();
        this.controller = controller;
    }
}
