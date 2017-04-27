package com.jadenball.sketch;

import java.util.LinkedList;

/**
 * Created by jadenball on 2017-03-08.
 */

/**
 * model for a Sketch
 */
public class SketchModel {

    /**
     * a list of all the subscribers to the model
     */
    LinkedList<SketchListener> subscribers;

    /**
     * a list of all the smoothpaths created
     */
    LinkedList<SketchPath> smoothPaths;

    /**
     * initializes the model
     */
    public SketchModel(){
        subscribers = new LinkedList<>();
        smoothPaths = new LinkedList<>();
    }

    /**
     * adds a subscriber to the model
     * @param newSubscriber the new subscriber to the model
     */
    public void addSubscriber(SketchListener newSubscriber){
        subscribers.add(newSubscriber);
    }

    /**
     * notifies all subscribers of a change
     */
    private void notifySubscribers(){
        for(SketchListener sl : subscribers){
            sl.modelChanged();
        }
    }

    /**
     * adds a path to the model
     * @param path the new path being added
     */
    public void addPath(SketchPath path){
        smoothPaths.add(path);
        notifySubscribers();
    }



}
