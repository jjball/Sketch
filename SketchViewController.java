package com.jadenball.sketch;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by jadenball on 2017-03-08.
 */

public class SketchViewController implements View.OnTouchListener{

    /**
     * model for the sketch
     */
    SketchModel sketchModel;

    /**
     * model for the interactions with the sketch
     */
    InteractionModel interactionModel;

    /**
     * the view that will be drawn on
     */
    SketchView view;

    /**
     * a list of raw points from a drawing
     */
    LinkedList<PointF> rawPoints;

    /**
     * current state of the state machine
     */
    InputState state;

    /**
     * old position of the user's finger/touch
     */
    PointF oldPos;


    /**
     * initializes the controller
     */
    public SketchViewController(){
        rawPoints = new LinkedList<>();
        state = new InputReadyState();
        oldPos = new PointF();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        state.handleTouch(v, event, this);
        return true;
    }


    /**
     * sets the model for the controller
     * @param m the model
     */
    public void setModel(SketchModel m){
        sketchModel = m;
    }

    /**
     * sets the interaction model for the controller
     * @param model the model
     */
    public void setInteractionModel(InteractionModel model){
        interactionModel = model;
    }

    /**
     * sets the view for the controller
     * @param v the view
     */
    public void setView(SketchView v){
        view = v;
    }

    /**
     * Finds the path closest to the point
     * @param point a point on the view
     * @return true if the point is close to a path, false otherwise
     *   If returning true, the view's selected path becomes the closest path
     */
    public boolean findPath(PointF point){
        SketchPath path = interactionModel.findGivenPath(sketchModel.smoothPaths, point);

        if(path != null){
            view.selectedPath = path;
            return true;
        }
        else{
            return false;
        }
    }

}
