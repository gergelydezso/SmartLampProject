package com.gergelydezso.smartlampsdk.sampleapp.alarmclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gergelydezso.smartlampsdk.sampleapp.R;

public class AlarmClockFragment extends Fragment {

  // private HorizontalVariableListView mList;

  @SuppressWarnings("unused")
  private static final String TAG = "AlarmClock";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_alarm_clock, container, false);

    // List<String> data = new ArrayList<String>();
    // for (int i = 0; i < 5; i++) {
    // data.add(String.valueOf(i));
    // }
    //
    // HorizontalListAdapter adapter = new
    // HorizontalListAdapter(getActivity(), R.layout.view1,
    // R.layout.divider, data);
    //
    // mList = (HorizontalVariableListView)
    // rootView.findViewById(R.id.list);
    // mList.setSelectionMode(SelectionMode.Single);
    // mList.setOverScrollMode(HorizontalVariableListView.OVER_SCROLL_ALWAYS);
    // mList.setEdgeGravityY(Gravity.CENTER);
    // mList.setAdapter(adapter);
    // mList.setGravity(Gravity.CENTER);

    return rootView;

  }

}
