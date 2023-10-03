package com.lutum.jbook.controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lutum.jbook.model.AppModel;
import com.lutum.jbook.view.CreateView;
import com.lutum.jbook.view.ListView;
import com.lutum.jbook.view.MenuView;

public class FrameController extends JFrame {

    private JPanel     layout;
    private AppModel   appModel;
    private MenuView   menuPanel;
    private CreateView createPanel;
    private ListView   listPanel;
    
    public FrameController() {

        appModel    = new AppModel();
        menuPanel   = new MenuView(this);
        createPanel = new CreateView(this);
        listPanel   = new ListView(appModel.getDados(), this);

        layout = new JPanel(new CardLayout());
        layout.add(menuPanel);
        layout.add(createPanel);
        layout.add(listPanel);

        setTitle("jBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setSize(280, 320);

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

        if (screen == 2)
            listPanel.setDados(appModel.getDados());

        layout.getComponent(screen).setVisible(true);
    }

    public String[][] buscar(String busca) {
        return appModel.buscar(busca);
    }

    public String create(int id, String titulo, String autor, String data, int qtdExemplares) {
        return appModel.create(id, titulo, autor, data, qtdExemplares);
    }

    public void atualizaTabela() {
        listPanel.atualiza();
    }
}
