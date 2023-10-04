package com.lutum.jbook.view.utils;

import java.awt.Font;

/**
 * @category View
 * 
 * Gerencia as fontes do View
 */
public class FontManager {
    
    // Atributos da Classe
    private Font title;
    private Font subtitle;
    private Font monospaced;

    /**
     * Construtor da Classe FontManager
     */
    public FontManager() {
        
        title = new Font("title", Font.BOLD, 48);
        subtitle = new Font("subtitle", Font.BOLD, 10);
        monospaced = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    }

    // Getters
    public Font getMonospaced() {
        return monospaced;
    }

    public Font getSubtitle() {
        return subtitle;
    }

    public Font getTitle() {
        return title;
    }

}
