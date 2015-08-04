package me.alonzoh.ip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Red_dino
 */
public class NothingProcessor implements Processor
{
    public NothingProcessor()
    {
    }
    
    public BufferedImage process(BufferedImage original)
    {
        return original;
    }
}