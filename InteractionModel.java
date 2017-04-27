package com.jadenball.sketch;


import android.graphics.PointF;
import android.widget.SeekBar;

import java.util.LinkedList;

/**
 * Created by jadenball on 2017-03-08.
 */

public class InteractionModel implements SeekBar.OnSeekBarChangeListener{

    /**
     * raw data points for a path
     */
    LinkedList<PointF> rawPoints;

    /**
     * thinned data points for a path
     */
    LinkedList<PointF> thinnedPoints;

    /**
     * a smoothed sketchpath
     */
    SketchPath smoothed;

    /**
     * the amount of thinning to apply to the raw data
     */
    int thinning;

    /**
     * initializes the interaction model
     */
    public InteractionModel(){
        rawPoints = new LinkedList<>();
        smoothed = new SketchPath();
        thinnedPoints = new LinkedList<>();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        thinning = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * smooths the given SketchPath using the thinning setting on the SeekBar
     */
    public void smoothPath(){
        int amountOfPoints = rawPoints.size();


        // putting all the thinned points inside of a list named thinnedPoints
        int i = 0;
        while(i < amountOfPoints){

            thinnedPoints.add(rawPoints.get(i));


            if( (i != amountOfPoints - 1) && ((i + thinning + 1) >= amountOfPoints) ){
                i = amountOfPoints - 1;
            }
            else{
                i += thinning + 1;
            }
        }

        // putting all the thinned points as a quadratic curve inside a new SketchPath named smoothed
        int thinnedSize = thinnedPoints.size() - 1; // -1 so the last p2 becomes the last point
        smoothed.moveTo(thinnedPoints.get(0).x, thinnedPoints.get(0).y);
        for(int j = 0; j < thinnedSize; j++){
            PointF p1 = thinnedPoints.get(j);
            PointF p2 = thinnedPoints.get(j+1);
            float midX = (p1.x + p2.x) / 2;
            float midY= (p1.y + p2.y) / 2;

            smoothed.quadTo(p1.x, p1.y, midX, midY);
        }

        // refresh the data
        rawPoints.clear();
        thinnedPoints.clear();
    }


    /**
     * finds a path given in the list where the point is situated
     * @param paths a list of paths to look through
     * @param point the point to see if it is close to any path
     * @return a SketchPath that occupies the area the point is in, null if there is none
     */
    public SketchPath findGivenPath(LinkedList<SketchPath> paths, PointF point){
        for(SketchPath p : paths){
            if(p.inRegion(point)){
                return p;
            }
        }

        return null;
    }

}
