package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Red_dino
 */
public class PointProcessor implements Processor
{
    int pointSize;
    
    int iterations;
    
    public PointProcessor(int pointSize, int iterations)
    {
        this.pointSize = pointSize;
        
        this.iterations = iterations;
    }
    
    public BufferedImage process(BufferedImage original)
    {
        int width = original.getWidth();
        int height = original.getHeight();
        
        BufferedImage end = new BufferedImage(width, height, 2);
        
        Graphics graphics = end.getGraphics();
        
        
        for(int i = 0; i < iterations; i++)
        {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            
            graphics.setColor(new Color(original.getRGB(x, y)));
            graphics.fillOval(x, y, pointSize, pointSize);
        }
        
        return end;
    }
}