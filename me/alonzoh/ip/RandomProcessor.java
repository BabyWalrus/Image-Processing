package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class RandomProcessor implements Processor
{
    int size;
    int shape;
    double frequency;
    
    public final double DEFAULT_FREQUENCY = 1.5;
    
    public RandomProcessor(int size)
    {
        this.size = size;
        this.shape = 1;
        this.frequency = DEFAULT_FREQUENCY;
    }
    
    public RandomProcessor(int size, int shape, double frequency)
    {
        this.size = size;
        this.shape = shape;
        this.frequency = frequency;
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
                if(Math.random() * size < frequency)
                {
                    graphics.setColor(new Color(original.getRGB(x, y)));
                    
                    if(shape == 0)
                        graphics.fillRect(x - size / 2, y - size / 2, size, size);
                    else if(shape == 1)
                        graphics.fillOval(x - size / 2, y - size / 2, size, size);
                }
            }
        }
        
        return end;
    }
}