/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;


/**
 * class InputMoveState
 * handles the touch where the user is moving a vertex
 */
public class InputMoveState implements InputState {


    @Override
    public void handleTouch(View v, MotionEvent event, SketchViewController c) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                // changes back to the ready state, user is no longer drawing the raw path
                c.state = new InputReadyState();

                // smooth the raw path data and save it to the model
                c.interactionModel.rawPoints.addAll(c.rawPoints);
                c.interactionModel.smoothPath();
                c.sketchModel.addPath(c.interactionModel.smoothed);
                c.interactionModel.smoothed = new SketchPath();
                c.rawPoints.clear();
                break;

            case MotionEvent.ACTION_MOVE:
                // Draw the line as the user moves his finger, updating the raw path data

                c.rawPoints.add(new PointF( event.getX(), event.getY()));

                c.view.tempPath.addPoint(event.getX(), event.getY());
                c.view.tempPath.lineTo(event.getX(), event.getY());

                v.invalidate();
                break;
        }
    }
}
