/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javacv;

/**
 *
 * @author unouser
 */
public class Segment {

    private String filename;
    private float duration;
    private Transition transition;
    
    public Segment(String inFilename, float inDuration, Transition inTransition)
    {
        filename = inFilename;
        duration = inDuration;
        transition = inTransition;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the duration
     */
    public float getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * @return the transition
     */
    public Transition getTransition() {
        return transition;
    }

    /**
     * @param transition the transition to set
     */
    public void setTransition(Transition transition) {
        this.transition = transition;
    }

}
