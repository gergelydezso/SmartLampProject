package com.gergelydezso.smartlampsdk.sampleapp.colorcontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;

public class ColorControlImageFragment extends Fragment {

    private ColorPickerView mColorPicker;
    @SuppressWarnings("unused")
    private int w, h;
    private SmartLampAPI mApi = null;
    private LightView lightView;
    private TextView hexCodeColor;
    private LinearLayout linearLayoutColor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mApi = SmartLampAPIHolder.getApi();

        View rootView = inflater.inflate(R.layout.fragment_color_control_image, container, false);

        hexCodeColor = (TextView) rootView.findViewById(R.id.hex_color_label);
        linearLayoutColor = (LinearLayout) rootView.findViewById(R.id.color_layout);
        lightView = (LightView) rootView.findViewById(R.id.light_view);
        mColorPicker = (ColorPickerView) rootView.findViewById(R.id.color_picker);
        mColorPicker.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                int intColor = getColor();
                String strColor = Integer.toHexString(intColor);
                String hexCode = strColor.substring(2, strColor.length());
                hexCodeColor.setText(hexCode.toUpperCase());
                linearLayoutColor.setBackgroundColor(intColor);
                setColorLight(intColor);

                int pixelColor = mColorPicker.getColor();

                int redValue = Color.red(pixelColor);
                int greenValue = Color.green(pixelColor);
                int blueValue = Color.blue(pixelColor);

                if (mApi != null) {

                    mApi.setLedValue(redValue, blueValue, greenValue, new CommandCallback() {

                        @Override
                        public void onSuccess() {
                            Log.d("ColorControlImageFragment", "OnSuccess");
                        }

                        @Override
                        public void onResult(String state) {
                            Log.d("ColorControlImageFragment", "OnResult");
                        }

                        @Override
                        public void onError() {
                            Log.d("ColorControlImageFragment", "OnError");
                        }
                    });
                }
            }
        });

        return rootView;

    }

    public int getColor() {
        return mColorPicker.getColor();
    }

    public void setColorLight(int color) {
        lightView.setColor(color);
    }

}
