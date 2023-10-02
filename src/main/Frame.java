package main;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private Menu menuPanel;
    private Create createPanel;
    
    public Frame() {

        menuPanel   = new Menu();
        createPanel = new Create();

        setTitle("jBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(createPanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
