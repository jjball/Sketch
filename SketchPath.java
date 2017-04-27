package com.jadenball.sketch;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;

import java.util.LinkedList;

/**
 * Created by jadenball on 2017-03-08.
 */

/**
 * an object to resemble a sketchpath on a view
 */
public class SketchPath extends Path {

    /**
     * a list of the path's points
     */
    LinkedList<PointF> points;

    /**
     * the region in which the path occupies
     */
    Region region;

    /**
     * a rectangle resembling the region around the path
     */
    RectF rect;


    /**
     * initializes the sketch path
     */
    public SketchPath(){
        super();
        points = new LinkedList<>();
        region = new Region();
        rect = new RectF();
    }


    /**
     * adds a point at the given coords to the path
     * @param x the x coord
     * @param y the y coord
     */
    public void addPoint(float x, float y){
        points.add(new PointF(x, y));
    }


    /**
     * checks if the given point is in the region that the path occupies
     * @param point the given point
     * @return true if the point is in the region the path occupies, false otherwise
     */
    public boolean inRegion(PointF point){
        computeBounds(rect, true);
        region.setPath(this, new Region((int) rect.left, (int) rect.top, (int) rect.right, (int) rect.bottom));
        if(region.contains((int) point.x, (int) point.y)){
            return true;
        }
        else{
            return false;
        }
    }





}
