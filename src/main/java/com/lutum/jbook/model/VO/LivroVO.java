package com.lutum.jbook.model.VO;

import java.util.Date;

public class LivroVO {
    
    private int    id;
    private String titulo;
    private String autor;
    private Date   dtPublicacao;
    private int    qtdExemplares;

    public LivroVO(int id, String titulo, String autor, Date dtPublicacao, int qtdExemplares) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dtPublicacao = dtPublicacao;
        this.qtdExemplares = qtdExemplares;
    }
    
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

    @Override
    public String toString() {
        return "LivroVO [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", dtPublicacao=" + dtPublicacao
                + ", qtdExemplares=" + qtdExemplares + "]";
    }

}
