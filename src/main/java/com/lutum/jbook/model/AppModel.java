package com.lutum.jbook.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import com.lutum.jbook.model.VO.LivroVO;

public class AppModel {
    
    ArrayList<LivroVO> livros;

    public AppModel() {
        this.livros = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date capitalDate = null;
        Date existenDate = null;
        Date budapesDate = null;
        Date aboboraDate = null;

        try {
            capitalDate = formatter.parse("14/09/1867");
            existenDate = formatter.parse("29/10/1945");
            budapesDate = formatter.parse("10/09/2003");
            aboboraDate = formatter.parse("01/11/2010");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.livros.add(new LivroVO(0, "O Capital", "Karl Marx", capitalDate, 10));
        this.livros.add(new LivroVO(1, "O Existencialismo é Humanismo", "Jean-Paul Sartre", existenDate, 5));
        this.livros.add(new LivroVO(2, "Budapeste", "Chico Buarque", budapesDate, 13));
        this.livros.add(new LivroVO(3, "As Duas Faces Da Abobora", "Caco Porto", aboboraDate, 7));
    }

    public String[][] getDados() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String[][] dados = new String[255][5];

        for (int i = 0; i < dados.length; i++) {       
            if (this.livros.size() > i) {
                dados[i][0] = String.valueOf(this.livros.get(i).getId());
                dados[i][1] = String.valueOf(this.livros.get(i).getTitulo());
                dados[i][2] = String.valueOf(this.livros.get(i).getAutor());
                dados[i][3] = String.valueOf(simpleDateFormat.format(this.livros.get(i).getDtPublicacao()));
                dados[i][4] = String.valueOf(this.livros.get(i).getQtdExemplares());
            }
        }

        return dados;
    }

    public String[][] buscar(String busca) {

        String[][] dadosT  = getDados();
        String[][] procura = new String[255][5];
        int qtdSearch = 0;

        for (int i = 0; i < dadosT.length; i++) {
            if (dadosT[i][1] != null) {
                if (dadosT[i][1].toLowerCase().contains(busca.toLowerCase()))
                    procura[qtdSearch++] = dadosT[i];
            }
        }

        return procura;

    }

    public void create() {
        
    }

}
