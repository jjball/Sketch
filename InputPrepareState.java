/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;


import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * class InputPrepareState
 * handles the touch where the user has touched an empty background
 */
public class InputPrepareState implements InputState {

    @Override
    public void handleTouch(View v, MotionEvent event, SketchViewController c) {

        switch(event.getAction()){
            // released touch on the view
            // check if user is selecting a sketchpath (assignment pt2)
            case MotionEvent.ACTION_UP:
                c.state = new InputReadyState();
                break;

            case MotionEvent.ACTION_MOVE:
                // user is dragging their finger across the canvas, drawing a path
                c.view.tempPath = new SketchPath();
                c.view.tempPath.moveTo(event.getX(),event.getY());

                c.rawPoints.add(new PointF(event.getX(), event.getY()));

                c.state = new InputMoveState();
                break;


        }


    }
}
