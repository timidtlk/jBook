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

/**
 * @category View
 * 
 * Panel de Menu da Aplicação
 */
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
    private JButton closeButton;

    /**
     * Para conseguir trocar de telas, todas as classes de painel recebem o frameController para poderem trocar de telas
     * 
     * @param frameController
     */
    public MenuView(FrameController frameController) {

        handler = new ButtonHandler();
        fm = new FontManager();
        this.frameController = frameController;

        setPreferredSize(new Dimension(280, 295));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initComponents();

    }

    /**
     * Inicia os Componentes da classe DeleteView
     */
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
        readButton.addActionListener(handler);

        updateButton = new JButton("Atualizar Livro");
        updateButton.setAlignmentX(CENTER_ALIGNMENT);
        updateButton.addActionListener(handler);

        deleteButton = new JButton("Remover Livro");
        deleteButton.setAlignmentX(CENTER_ALIGNMENT);
        deleteButton.addActionListener(handler);

        closeButton  = new JButton("Sair");
        closeButton.setAlignmentX(CENTER_ALIGNMENT);
        closeButton.addActionListener(handler);

        add(titleLabel);
        add(subTitleLabel);
        add(Box.createVerticalStrut(25));
        add(createButton);
        add(Box.createVerticalStrut(6));
        add(updateButton);
        add(Box.createVerticalStrut(6));
        add(readButton);
        add(Box.createVerticalStrut(6));
        add(deleteButton);
        add(Box.createVerticalStrut(6));
        add(closeButton);
    }

    /**
     * Classe que lida com os botões do MenuView    
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == createButton) {
                frameController.changeToScreen(1, 270, 200);
            } else if (src == readButton) {
                frameController.changeToScreen(2, 494, 502);
            } else if (src == updateButton) {
                frameController.changeToScreen(3, 270, 207);
            } else if (src == deleteButton) {
                frameController.changeToScreen(4, 270, 207);
            } else {
                frameController.dispose();
                System.exit(0);
            }
                        
        }

    }

}
