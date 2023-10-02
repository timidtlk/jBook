package com.lutum.jbook.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lutum.jbook.controller.FrameController;
import com.lutum.jbook.view.utils.FontManager;

public class MenuView extends JPanel {

    private ButtonHandler handler;
    private FrameController frameController;
    private FontManager fm;
    
    private JLabel titleLabel;
    private JLabel subTitleLabel;

    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;

    /**
     * Para conseguir trocar de telas, todas as classes de painel recebem o frameController para poderem trocar de telas
     * 
     * @param frameController
     */
    public MenuView(FrameController frameController) {

        handler = new ButtonHandler();
        fm = new FontManager();
        this.frameController = frameController;

        setPreferredSize(new Dimension(280, 315));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initComponents();

    }

    private void initComponents() {
        titleLabel = new JLabel("jBook");
        subTitleLabel = new JLabel("Feito por Gustavo Gil e Emilly Caxias");

        titleLabel.setFont(fm.getTitle());
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        subTitleLabel.setFont(fm.getSubtitle());
        subTitleLabel.setAlignmentX(CENTER_ALIGNMENT);

        createButton = new JButton("Adicionar Livro");
        createButton.setAlignmentX(CENTER_ALIGNMENT);
        createButton.addActionListener(handler);

        readButton   = new JButton("Listar Livros");
        readButton.setAlignmentX(CENTER_ALIGNMENT);

        updateButton = new JButton("Atualizar Livro");
        updateButton.setAlignmentX(CENTER_ALIGNMENT);

        deleteButton = new JButton("Remover Livro");
        deleteButton.setAlignmentX(CENTER_ALIGNMENT);

        searchButton = new JButton("Buscar Livro");
        searchButton.setAlignmentX(CENTER_ALIGNMENT);

        add(titleLabel);
        add(subTitleLabel);
        add(Box.createVerticalStrut(25));
        add(createButton);
        add(Box.createVerticalStrut(6));
        add(updateButton);
        add(Box.createVerticalStrut(6));
        add(searchButton);
        add(Box.createVerticalStrut(6));
        add(readButton);
        add(Box.createVerticalStrut(6));
        add(deleteButton);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == createButton) {
                frameController.changeToScreen(1, 270, 200);
            }
                        
        }

    }

}
