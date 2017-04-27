/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;


import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * class InputSelectedState
 * handles the touch where the user has selected a vertex on the canvas
 */
public class InputSelectedState implements InputState {


    @Override
    public void handleTouch(View v, MotionEvent event, SketchViewController c) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    // get ready for the next action
                    c.state = new InputReadyState();
                    break;

                case MotionEvent.ACTION_MOVE:
                    // move the selected path and update the bounds and view
                    c.view.selectedPath.offset(event.getX() - c.oldPos.x, event.getY() - c.oldPos.y);
                    c.view.selectedPath.computeBounds(c.view.selectedPath.rect, true);
                    c.oldPos.set(event.getX(), event.getY());
                    v.invalidate();


                    break;
            }

    }
}
