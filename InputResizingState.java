package com.jadenball.sketch;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jadenball on 2017-03-10.
 */

/**
 * State for resizing the selected sketchpath object
 */
public class InputResizingState implements InputState {
    @Override
    public void handleTouch(View v, MotionEvent event, SketchViewController c) {

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                // user is done resizing for now, go back to ready state
                c.state = new InputReadyState();
                break;
            case MotionEvent.ACTION_MOVE:
                // user is resizing the Sketchpath object
                SketchPath resizingPath = new SketchPath();
                resizingPath.addPath(c.view.selectedPath);

                // set up the amount we transform by
                float xScale = (event.getX() - c.view.selectedPath.rect.left) / (c.view.selectedPath.rect.width());
                float yScale = (event.getY() - c.view.selectedPath.rect.top) / (c.view.selectedPath.rect.height());
                Matrix m = new Matrix();
                m.setScale(xScale, yScale, c.view.selectedPath.rect.left, c.view.selectedPath.rect.top);

                // transform a new Sketchpath object so nothing odd occurs
                resizingPath.set(c.view.selectedPath);
                resizingPath.transform(m);

                c.view.selectedPath.reset();
                c.view.selectedPath.rewind();
                c.view.selectedPath.set(resizingPath);

                // update the bounds of the path and update the view
                c.view.selectedPath.computeBounds(c.view.selectedPath.rect, true);
                v.invalidate();
                break;
        }



    }
}
