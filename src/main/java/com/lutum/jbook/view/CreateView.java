package com.lutum.jbook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import com.lutum.jbook.controller.FrameController;
import com.lutum.jbook.view.utils.FontManager;

public class CreateView extends JPanel {
    
    private ButtonHandler handler;
    private FrameController frameController;
    private FontManager fm;

    private Calendar calendar;
    private DecimalFormat decFormat;

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

    public CreateView(FrameController frameController) {

        this.calendar = Calendar.getInstance();
        this.decFormat = new DecimalFormat("00");
        this.fm = new FontManager();
        this.frameController = frameController;
        this.handler = new ButtonHandler();

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
        dtField.setText("" + decFormat.format(calendar.get(Calendar.DAY_OF_MONTH)) +  decFormat.format(calendar.get(Calendar.MONTH) + 1) +  calendar.get(Calendar.YEAR));

        qtdSpinner  = new JSpinner(model);

        addButton   = new JButton("Adicionar");
        cleanButton = new JButton("Limpar");
        closeButton = new JButton("Fechar");

        addButton.addActionListener(handler);
        cleanButton.addActionListener(handler);
        closeButton.addActionListener(handler);

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

    protected void clean() {

        idSpinner.setValue(0);
        titleField.setText("");
        autorField.setText("");
        dtField.setText("" + decFormat.format(calendar.get(Calendar.DAY_OF_MONTH)) +  decFormat.format(calendar.get(Calendar.MONTH) + 1) +  calendar.get(Calendar.YEAR));
        qtdSpinner.setValue(0);

    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == cleanButton) {
                clean();
            } else if (src == closeButton) {
                frameController.changeToScreen(0, 280, 320);
            }
                        
        }

    }

}
