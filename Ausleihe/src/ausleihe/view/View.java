package ausleihe.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ausleihe.controller.Controller;
import ausleihe.view.panel.Login;
import ausleihe.view.panel.ToolBar;

public class View extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private Controller controller;
    private JPanel login_tool_Pannel;
    private JPanel main_Pannel;
    
    public View(Controller controller){ 
        super();
        this.controller = controller;
        this.login_tool_Pannel = new JPanel();
        this.main_Pannel = new JPanel();
        
        Login login = new Login(controller);
        ToolBar tools = new ToolBar(controller);
        
        login_tool_Pannel.setLayout(new FlowLayout());
        
        login_tool_Pannel.add(login);
        login_tool_Pannel.add(tools);
        
        setLayout(new BorderLayout());
        add(login_tool_Pannel, BorderLayout.NORTH);
        add(main_Pannel, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }

}
