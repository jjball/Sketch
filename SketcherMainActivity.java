/*
 * Jaden Ball
 * CMPT381
 */

package com.jadenball.sketch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class SketcherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sketcher_main);


        // Set up MVC
        SketchView sketchView = new SketchView(this);
        SketchViewController controller = new SketchViewController();
        SketchModel model = new SketchModel();
        InteractionModel interactionModel = new InteractionModel();
        SeekBar slider = new SeekBar(this);
        slider.setMax(9);

        // connections between M, V, C
        sketchView.setModel(model);
        controller.setModel(model);
        controller.setInteractionModel(interactionModel);
        controller.setView(sketchView);
        model.addSubscriber(sketchView);

        // event handling
        sketchView.setOnTouchListener(controller);
        slider.setOnSeekBarChangeListener(interactionModel);

        // layout
        LinearLayout vbox = new LinearLayout(this);
        vbox.setOrientation(LinearLayout.VERTICAL);
        vbox.addView(slider);
        vbox.addView(sketchView);
        setContentView(vbox);
    }
}
