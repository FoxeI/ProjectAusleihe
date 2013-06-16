package ausleihe.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ausleihe.controller.Controller;

public class ToolBar extends JPanel{
    private static final long serialVersionUID = 1L;

    private Controller controller;
    
    public ToolBar(Controller controler){
        super();
        this.controller = controler;
       
    }
    
    public void loadTools(String username){
        final Controller tmp = this.controller;
        
        ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                tmp.loadTable(e.getActionCommand());
            }
        };
        
        // TODO: select buttons for specific user permissions
        
        JButton computer = new JButton("COMPUTER");
        JButton lizenz = new JButton("LIZENZ");
        JButton ausleihe = new JButton("AUSLEIHE");
        JButton mitarbeiter = new JButton("MITARBEITER");
        JButton kunde = new JButton("KUNDE");
        
        computer.addActionListener(al);
        lizenz.addActionListener(al);
        ausleihe.addActionListener(al);
        mitarbeiter.addActionListener(al);
        kunde.addActionListener(al);

        add(computer);
        add(lizenz);
        add(ausleihe);
        add(mitarbeiter);
        add(kunde);
    }
    
   public void addData() {
	   
   }
}
