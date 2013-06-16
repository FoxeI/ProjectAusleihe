package ausleihe.view.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ausleihe.controller.Controller;


public class Login extends JPanel{
    private static final long serialVersionUID = 1L;
    private Controller controller;
    
    private JTextArea taUser;
    private JTextArea taPasw;
    private JButton bLogin;
    
    public Login(final Controller controller){
        super();
        this.controller = controller;
        
        taUser = new JTextArea("Foxel");
        taPasw = new JTextArea("123");
        
        taUser.setPreferredSize(new Dimension(80, 20));
        taPasw.setPreferredSize(new Dimension(80, 20));
        
        bLogin = new JButton("login");
        bLogin.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) { 
                controller.connect(taUser.getText(), taPasw.getText());
            }
        });
        
        add(taUser);
        add(taPasw);
        add(bLogin);

    }
    
    public void setConnected(String name){
        removeAll();
        add(new JLabel("Connected as " + name));
    }

    public String getUsername(){
        return taUser.getText();
    }
    
    public String getPassword(){
        return taPasw.getText();
    }
    
    public JButton getLoginButton(){
        return bLogin;
    }
}
