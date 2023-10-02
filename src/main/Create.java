package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

public class Create extends JPanel {
    
    private FontManager fm;

    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel autorLabel;
    private JLabel dtLabel;
    private JLabel qtdLabel;

    private JSpinner idSpinner;
    private JTextField titleField;
    private JTextField autorField;
    private JFormattedTextField dtField;
    private JSpinner qtdSpinner;

    private JButton addButton;
    private JButton cleanButton;
    private JButton closeButton;

    public Create() {

        fm = new FontManager();

        setPreferredSize(new Dimension(255, 162));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        initComponents();

    }

    private void initComponents() {

        MaskFormatter dtFormatter = null;
        SpinnerModel model   = new SpinnerNumberModel(0, 0, 70, 1);
        SpinnerModel modelID = new SpinnerNumberModel(0, 0, 999, 1);

        try {
            dtFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idLabel     = new JLabel("   ID:                                                     ");
        titleLabel  = new JLabel("   Título: ");
        autorLabel  = new JLabel("   Autor: ");
        dtLabel     = new JLabel("   Data de Publicação:  ");
        qtdLabel    = new JLabel("   Quantidade de Exemplares:          ");
        
        idSpinner   = new JSpinner(modelID);
        titleField  = new JTextField(17);
        autorField  = new JTextField(17);

        dtField     = new JFormattedTextField(dtFormatter);
        dtField.setColumns(15);
        dtField.setFont(fm.getMonospaced());

        qtdSpinner  = new JSpinner(model);

        addButton   = new JButton("Adicionar");
        cleanButton = new JButton("Limpar");
        closeButton = new JButton("Fechar");

        add(idLabel);
        add(idSpinner);
        
        add(titleLabel);
        add(titleField);

        add(autorLabel);
        add(autorField);

        add(dtLabel);
        add(dtField);

        add(qtdLabel);
        add(qtdSpinner);

        add(addButton);
        add(cleanButton);
        add(closeButton);

    }

}
