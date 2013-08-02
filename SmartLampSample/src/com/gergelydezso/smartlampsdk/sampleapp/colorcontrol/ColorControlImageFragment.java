package com.gergelydezso.smartlampsdk.sampleapp.colorcontrol;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.AllCommandConfiguration;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;

public class ColorControlImageFragment extends Fragment implements OnTouchListener, OnClickListener {

  private ImageView mImageColor;
  private Bitmap bitmap;
  private ColorPickerCustomView mColorPicker;
  @SuppressWarnings("unused")
  private int w, h;
  private SmartLampAPI mApi = null;

  // private HorizontalVariableListView mList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mApi = SmartLampAPIHolder.getApi();

    View rootView = inflater.inflate(R.layout.fragment_color_control_image, container, false);

    mImageColor = (ImageView) rootView.findViewById(R.id.imageView_color_image);
    mImageColor.setOnTouchListener(this);
    mImageColor.setOnClickListener(this);
    mColorPicker = (ColorPickerCustomView) rootView.findViewById(R.id.color_picker);
    mColorPicker.setVisibility(View.INVISIBLE);
    bitmap = ((BitmapDrawable) mImageColor.getDrawable()).getBitmap();

    w = bitmap.getWidth();
    h = bitmap.getHeight();

    // List<String> data = new ArrayList<String>();
    // for (int i = 0; i < 20; i++) {
    // data.add(String.valueOf(i));
    // }
    //
    // ListAdapter adapter = new HorizontalListAdapter(getActivity(), R.layout.view1, R.layout.divider, data);
    //
    // mList = (HorizontalVariableListView) rootView.findViewById(R.id.color_list);
    // mList.setSelectionMode(SelectionMode.Single);
    // mList.setOverScrollMode(HorizontalVariableListView.OVER_SCROLL_ALWAYS);
    // mList.setEdgeGravityY(Gravity.CENTER);
    // mList.setAdapter(adapter);
    // mList.setGravity(Gravity.CENTER);

    return rootView;

  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {

    if (mColorPicker.getVisibility() == View.INVISIBLE) {
      mColorPicker.setVisibility(View.VISIBLE);
    }

    int x = (int) event.getX();
    int y = (int) event.getY();

    int pixelColor = bitmap.getPixel(x, y);

    int redValue = Color.red(pixelColor);
    int greenValue = Color.green(pixelColor);
    int blueValue = Color.blue(pixelColor);
    int alpha = Color.alpha(pixelColor);

    switch (event.getAction()) {

    case MotionEvent.ACTION_MOVE:
      mColorPicker.setValues(x, y, pixelColor);

      break;
    case MotionEvent.ACTION_UP:

      // redValue = (int) (redValue * 0.7 * 1.5);
      // greenValue = (int) (greenValue * 1.2 * 1.5);
      // blueValue = (int) (blueValue * 1.5);

      mApi.setLedValue(redValue, blueValue, greenValue, new CommandCallback() {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onResult(String state) {
        }

        @Override
        public void onError() {
        }
      });
      break;
    case MotionEvent.ACTION_DOWN:
      mColorPicker.setValues(x, y, pixelColor);
      break;

    }

    return true;
  }

  @Override
  public void onClick(View v) {

  }

}
