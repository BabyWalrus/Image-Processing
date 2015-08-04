package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class NoiseProcessor implements Processor
{
    int pointSize;
    
    
    public NoiseProcessor(int pointSize)
    {
        this.pointSize = pointSize;
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
                int color = 0;
                
                if(Math.round(Math.random()) == 0)
                    color = original.getRGB(x, y);
                else
                    color = end.getRGB(x, y);
                
                graphics.setColor(new Color(color));
                graphics.drawRect(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
            }
        }
        
        return end;
    }
}