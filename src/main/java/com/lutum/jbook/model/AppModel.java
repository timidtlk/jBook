package com.lutum.jbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.lutum.jbook.model.VO.LivroVO;
import com.lutum.jbook.model.db.ConnectDB;

/**
 * @category Model
 * 
 * Controla as funções do ArrayList, como o CRUD inteiro e a criação de registros iniciais
 */
public class AppModel extends ConnectDB {
    
    // Atributos da Classe
    private ArrayList<LivroVO> livros;
    private SimpleDateFormat formatter;
    private Connection connect;

    // Construtor da Classe AppModel
    public AppModel() {
        this.livros = new ArrayList<>();
        this.connect = super.connectDB();

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

        String[][] procura = new String[256][5];

        try {
            String query = "SELECT * FROM Livros";
            ResultSet rs = connect.prepareStatement(query).executeQuery();

            ArrayList<LivroVO> livroVOs = new ArrayList<>();

            SimpleDateFormat americanFormatter = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                livroVOs.add(new LivroVO(rs.getInt(1), rs.getString(2), rs.getString(3), americanFormatter.parse(rs.getDate(4).toString()), rs.getInt(5)));
            }

            for (int i = 0; i < livroVOs.size(); i++) {
                procura[i][0] = String.valueOf(livroVOs.get(i).getId());
                procura[i][1] = String.valueOf(livroVOs.get(i).getTitulo());
                procura[i][2] = String.valueOf(livroVOs.get(i).getAutor());
                procura[i][3] = formatter.format(livroVOs.get(i).getDtPublicacao());
                procura[i][4] = String.valueOf(livroVOs.get(i).getQtdExemplares());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return procura;
    }

    /**
     * Busca um registro de livro no ArrayList
     * 
     * @param busca
     * 
     * @return Uma matriz com os valores buscados
     */
    public String[][] buscar(String busca) {

        String[][] procura = new String[256][5];

        try {
            String query = "SELECT * FROM Livros WHERE titulo LIKE '%" + busca + "%'";
            ResultSet rs = connect.prepareStatement(query).executeQuery();

            ArrayList<LivroVO> livroVOs = new ArrayList<>();

            SimpleDateFormat americanFormatter = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                livroVOs.add(new LivroVO(rs.getInt(1), rs.getString(2), rs.getString(3), americanFormatter.parse(rs.getDate(4).toString()), rs.getInt(5)));
            }

            for (int i = 0; i < livroVOs.size(); i++) {
                procura[i][0] = String.valueOf(livroVOs.get(i).getId());
                procura[i][1] = String.valueOf(livroVOs.get(i).getTitulo());
                procura[i][2] = String.valueOf(livroVOs.get(i).getAutor());
                procura[i][3] = formatter.format(livroVOs.get(i).getDtPublicacao());
                procura[i][4] = String.valueOf(livroVOs.get(i).getQtdExemplares());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
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

        try {
            String id_query = "SELECT id FROM Livros WHERE id = ?";
            PreparedStatement id_pstm = connect.prepareStatement(id_query);
            id_pstm.setInt(1, id);
            ResultSet id_rs = id_pstm.getResultSet();

            if (id_rs != null) {
                return "Já existe um livro cadastrado com esse ID@err";
            }
        } catch (SQLException e1) {
            return "Ocorreu um erro: "+ e1.getMessage() +"@err";
        }
        
        try {
            String query = "INSERT INTO Livros(id, titulo, autor, dtPublicacao, qtdExemplares) VALUES (?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?)";
            PreparedStatement pstm = connect.prepareStatement(query);

            pstm.setInt(1, id);
            pstm.setString(2, titulo);
            pstm.setString(3, autor);
            pstm.setString(4, data);
            pstm.setInt(5, qtdExemplares);

            pstm.executeUpdate();
        } catch (SQLException e) {
            return "Ocorreu um erro: "+ e.getMessage() +"@err";
        }
        
        return "Livro cadastrado com sucesso@suc";
    }

    /**
     * Verifica se existe um registro de Livro específico no ArrayList
     * 
     * @param id
     * @return
     */
    public Object[] verifica(int id) {
        
        try {
            String id_query = "SELECT * FROM Livros WHERE id = ?";
            PreparedStatement id_pstm = connect.prepareStatement(id_query);
            id_pstm.setInt(1, id);
            ResultSet id_rs = id_pstm.executeQuery();

            if (id_rs == null) {
                return null;
            } else {
                ArrayList<String> livroVOs = new ArrayList<>();

                if (id_rs.next()) {
                    livroVOs.add(String.valueOf(id_rs.getInt(1)));
                    livroVOs.add(id_rs.getString(2));
                    livroVOs.add(id_rs.getString(3));
                    livroVOs.add(String.valueOf(formatter.format(id_rs.getDate(4))));
                    livroVOs.add(String.valueOf(id_rs.getInt(5)));
                }

                return livroVOs.toArray();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    /**
     * Atualiza um objeto no ArrayList com base no seu índice e nas informações novas
     *  
     * @param i
     * @param object
     */
    public void update(int i, String[] object) {

        try {
            LivroVO livro = new LivroVO(i, object[0], object[1], formatter.parse(object[2]), Integer.parseInt(object[3]));

            String query = "UPDATE Livros SET titulo=?, autor=?, dtPublicacao=STR_TO_DATE(?, '%d/%m/%Y'), qtdExemplares=? WHERE id = ?";
            PreparedStatement pstm = connect.prepareStatement(query);

            pstm.setString(1, livro.getTitulo());
            pstm.setString(2, livro.getAutor());
            pstm.setString(3, String.valueOf(formatter.format(livro.getDtPublicacao()) ));
            pstm.setInt(4, livro.getQtdExemplares());
            pstm.setInt(5, i);

            pstm.executeUpdate();

        } catch (NumberFormatException | ParseException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Remove um objeto no ArrayList com base no seu índice
     * 
     * @param i
     */
    public void delete(int i) {

        try {
            
            String query = "DELETE FROM Livros WHERE id = ?";
            PreparedStatement pstm = connect.prepareStatement(query);
            pstm.setInt(1, i);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
