package me.alonzoh.ip;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class OverlayProcessor implements Processor
{
    float opacity;
    BufferedImage original;
    
    public OverlayProcessor(float opacity, BufferedImage original)
    {
        this.opacity = opacity;
        this.original = original;
    }
    
    public BufferedImage process(BufferedImage processed)
    {        
        Graphics2D graphics = (Graphics2D) processed.getGraphics();
        
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        
        graphics.drawImage(original, 0, 0, null);
        
        return processed;
    }
}