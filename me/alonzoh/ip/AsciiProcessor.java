package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontMetrics;

public class AsciiProcessor implements Processor
{
    float fontSize;
    boolean negative;
    Color background;
    
    public AsciiProcessor()
    {
        this(10, false, Color.BLACK);
    }
    
    public AsciiProcessor(int fontSize, boolean negative, Color background)
    {
        this.fontSize = fontSize;
        this.negative = negative;
        this.background = background;
    }
    
    public BufferedImage process(BufferedImage original)
    {
        int width = original.getWidth();
        int height = original.getHeight();
        
        BufferedImage end = new BufferedImage(width, height, 2);
        
        Graphics graphics = end.getGraphics();
        
        graphics.setColor(background);
        graphics.fillRect(0, 0, width, height);
        
        Font font = FontLoader.loadFont("font_1.ttf", fontSize);
        graphics.setFont(font);
        FontMetrics fm = graphics.getFontMetrics();
        int gridWidth = fm.charWidth('@') + 1;
        int gridHeight = fm.getHeight() + 1;
        
        for(int x = 1; x < width; x += gridWidth)
        {
            for(int y = gridHeight; y < height; y += gridHeight)
            {
                int r = 0;
                int g = 0;
                int b = 0;
                
                for(int sx = 0; sx < gridWidth; sx++)
                {
                    for(int sy = 0; sy < gridHeight; sy++)
                    {
                        if(x + sx < width && y + sy < height)
                        {
                            int color = original.getRGB(x + sx, y + sy);
                            //color += original.getRGB(x + sx, y + sy);
                            r += (color & 0xFF0000) >> 16;
                            g += (color & 0xFF00) >> 8;
                            b += (color & 0xFF);
                        }
                        //System.out.println(color);
                    }
                }
                r /= gridHeight*gridWidth;
                b /= gridHeight*gridWidth;
                g /= gridHeight*gridWidth;
                
                String character = getChar(r, g, b);
                graphics.setColor(new Color(r, g, b));
                graphics.drawString(character, x, y);
            }
        }
        
        return end;
    }
    
    private String getChar(int r, int g, int b)
    {
        float gray = (r+g+b)/3;
        
        final String str;
 
	if (gray >= 230.0) {
            str = " ";
	} else if (gray >= 200.0) {
            str = ".";
	} else if (gray >= 180.0) {
            str = "*";
	} else if (gray >= 160.0) {
            str = ";";
	} else if (gray >= 130.0) {
            str = "o";
	} else if (gray >= 100.0) {
            str = "&";
	} else if (gray >= 70.0) {
            str = "8";
	} else if (gray >= 50.0) {
            str = "#";
	} else {
            str = "@";
	}
	return str;
    }
}