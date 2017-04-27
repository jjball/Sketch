/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;


/**
 * class InputReadyState
 * handles the touch where the user is just placing their finger on the canvas
 */
public class InputReadyState implements InputState {

    @Override
    public void handleTouch(View v, MotionEvent event, SketchViewController c) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // user has tapped on a path/drawing, select it

                PointF handle = null;
                if(c.view.selectedPath != null) {
                    handle = new PointF(c.view.selectedPath.rect.right, c.view.selectedPath.rect.bottom);
                }

                // user is selecting a handle on a selected sketchpath object
                if (handle != null && event.getX() < handle.x + 20 && event.getX() > handle.x - 20 &&
                            event.getY() < handle.y + 20 && event.getY() > handle.y - 20) {
                        c.state = new InputResizingState();
                }
                else if(c.findPath(new PointF(event.getX(), event.getY()))){
                    // user is selecting a sketchpath object
                    c.state = new InputSelectedState();
                    c.oldPos.set(event.getX(), event.getY());
                }
                else {
                    // event is on the background, prepare for drawing a path
                    c.view.selectedPath = null;
                    c.state = new InputPrepareState();
                }
                v.invalidate();
                break;
        }
    }
}

