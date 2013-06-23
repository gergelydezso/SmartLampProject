package com.gergelydezso.smartlampsdk.sampleapp;

import it.sephiroth.android.library.widget.HorizontalVariableListView;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HorizontalListAdapter extends ArrayAdapter<String> {

  int resId1;
  int resId2;

  public HorizontalListAdapter(Context context, int textViewResourceId, int dividerResourceId, List<String> objects) {
    super(context, textViewResourceId, objects);
    resId1 = textViewResourceId;
    resId2 = dividerResourceId;
  }

  @Override
  public int getItemViewType(int position) {
    if (getViewTypeCount() > 1) {
      if (position % 4 == 1) {
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    View view;
    int type = getItemViewType(position);

    if (convertView == null) {
      view = LayoutInflater.from(getContext()).inflate(type == 0 ? resId1 : resId2, parent, false);
      view.setLayoutParams(new HorizontalVariableListView.LayoutParams(
          HorizontalVariableListView.LayoutParams.WRAP_CONTENT, HorizontalVariableListView.LayoutParams.WRAP_CONTENT));
    }
    else {
      view = convertView;
    }

    if (type == 0) {

    }

    return view;
  }

}