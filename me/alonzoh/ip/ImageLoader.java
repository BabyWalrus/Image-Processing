package me.alonzoh.ip;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader
{
    public static BufferedImage loadImage(String name)
    {
        try {
            BufferedImage bi = ImageIO.read(ImageLoader.class.getResourceAsStream(name));
            
            System.out.println("Image loaded: " + bi);
            
            return bi;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}