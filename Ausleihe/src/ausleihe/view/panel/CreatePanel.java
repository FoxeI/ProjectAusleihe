package ausleihe.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import ausleihe.controller.Controller;
import ausleihe.view.View;

public class CreatePanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private View view;
    
    private JPanel values_panel;
    private JPanel button_panel;
    
    public CreatePanel(final Controller controller, final View view, TableModel tableModle){
        super();
        this.controller = controller;
        this.view = view;
        
        
        values_panel = new JPanel();
        button_panel = new JPanel();
        
        JTextArea text;
        for(int i = 0; i < tableModle.getColumnCount(); i++){
            values_panel.add(new JLabel(tableModle.getColumnName(i)));
            text = new JTextArea();
            text.setPreferredSize(new Dimension(80,20));
            values_panel.add(text);
        }
        
        //values_panel.setPreferredSize(new Dimension(200, 0));
        JButton create_button = new JButton("CREATE");
        create_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s1 = "(";
                String s2 = "('";
                
                JLabel label = null;
                JTextArea area = null;
                
                for(int i = 0; i < values_panel.getComponentCount(); i += 2){
                    label = (JLabel) values_panel.getComponent( i );
                    area = (JTextArea) values_panel.getComponent(i+1);
                    
                    s1 += label.getText();
                    s2 += area.getText();
                    
                    if(i < values_panel.getComponentCount()-2){
                        s1 += ",";
                        s2 += "','";
                    } else {
                        s1 += ")";
                        s2 += "')";
                    }
                }
                
                controller.createTupel(s1, s2);
            }
        });
        
        JButton delete_button = new JButton("DELETE");
        delete_button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                view.deleteSelectedTupels();
            }
        });
        
        
        values_panel.setLayout(new GridLayout(9, 2,2,2));
        button_panel.add(create_button);
        button_panel.add(delete_button);
        button_panel.setPreferredSize(new Dimension(200, 400));
        
        
        setLayout(new BorderLayout());
        add(values_panel,BorderLayout.CENTER);
        add(button_panel, BorderLayout.SOUTH);
    }
}
