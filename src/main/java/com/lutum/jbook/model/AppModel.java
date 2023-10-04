package com.lutum.jbook.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lutum.jbook.model.VO.LivroVO;

/**
 * @category Model
 * 
 * Controla as funções do ArrayList, como o CRUD inteiro e a criação de registros iniciais
 */
public class AppModel {
    
    // Atributos da Classe
    private ArrayList<LivroVO> livros;
    private SimpleDateFormat formatter;

    // Construtor da Classe AppModel
    public AppModel() {
        this.livros = new ArrayList<>();

        formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date capitalDate = null;
        Date existenDate = null;
        Date budapesDate = null;
        Date aboboraDate = null;
        Date harryPoDate = null;

        try {
            capitalDate = formatter.parse("14/09/1867");
            existenDate = formatter.parse("29/10/1945");
            budapesDate = formatter.parse("10/09/2003");
            aboboraDate = formatter.parse("01/11/2010");
            harryPoDate = formatter.parse("26/06/1997");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.livros.add(new LivroVO(0, "O Capital", "Karl Marx", capitalDate, 10));
        this.livros.add(new LivroVO(1, "O Existencialismo é Humanismo", "Jean-Paul Sartre", existenDate, 5));
        this.livros.add(new LivroVO(2, "Budapeste", "Chico Buarque", budapesDate, 13));
        this.livros.add(new LivroVO(3, "As Duas Faces Da Abobora", "Caco Porto", aboboraDate, 7));
        this.livros.add(new LivroVO(4, "Harry Potter e a Pedra Filosofal", "J. K. Rowling", harryPoDate, 9));
    }

    /**
     * Pega todos os dados do Array List e transforma em tabela
     * 
     * @return matriz com a tabela
     */
    public String[][] getDados() {

        String[][] dados = new String[256][5];

        for (int i = 0; i < dados.length; i++) {       
            if (this.livros.size() > i) {
                dados[i][0] = String.valueOf(this.livros.get(i).getId());
                dados[i][1] = String.valueOf(this.livros.get(i).getTitulo());
                dados[i][2] = String.valueOf(this.livros.get(i).getAutor());
                dados[i][3] = String.valueOf(this.formatter.format(this.livros.get(i).getDtPublicacao()));
                dados[i][4] = String.valueOf(this.livros.get(i).getQtdExemplares());
            }
        }

        return dados;
    }

    /**
     * Busca um registro de livro no ArrayList
     * 
     * @param busca
     * 
     * @return Uma matriz com os valores buscados
     */
    public String[][] buscar(String busca) {

        String[][] dadosT  = getDados();
        String[][] procura = new String[256][5];
        int qtdSearch = 0;

        for (int i = 0; i < dadosT.length; i++) {
            if (dadosT[i][1] != null) {
                if (dadosT[i][1].toLowerCase().contains(busca.toLowerCase()))
                    procura[qtdSearch++] = dadosT[i];
            }
        }

        return procura;

    }

    /**
     * Cria um registro de livro no ArrayList
     * 
     * @param id
     * @param titulo
     * @param autor
     * @param data
     * @param qtdExemplares
     * 
     * @return Mensagem do message dialog e codigo de erro
     */
    public String create(int id, String titulo, String autor, String data, int qtdExemplares) {
        
        if ( titulo.isBlank() || autor.isBlank() || data.isBlank()) {
            return "Preencha todos os campos para cadastrar um livro@inf";
        }

        for (LivroVO livro : this.livros) {
            if (livro.getId() == id) {
                return "Já existe um livro cadastrado com esse ID@err";
            }
        }

        Date dtPubli = null;

        try {
            dtPubli = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.livros.add(new LivroVO(id, titulo, autor, dtPubli, qtdExemplares));
        
        return "Livro cadastrado com sucesso@suc";
    }

    /**
     * Verifica se existe um registro de Livro específico no ArrayList
     * 
     * @param id
     * @return
     */
    public String[] verifica(int id) {
        
        for (LivroVO livroVO : livros) {
            if (livroVO.getId() == id) {
                String[] temp = new String[4];
                
                temp[0] = livroVO.getTitulo();
                temp[1] = livroVO.getAutor();
                temp[2] = String.valueOf(formatter.format(livroVO.getDtPublicacao()));
                temp[3] = String.valueOf(livroVO.getQtdExemplares());

                return temp;
            }
        }

        return null;

    }

    /**
     * Atualiza um objeto no ArrayList com base no seu índice e nas informações novas
     *  
     * @param i
     * @param object
     */
    public void update(int i, String[] object) {

        Date dtPubli = null;

        try {
            dtPubli = formatter.parse(object[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int id = 0;

        for (LivroVO livroVO : this.livros) {
            if (livroVO != null) {
                if (livroVO.getId() == i) {
                    id = this.livros.indexOf(livroVO);
                }
            }
        }

        this.livros.set(id, new LivroVO(i, object[0], object[1], dtPubli, Integer.parseInt(object[3])));

    }

    /**
     * Remove um objeto no ArrayList com base no seu índice
     * 
     * @param i
     */
    public void delete(int i) {

        int id = 0;

        for (LivroVO livroVO : this.livros) {
            if (livroVO != null) {
                if (livroVO.getId() == i) {
                    id = this.livros.indexOf(livroVO);
                }
            }
        }

        this.livros.remove(id);
    }

}
