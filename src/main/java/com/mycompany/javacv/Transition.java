/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javacv;

import java.awt.image.BufferedImage;

/**
 *
 * @author unouser
 */
interface Transition {
    
    float getDuration();
    
    BufferedImage getTransitionImage(BufferedImage start, float timeElapsed);
    
}
