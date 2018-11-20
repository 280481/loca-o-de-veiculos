package br.com.rff.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Telinha extends JInternalFrame {

    public Telinha() {
        super("BiCiDi");
        this.add(new JLabel("One thing"));
        this.add(new JTextField("I dunno uai"));
        
        this.pack();
        this.setVisible(true);
    }
    
}
