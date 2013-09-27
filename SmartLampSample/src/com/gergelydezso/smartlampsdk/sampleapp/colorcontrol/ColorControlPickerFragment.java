package com.gergelydezso.smartlampsdk.sampleapp.colorcontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.larswerkman.colorpicker .ColorPicker;
import com.larswerkman.colorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.colorpicker.OpacityBar;
import com.larswerkman.colorpicker.SaturationBar;
import com.larswerkman.colorpicker.ValueBar;

public class ColorControlPickerFragment extends Fragment {

  private ColorPicker picker;
  private static final String TAG = "ColorControlActivity";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_color_control_picker, container, false);

    picker = (ColorPicker) rootView.findViewById(R.id.picker);
    picker.setOnColorChangedListener(new OnColorChangedListener() {

      @Override
      public void onColorChanged(int color) {
        Log.d(TAG, "" + picker.getColor());
      }
    });

    OpacityBar opacityBar = (OpacityBar) rootView.findViewById(R.id.opacitybar);
    SaturationBar saturationBar = (SaturationBar) rootView.findViewById(R.id.saturationbar);
    ValueBar valueBar = (ValueBar) rootView.findViewById(R.id.valuebar);

    picker.addOpacityBar(opacityBar);
    picker.addSaturationBar(saturationBar);
    picker.addValueBar(valueBar);

    return rootView;

  }

}
