package ausleihe.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ausleihe.controller.Controller;

public class ToolBar extends JPanel{
    private static final long serialVersionUID = 1L;

    private Controller controller;
    private ArrayList<JButton> btn = new ArrayList<JButton>();
    
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
        
        for (int i = 0; i < controller.getDatabase().getReadList().size(); i++) {
			btn.add(new JButton(controller.getDatabase().getReadList().get(i)));
			btn.get(i).addActionListener(al);
			add(btn.get(i));
		}
        
    }
    
}
