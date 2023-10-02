package com.lutum.jbook.view.utils;

import java.awt.Font;

public class FontManager {
    
    private Font title;
    private Font subtitle;
    private Font monospaced;

    public FontManager() {
        
        title = new Font("title", Font.BOLD, 48);
        subtitle = new Font("subtitle", Font.BOLD, 10);
        monospaced = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    }

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
