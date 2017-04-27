/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;


import android.view.MotionEvent;
import android.view.View;


/**
 * An interface for the state of input on the screen.
 * Is used for making a state machine
 */
public interface InputState {

    /**
     * handles the touch motion event on the graph view controller c
     * @param c the GraphViewController
     * @param event the MotionEvent
     */
    public void handleTouch(View v, MotionEvent event, SketchViewController c);
}
