package me.alonzoh.ip;

import java.awt.Font;

public class FontLoader
{
    public static Font loadFont(String font, float size)
    {
        Font f = null;

        try
        {
            f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream(font)).deriveFont(size);
            System.out.println("Font loaded: " + font);
        } catch(Exception e) {
            System.out.println("Failed to load font: " + font);
        }

        return f;
    }

    public static Font size(Font f, float size)
    {
        return f.deriveFont(size);
    }
}