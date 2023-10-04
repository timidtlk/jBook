package com.lutum.jbook.model.VO;

import java.util.Date;

/**
 * @category Model
 * 
 * View Object do Livro, é o objeto utilizado no CRUD
 */
public class LivroVO {
    
    // Atributos da Classe
    private int    id;
    private String titulo;
    private String autor;
    private Date   dtPublicacao;
    private int    qtdExemplares;

    /**
     * Construtor da Classe LivroVO
     * 
     * @param id
     * @param titulo
     * @param autor
     * @param dtPublicacao
     * @param qtdExemplares
     */
    public LivroVO(int id, String titulo, String autor, Date dtPublicacao, int qtdExemplares) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dtPublicacao = dtPublicacao;
        this.qtdExemplares = qtdExemplares;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Date getDtPublicacao() {
        return dtPublicacao;
    }
    public void setDtPublicacao(Date dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }
    public int getQtdExemplares() {
        return qtdExemplares;
    }
    public void setQtdExemplares(int qtdExemplares) {
        this.qtdExemplares = qtdExemplares;
    }

    /**
     * @implNote Método toString do LivroVO, serve somente para testes, por isso está marcado como Deprecated
     * 
     * @deprecated
     */
    @Override
    public String toString() {
        return "LivroVO [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", dtPublicacao=" + dtPublicacao
                + ", qtdExemplares=" + qtdExemplares + "]";
    }

}
