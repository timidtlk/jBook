package com.lutum.jbook.controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lutum.jbook.view.CreateView;
import com.lutum.jbook.view.MenuView;

public class FrameController extends JFrame {

    private JPanel     layout;
    private MenuView   menuPanel;
    private CreateView createPanel;
    
    public FrameController() {

        menuPanel   = new MenuView(this);
        createPanel = new CreateView(this);

        layout = new JPanel(new CardLayout());
        layout.add(menuPanel);
        layout.add(createPanel);

        setTitle("jBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setSize(280, 340);

        add(layout);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Troca de tela a partir do indice da tela no CardLayout
     * 
     * @param screen
     * @param width
     * @param height
     */
    public void changeToScreen(int screen, int width, int height) {

        int actualPanel = 0;

        for (int i = 0; i < layout.getComponentCount(); i++) {
            if (layout.getComponent(i).isVisible()) {
                actualPanel = i;
                break;
            }
        }

        layout.getComponent(actualPanel).setVisible(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        layout.getComponent(screen).setVisible(true);
    }
}
