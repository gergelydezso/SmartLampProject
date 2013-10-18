package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.gergelydezso.smartlampsdk.sampleapp.R;

public class MusicVisualizationFragment extends Fragment {

  private static final String TAG = "MusicVisualizationFrame";

  private ImageView circle;
  private int globalDegree;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_music_visualization, container, false);

    circle = (ImageView) rootView.findViewById(R.id.circle);
    circle.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();


        double radian = Math.atan2(x - 720 / 2, 1280 / 2 - y);
        globalDegree = (int) Math.toDegrees(radian);

        int degree = 0;


        if (globalDegree >= 0) {
          degree = globalDegree;
        }
        else {
          degree = 360 + globalDegree;
        }

        circle.setRotation(degree);

        Log.d("Test", "angle: " + degree);

        return true;
      }
    });

    return rootView;
  }


}
