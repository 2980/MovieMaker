/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javacv;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author unouser
 */
public class FadeToBlack implements Transition {
    
    float duration;

    FadeToBlack(float inDuration) {
        duration = inDuration;
    }

    public float getDuration() {
        return duration;
    }
    
    @Override
    public BufferedImage getTransitionImage(BufferedImage start, float timeElapsed)
    {
        BufferedImage toReturn = new BufferedImage(start.getWidth(), start.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        
        
        int[] rgbArray = new int[start.getWidth() * start.getHeight()];
        int[] outArray = new int[start.getWidth() * start.getHeight()];
        
        start.getRGB(0, 0, start.getWidth(), start.getHeight(), rgbArray, 0, start.getWidth() );
        
        
        float percentElapsed = timeElapsed / duration;
        
        for(int y = 0; y < start.getHeight(); y++)
        {
            for(int x = 0; x < start.getWidth(); x++)
            {
                 int index = y*start.getWidth() + x;
                int pixel = rgbArray[index]; 
                Color c = new Color(pixel);
                
                //Do the fade to black
                
                int r = (int) (c.getRed() * (1 - percentElapsed));
                int g = (int) (c.getGreen() * (1 - percentElapsed));
                int b = (int) (c.getBlue() * (1 - percentElapsed));
                
                Color outColor = new Color(r, g, b);
                outArray[index]  = outColor.getRGB();
            }
        }
        
        toReturn.setRGB(0, 0, toReturn.getWidth(), toReturn.getHeight(), outArray, 0, toReturn.getWidth());
            
        
        return toReturn;
    }
    
    
}
