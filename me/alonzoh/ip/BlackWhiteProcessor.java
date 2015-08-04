package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class BlackWhiteProcessor implements Processor
{ 
    int threshold;
    
    public BlackWhiteProcessor()
    {
        threshold = 123;
    }
    
    public BlackWhiteProcessor(int threshold)
    {
        this.threshold = threshold;
    }
    
    public BufferedImage process(BufferedImage original)
    {
        int width = original.getWidth();
        int height = original.getHeight();
        
        BufferedImage end = new BufferedImage(width, height, 2);
        
        Graphics graphics = end.getGraphics();
        
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                int color = original.getRGB(x, y);
                int r = (color & 0xFF0000) >> 16;
                int g = (color & 0xFF00) >> 8;
                int b = (color & 0xFF);
                int gray = (r + g + b) / 3;
                
                if(gray > threshold)
                    graphics.setColor(Color.white);
                else 
                    graphics.setColor(Color.BLACK);
                graphics.fillRect(x, y, 1, 1);
            }
        }
        
        return end;
    }
}