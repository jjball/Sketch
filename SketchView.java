package com.jadenball.sketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by jadenball on 2017-03-08.
 */

public class SketchView extends View implements SketchListener{

    /**
     * the model for the view
     */
    SketchModel model;

    /**
     * the paint in which to draw from
     */
    Paint myPaint;

    /**
     * temp path for when a user is currently drawing
     */
    SketchPath tempPath;

    /**
     * the selected path for when a user selects a path
     */
    SketchPath selectedPath;

    /**
     * Initializes the view with the given context
     * @param context the context
     */
    public SketchView(Context context) {
        super(context);
        myPaint = new Paint();
        this.setBackgroundColor(Color.GRAY);
    }

    /**
     * Sets the model for the view to follow
     * @param m the model
     */
    public void setModel(SketchModel m){
        model = m;
    }





    @Override
    public void onDraw(Canvas c){
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(10);


        for(SketchPath path : model.smoothPaths){
            if(path == selectedPath) {
                // draw the currently selected sketchpath object
                myPaint.setColor(Color.BLUE);
                c.drawPath(path, myPaint);
                myPaint.setColor(Color.BLACK);
                myPaint.setStrokeWidth(2);
                c.drawRect(path.rect, myPaint);
                myPaint.setStrokeWidth(20);
                c.drawCircle(path.rect.right, path.rect.bottom, 10, myPaint);
                myPaint.setStrokeWidth(10);
            }
            else{
                // draw all unselected paths
                myPaint.setColor(Color.RED);
                c.drawPath(path, myPaint);
            }
        }

        // draw the path that is currently being drawn by the user in gray
        if(tempPath != null){
            myPaint.setColor(Color.DKGRAY);
            c.drawPath(tempPath, myPaint);
        }
    }

    @Override
    public void modelChanged() {
        invalidate();
    }
}
