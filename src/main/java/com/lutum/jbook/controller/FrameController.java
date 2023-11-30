package com.lutum.jbook.controller;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lutum.jbook.model.AppModel;
import com.lutum.jbook.view.CreateView;
import com.lutum.jbook.view.DeleteView;
import com.lutum.jbook.view.ListView;
import com.lutum.jbook.view.MenuView;
import com.lutum.jbook.view.UpdateView;

/**
 * @category Controller
 * 
 * Controlador dos Frames da APP, conversa com o Model e com o View, além de inicializar o frame e trocar de janelas
 */
public class FrameController extends JFrame {

    // Atributos
    private JPanel     layout;
    private AppModel   appModel;
    private MenuView   menuPanel;
    private CreateView createPanel;
    private ListView   listPanel;
    private UpdateView updatePanel;
    private DeleteView deletePanel;
    
    // Construtor
    public FrameController() {

        // Inicializa os atributos
        appModel    = new AppModel();
        menuPanel   = new MenuView(this);
        createPanel = new CreateView(this);
        listPanel   = new ListView(appModel.getDados(), this);
        updatePanel = new UpdateView(this);
        deletePanel = new DeleteView(this);

        // Inicializa o layout que vai trocar de telas
        layout = new JPanel(new CardLayout());

        // Adiciona os Panel no Layout
        layout.add(menuPanel);
        layout.add(createPanel);
        layout.add(listPanel);
        layout.add(updatePanel);
        layout.add(deletePanel);

        // Configurações do frame
        setTitle("jBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Image icon = null;

        try {
            BufferedImage bImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon.png"));
            icon = bImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIconImage(icon);

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

        if (screen == 2)
            listPanel.setDados(appModel.getDados());

        layout.getComponent(screen).setVisible(true);
    }

    /**
     * Chama o buscar do Model, serve para chamar o buscar através do View
     * 
     * @param busca
     * @return
     */
    public String[][] buscar(String busca) {
        return appModel.buscar(busca);
    }

    /**
     * Chama o create do Model, serve para chamar o create através do View
     * 
     * @param id
     * @param titulo
     * @param autor
     * @param data
     * @param qtdExemplares
     * @return
     */
    public String create(int id, String titulo, String autor, String data, int qtdExemplares) {
        return appModel.create(id, titulo, autor, data, qtdExemplares);
    }

    /**
     * Atualiza a tabela do listPanel
     */
    public void atualizaTabela() {
        listPanel.atualiza();
    }

    /**
     * Chama o verifica do Model, serve para chamar o verifica através do View
     * 
     * @param id
     * @return
     */
    public Object[] verifica(int id) {
        return appModel.verifica(id);
    }

    /**
     * Chama o update do Model, serve para chamar o update através do View
     * 
     * @param i
     * @param object
     */
    public void update(int i, String[] object) {
        appModel.update(i, object);
        listPanel.atualiza();
    }

    /**
     * Chama o buscar do Model, serve para chamar o buscar através do View
     * 
     * @param i
     */
    public void delete(int i) {
        appModel.delete(i);
        listPanel.atualiza();
    }
}
