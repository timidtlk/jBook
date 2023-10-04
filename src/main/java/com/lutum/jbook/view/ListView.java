package com.lutum.jbook.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lutum.jbook.controller.FrameController;

/**
 * @category View
 * 
 * Panel de Listar registros do ArrayList
 */
public class ListView extends JPanel {
    
    // Atributos da Classe
    private ButtonHandler   handler;
    private FrameController frameController;

    private JTable     tableList;
    private Object[][] dados;

    private JTextField searchField;
    private JButton    searchButton;

    private JButton    closeButton;

    /**
     * Construtor da classe ListView
     * 
     * @param dados
     * @param frameController
     */
    public ListView(Object[][] dados, FrameController frameController) {

        this.handler = new ButtonHandler();
        this.frameController = frameController;
        this.dados = dados;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        initComponents();

    }

    /**
     * Inicia os Componentes da classe DeleteView
     */
    private void initComponents() {

        String [] colunas = {"ID", "Título", "Autor", "Publicado em", "Quantidade"};

        tableList = new JTable(dados, colunas);
        tableList.setDefaultEditor(Object.class, null);
        tableList.getTableHeader().setReorderingAllowed(false); 
        tableList.getTableHeader().setResizingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(tableList);

        searchField = new JTextField(10);

        ImageIcon searchIcon = null;
        ImageIcon searchRollover = null;

        try {
            BufferedImage bImage = ImageIO.read(getClass().getResource("../resources/search.png"));
            searchIcon = new ImageIcon(bImage);

            bImage = ImageIO.read(getClass().getResource("../resources/searchRollover.png"));
            searchRollover = new ImageIcon(bImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchButton = new JButton("Buscar", searchIcon);
        searchButton.setRolloverIcon(searchRollover);
        searchButton.addActionListener(handler);

        closeButton = new JButton("Fechar");
        closeButton.addActionListener(handler);

        add(searchField);
        add(searchButton);
        add(Box.createHorizontalStrut(170));
        add(closeButton);
        add(scrollPane);

    }

    /**
     * Busca os registros compatíveis com a consulta no ArrayList
     */
    public void busca() {
        if (searchField.getText() != "") {
            String[][] busca = frameController.buscar(searchField.getText());
            
            for (int i = 0; i < busca.length; i++) {
                for (int j = 0; j < busca[0].length; j++) {
                    tableList.setValueAt(busca[i][j], i, j);
                }
            }
        }
    }

    /** 
     * Atualiza a tabela com base no ArrayList
     */
    public void atualiza() {
        String[][] busca = frameController.buscar("");
        
        for (int i = 0; i < busca.length; i++) {
            for (int j = 0; j < busca[0].length; j++) {
                tableList.setValueAt(busca[i][j], i, j);
            }
        }
    }

    /**
     * Classe que lida com os botões do ListView
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == searchButton) {
                busca();
            } else if (src == closeButton) {
                frameController.changeToScreen(0, 280, 340);
            }
                        
        }
    }

    // Getters e Setters
    public Object[][] getDados() {
        return dados;
    }

    public void setDados(Object[][] dados) {
        this.dados = dados;
    }

}
