package main;

import java.awt.Font;

public class FontManager {
    
    private Font title;
    private Font subtitle;

    public FontManager() {
        
        title = new Font("title", Font.BOLD, 48);
        subtitle = new Font("subtitle", Font.BOLD, 10);

    }

    public Font getSubtitle() {
        return subtitle;
    }

    public Font getTitle() {
        return title;
    }

}
