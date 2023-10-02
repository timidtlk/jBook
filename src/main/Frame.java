package main;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private Menu menuPanel;
    
    public Frame() {

        menuPanel = new Menu();

        setTitle("jBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(menuPanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
