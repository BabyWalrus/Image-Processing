package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class NegativeProcessor implements Processor
{
    boolean ir;
    boolean ig;
    boolean ib;
    
    public NegativeProcessor()
    {
        ir = ig = ib = true;
    }
    
    public NegativeProcessor(boolean ir, boolean ig, boolean ib)
    {
        this.ir = ir;
        this.ig = ig;
        this.ib = ib;
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
                int r = ((color & 0xFF0000) >> 16);
                int g = ((color & 0xFF00) >> 8);
                int b = (color & 0xFF);
                
                if(ir)
                {
                    r = 255 - r;
                }
                if(ig)
                {
                    g = 255 - g;
                }
                if(ib)
                {
                    b = 255 - b;
                }
                
                graphics.setColor(new Color(r, g, b));
                graphics.fillRect(x, y, 1, 1);
            }
        }
        
        return end;
    }
}