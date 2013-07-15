package com.gergelydezso.smartlampsdk.sampleapp.motioncontrol;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.gergelydezso.smartlampsdk.ServoMotorEntities;
import com.gergelydezso.smartlampsdk.api.SmartLampAPI;
import com.gergelydezso.smartlampsdk.command.CommandCallback;
import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.SmartLampAPIHolder;

public class MotionFragment extends Fragment implements OnClickListener, OnSeekBarChangeListener {

  private final static String TAG = "MotionFragment";
  private LampMotion mLampMotion;
  @SuppressWarnings("unused")
  private Button mButtonSavePosition;
  private ImageView mImageBitmap;
  public ImageView mImageCoordonate;
  // private HorizontalVariableListView mList;
  private SeekBar mSeekBarPart0;
  private SeekBar mSeekBarPart3;
  private SeekBar mSeekBarPart4;
  private TextView mTextPart0;
  private TextView mTextPart4;
  @SuppressWarnings("unused")
  private RelativeLayout mRelativLayoutContainer;
  private SmartLampAPI mApi;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mApi = SmartLampAPIHolder.getApi();

    View rootView = inflater.inflate(R.layout.fragment_motion_control, container, false);

    mRelativLayoutContainer = (RelativeLayout) rootView.findViewById(R.id.custom_view_container);
    mTextPart0 = (TextView) rootView.findViewById(R.id.textView_part0);
    mTextPart4 = (TextView) rootView.findViewById(R.id.textView_part4);
    mImageCoordonate = (ImageView) rootView.findViewById(R.id.imageView_coordinate);
    // mImageBitmap = (ImageView) rootView.findViewById(R.id.bitmap_image2);
    mLampMotion = (LampMotion) rootView.findViewById(R.id.lamp_motion);
    // mButtonSavePosition = (Button)
    // rootView.findViewById(R.id.button_saveposition);
    // mButtonSavePosition.setOnClickListener(this);
    mSeekBarPart0 = (SeekBar) rootView.findViewById(R.id.seekBar_part0);
    mSeekBarPart3 = (SeekBar) rootView.findViewById(R.id.seekBar_part3);
    mSeekBarPart4 = (SeekBar) rootView.findViewById(R.id.seekBar_part4);
    mSeekBarPart0.setOnSeekBarChangeListener(this);
    mSeekBarPart3.setOnSeekBarChangeListener(this);
    mSeekBarPart4.setOnSeekBarChangeListener(this);

    mLampMotion.setOwnerObejct(this);

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
    //
    // mList.setOnLayoutChangeListener(new OnLayoutChangeListener() {
    //
    // @Override
    // public void onLayoutChange(boolean changed, int left, int top, int
    // right, int bottom) {
    // Log.d("MotionFragment", "onLayoutChange: " + changed + ", " + bottom
    // + ", " + top);
    // if (changed) {
    // mList.setEdgeHeight(bottom - top);
    // }
    // }
    // });
    //
    // mList.setOnItemClickedListener(new OnItemClickedListener() {
    //
    // @Override
    // public boolean onItemClick(AdapterView<?> parent, View view, int
    // position, long id) {
    // Log.i("MotionFragment", "onItemClick: " + position);
    //
    // // item has been clicked, return true if you want the
    // // HorizontalVariableList to handle the event
    // // false otherwise
    // return true;
    // }
    // });
    //
    // mList.setOnItemSelectedListener(new OnItemSelectedListener() {
    //
    // @Override
    // public void onItemSelected(AdapterView<?> parent, View view, int
    // position, long id) {
    // // mText.setText("item selected: " + position + ", selected items: "
    // + mList.getSelectedPositions().length);
    // }
    //
    // @Override
    // public void onNothingSelected(android.widget.AdapterView<?> parent) {
    // // mText.setText("nothing selected");
    // };
    //
    // });

    return rootView;
  }

  @Override
  public void onClick(View v) {
    Log.d(TAG, "onClick");
    // saveViewToBitmap();
  }

  public void saveViewToBitmap() {

    // Bitmap b = Bitmap.createBitmap(mRelativLayoutContainer.getWidth(),
    // mRelativLayoutContainer.getHeight(),
    // Bitmap.Config.ARGB_8888);
    // Canvas c = new Canvas(b);
    // mRelativLayoutContainer.draw(c);
    // BitmapDrawable d = new BitmapDrawable(getResources(), b);
    // canvasView.setBackgroundDrawable(d);

    Bitmap resized = Bitmap.createScaledBitmap(mLampMotion.getDrawingCache(), 240, 426, true);
    mImageBitmap.setImageBitmap(resized);
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    switch (seekBar.getId()) {
    case R.id.seekBar_part0:
      mLampMotion.setActivePart(0);
      mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_y);
      // mTextPart0.setText("lamp rotate by Y (" + progress + ")");

      mApi.setServoPosition(ServoMotorEntities.SERVO1, progress, new CommandCallback() {

        @Override
        public void onSuccess() {
          // TODO Auto-generated method stub

        }

        @Override
        public void onResult(String state) {
          // TODO Auto-generated method stub

        }

        @Override
        public void onError() {
          // TODO Auto-generated method stub

        }
      });

      break;
    case R.id.seekBar_part4:
      mLampMotion.setActivePart(4);
      mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_x);
      // mTextPart4.setText("lamp rotate by X (" + progress + ")");

      mApi.setServoPosition(ServoMotorEntities.SERVO5, progress, new CommandCallback() {

        @Override
        public void onSuccess() {
          // TODO Auto-generated method stub

        }

        @Override
        public void onResult(String state) {
          // TODO Auto-generated method stub

        }

        @Override
        public void onError() {
          // TODO Auto-generated method stub

        }
      });

      break;
    case R.id.seekBar_part3:
      // mLampMotion.setActivePart(4);
      // mImageCoordonate.setImageResource(R.drawable.coordinate_syztem_x);
      // mTextPart4.setText("lamp rotate by X (" + progress + ")");

      mApi.setServoPosition(ServoMotorEntities.SERVO1, progress, new CommandCallback() {

        @Override
        public void onSuccess() {
          // TODO Auto-generated method stub

        }

        @Override
        public void onResult(String state) {
          // TODO Auto-generated method stub

        }

        @Override
        public void onError() {
          // TODO Auto-generated method stub

        }
      });

      break;
    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
  }

}
