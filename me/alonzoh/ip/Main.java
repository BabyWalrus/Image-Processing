package me.alonzoh.ip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main
{
    public static void main(String args[])
    {                
        BufferedImage image = ImageLoader.loadImage("dragon.jpg");
        
        Processor processor = new BinaryProcessor();
        
        BufferedImage pi = processor.process(image);
        
        try {
            File outputfile = new File("dragon_ascii.png");
            ImageIO.write(pi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        ImageIcon processedImage = new ImageIcon(pi);
        
        JLabel label = new JLabel(processedImage);
        
        JFrame frame = new JFrame("Processed Image");
        frame.setIconImage(pi);
        frame.setDefaultCloseOperation(3);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
//new HardEdgeProcessor(7, 5, 14);