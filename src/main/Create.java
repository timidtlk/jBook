package main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Create extends JPanel {
    
    private FontManager fm;

    public Create() {

        fm = new FontManager();

        setPreferredSize(new Dimension(300, 315));
        setLayout(new FlowLayout());

        initComponents();

    }

    private void initComponents() {



    }

}
