package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class HardEdgeProcessor implements Processor
{
    int edgeSize;
    int sampleDistance;
    int hardDistance;
    
    public HardEdgeProcessor(int edgeSize, int sampleDistance, int hardDistance)
    {
        this.edgeSize = edgeSize;
        this.sampleDistance = sampleDistance;
        this.hardDistance = hardDistance;
    }
    
    public BufferedImage process(BufferedImage original)
    {
        int width = original.getWidth();
        int height = original.getHeight();
        
        BufferedImage end = new BufferedImage(width, height, 2);
        
        Graphics graphics = end.getGraphics();
        //graphics.drawImage(original, 0, 0, null);
        graphics.setColor(Color.BLACK);
        
        for(int x = sampleDistance; x < width - sampleDistance; x++)
        {
            for(int y = sampleDistance; y < height - sampleDistance; y++)
            {
                int color = original.getRGB(x, y);
                int rM = ((color & 0xFF0000) >> 16);
                int gM = ((color & 0xFF00) >> 8);
                int bM = (color & 0xFF);
                
                int colorLeft = original.getRGB(x - sampleDistance, y);
                int rL = ((colorLeft & 0xFF0000) >> 16);
                int gL = ((colorLeft & 0xFF00) >> 8);
                int bL = (colorLeft & 0xFF);
                
                int rightColor = original.getRGB(x + sampleDistance, y);
                int rR = ((rightColor & 0xFF0000) >> 16);
                int gR = ((rightColor & 0xFF00) >> 8);
                int bR = (rightColor & 0xFF);
                
                int upColor = original.getRGB(x, y - sampleDistance);
                int rU = ((upColor & 0xFF0000) >> 16);
                int gU = ((upColor & 0xFF00) >> 8);
                int bU = (upColor & 0xFF);
                
                int downColor = original.getRGB(x, y + sampleDistance);
                int rD = ((downColor & 0xFF0000) >> 16);
                int gD = ((downColor & 0xFF00) >> 8);
                int bD = (downColor & 0xFF);
                
                double uD = colorDistance(rM, gM, bM, rD, gD, bD);
                double uU = colorDistance(rM, gM, bM, rU, gU, bU);
                double uL = colorDistance(rM, gM, bM, rL, gL, bL);
                double uR = colorDistance(rM, gM, bM, rR, gR, bR);
                
                if(uD > hardDistance || uU  > hardDistance|| uL > hardDistance || uR > hardDistance)
                {
                    graphics.fillOval(x, y, edgeSize, edgeSize);
                }
            }
        }
        
        return end;
    }
    
    private double colorDistance(int r1, int g1, int b1, int r2, int g2, int b2)
    {
        long inside = (r2 - r1) ^ 2 + (g2 - g1) ^ 2 + (b2 - b1) ^ 2;
        
        return Math.sqrt(inside);
    }
}