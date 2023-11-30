package com.lutum.jbook.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import com.lutum.jbook.controller.FrameController;
import com.lutum.jbook.view.utils.FontManager;

/**
 * @category View
 * 
 * Panel de Deletar registro do ArrayList
 */
public class DeleteView extends JPanel {
    
    // Atributos da Classe
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

    private JButton confirmButton;
    private JButton deleteButton;
    private JButton cleanButton;
    private JButton closeButton;

    /**
     * Construtor da Classe DeleteView
     * 
     * @param frameController
     */
    public DeleteView(FrameController frameController) {

        this.calendar = Calendar.getInstance();
        this.decFormat = new DecimalFormat("00");
        this.fm = new FontManager();
        this.frameController = frameController;
        this.handler = new ButtonHandler();

        setLayout(new FlowLayout(FlowLayout.LEFT));

        initComponents();

    }

    /**
     * Inicia os Componentes da classe DeleteView
     */
    private void initComponents() {

        MaskFormatter dtFormatter = null;
        SpinnerModel model   = new SpinnerNumberModel(0, 0, 70, 1);
        SpinnerModel modelID = new SpinnerNumberModel(0, 0, 999, 1);

        try {
            dtFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        idLabel       = new JLabel("   ID:                           ");
        titleLabel    = new JLabel("   Título: ");
        autorLabel    = new JLabel("   Autor: ");
        dtLabel       = new JLabel("   Data de Publicação:  ");
        qtdLabel      = new JLabel("   Quantidade de Exemplares:          ");
         
        idSpinner     = new JSpinner(modelID);
        titleField    = new JTextField(17);
        autorField    = new JTextField(17);

        dtField       = new JFormattedTextField(dtFormatter);
        dtField.setColumns(15);
        dtField.setFont(fm.getMonospaced());
        dtField.setText("" + decFormat.format(calendar.get(Calendar.DAY_OF_MONTH)) +  decFormat.format(calendar.get(Calendar.MONTH) + 1) +  calendar.get(Calendar.YEAR));

        qtdSpinner    = new JSpinner(model);

        titleField.setEnabled(false);
        autorField.setEnabled(false);
        dtField.setEnabled(false);
        qtdSpinner.setEnabled(false);

        confirmButton = new JButton("Buscar");
        deleteButton  = new JButton("Remover");
        cleanButton   = new JButton("Limpar");
        closeButton   = new JButton("Fechar");

        confirmButton.addActionListener(handler);
        deleteButton.addActionListener(handler);
        cleanButton.addActionListener(handler);
        closeButton.addActionListener(handler);

        deleteButton.setEnabled(false);

        add(idLabel);
        add(idSpinner);
        
        add(confirmButton);
        
        add(titleLabel);
        add(titleField);

        add(autorLabel);
        add(autorField);

        add(dtLabel);
        add(dtField);

        add(qtdLabel);
        add(qtdSpinner);

        add(deleteButton);
        add(cleanButton);
        add(closeButton);

    }

    /**
     * Limpa todos os campos do DeleteView
     */
    protected void clean() {

        idSpinner.setValue(0);
        titleField.setText("");
        autorField.setText("");
        dtField.setText("" + decFormat.format(calendar.get(Calendar.DAY_OF_MONTH)) +  decFormat.format(calendar.get(Calendar.MONTH) + 1) +  calendar.get(Calendar.YEAR));
        qtdSpinner.setValue(0);

        titleField.setEnabled(false);
        autorField.setEnabled(false);
        dtField.setEnabled(false);
        qtdSpinner.setEnabled(false);

        deleteButton.setEnabled(false);

    }

    /**
     * Verifica se o ID inserido existe no ArrayList
     */
    protected void verifica() {
        
        Object[] exists = frameController.verifica((int) idSpinner.getValue());

        try {
            deleteButton.setEnabled(true);
            
            titleField.setText(String.valueOf(exists[1]));
            autorField.setText(String.valueOf(exists[2]));
            dtField.setText(String.valueOf(exists[3]));
            qtdSpinner.setValue(Integer.parseInt(String.valueOf(exists[4])));
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Não existe livro cadastrado com esse ID", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Deleta um livro registrado no ArrayList
     */
    protected void delete() {

        int input = JOptionPane.showConfirmDialog(null,"Confirme a ação de remover um livro do registro", "Confirma",JOptionPane.YES_NO_OPTION);

        if (input == 0) {
            frameController.delete(Integer.parseInt(String.valueOf(idSpinner.getValue())));

            JOptionPane.showMessageDialog(null, "Livro removido com sucesso", "Removido", JOptionPane.PLAIN_MESSAGE);

            clean();
        } else {
            clean();
        }
    }

    /**
     * Classe que lida com os botões do DeleteView
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == cleanButton) {
                clean();
            } else if (src == closeButton) {
                frameController.changeToScreen(0, 280, 340);
            } else if (src == confirmButton) {
                verifica();
            } else {
                delete();
            }
                        
        }

    }

}
