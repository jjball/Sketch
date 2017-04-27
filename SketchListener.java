package com.jadenball.sketch;

/**
 * Created by jadenball on 2017-03-08.
 */

/**
 * listener for a sketch
 */
public interface SketchListener {
    /**
     * called when the model is changed
     * updates the data to be to date with any changes
     */
    void modelChanged();
}
