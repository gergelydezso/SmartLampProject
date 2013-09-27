package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.gergelydezso.smartlampsdk.sampleapp.R;

public class MusicVisualizationFragment extends Fragment {

  private static final String TAG = "MusicVisualizationFrame";


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_music_visualization, container, false);

    return rootView;
  }


}
