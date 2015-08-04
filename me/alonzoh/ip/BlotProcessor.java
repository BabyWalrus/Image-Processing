package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class BlotProcessor implements Processor
{
    int shape;
    int blotSize;
    boolean over;
    
    //Shape: 0-oval, 1-square
    public BlotProcessor(int shape, int blotSize, boolean over)
    {
        this.shape = shape;
        this.blotSize = blotSize;
        this.over = over;
    }
    
    public BufferedImage process(BufferedImage original)
    {
        int width = original.getWidth();
        int height = original.getHeight();
        
        BufferedImage end;
        
        if(over)
            end = original;
        else
            end = new BufferedImage(width, height, 2);
        
        Graphics graphics = end.getGraphics();
        
        for(int x = 0; x < width; x += blotSize)
        {
            for(int y = 0; y < height; y += blotSize)
            {
                //int color = 0;
                int r = 0;
                int g = 0;
                int b = 0;
                
                
                for(int sx = 0; sx < blotSize; sx++)
                {
                    for(int sy = 0; sy < blotSize; sy++)
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
                r /= Math.pow(blotSize, 2);
                b /= Math.pow(blotSize, 2);
                g /= Math.pow(blotSize, 2);
                
                graphics.setColor(new Color(r, g, b));
                
                if(shape == 0)
                    graphics.fillOval(x, y, blotSize, blotSize);
                else if(shape == 1)
                    graphics.fillRect(x, y, blotSize, blotSize);
            }
        }
        
        return end;
    }
}