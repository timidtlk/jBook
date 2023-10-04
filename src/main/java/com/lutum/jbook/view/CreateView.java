package com.lutum.jbook.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Panel de Criar registro no ArrayList
 */
public class CreateView extends JPanel {
    
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

    private JButton addButton;
    private JButton cleanButton;
    private JButton closeButton;

    /**
     * Construtor da classe CreateView
     * 
     * @param frameController
     */
    public CreateView(FrameController frameController) {

        this.calendar = Calendar.getInstance();
        this.decFormat = new DecimalFormat("00");
        this.fm = new FontManager();
        this.frameController = frameController;
        this.handler = new ButtonHandler();

        setLayout(new FlowLayout(FlowLayout.LEFT));

        initComponents();

    }

    /**
     * Inicia os Componentes da classe CreateView
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

    /**
     * Limpa todos os campos do CreateView
     */
    protected void clean() {

        idSpinner.setValue(0);
        titleField.setText("");
        autorField.setText("");
        dtField.setText("" + decFormat.format(calendar.get(Calendar.DAY_OF_MONTH)) +  decFormat.format(calendar.get(Calendar.MONTH) + 1) +  calendar.get(Calendar.YEAR));
        qtdSpinner.setValue(0);

    }

    /**
     * Serve para criar o registro no ArrayList, chamando o Controller para fazer esse registro
     */
    protected void create() {
        
        int id        = (int) idSpinner.getValue();
        String titulo = titleField.getText();
        String autor  = autorField.getText();
        String data   = dtField.getText();
        int qtd       = (int) qtdSpinner.getValue(); 

        String create = frameController.create(id, titulo, autor, data, qtd);
        String[] message = create.split("@");

        int type = JOptionPane.PLAIN_MESSAGE;
        String titleMessage = "Cadastrado";

        switch (message[1]) {
            case "err":
                type = JOptionPane.ERROR_MESSAGE;
                titleMessage = "Erro";
                break;
            case "inf":
                type = JOptionPane.INFORMATION_MESSAGE;
                titleMessage = "Campos Vazios";
                break;
            default:
                break;
        }

        frameController.atualizaTabela();
        JOptionPane.showMessageDialog(null, message[0], titleMessage, type);
        if (type != JOptionPane.ERROR_MESSAGE) 
            clean();

    }

    /**
     * Classe que lida com os botões do CreateView
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object src = e.getSource();

            if (src == cleanButton) {
                clean();
            } else if (src == closeButton) {
                frameController.changeToScreen(0, 280, 340);
            } else {
                create();
            }
                        
        }

    }

}
