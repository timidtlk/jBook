package main;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    private FontManager fm;
    
    private JLabel titleLabel;
    private JLabel subTitleLabel;

    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;

    public Menu() {

        fm = new FontManager();

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

}
