package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.gergelydezso.smartlampsdk.sampleapp.R;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.playlist.PlayListItem;
import com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.playlist.PlaylistActivity;

import java.io.IOException;

public class MusicVisualizationFragment extends Fragment {
    private static final String TAG = MusicVisualizationFragment.class.getSimpleName();

    private Button startBtn;
    private Button stopBtn;
    private MediaPlayer mediaPlayer;
    private VisualizerView visualizerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music_visualization, container, false);

        startBtn = (Button) rootView.findViewById(R.id.start_music_btn);
        stopBtn = (Button) rootView.findViewById(R.id.stop_music_btn);
        visualizerView = (VisualizerView) rootView.findViewById(R.id.visualizerView);

        mediaPlayer = createMediaPlayer();
        initListeners();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            PlayListItem playListItem = data.getParcelableExtra(PlayListItem.ID);
            if (playListItem != null) {
                Uri uri = Uri.parse(playListItem.getUri());
                //mediaPlayer = createMediaPlayer(uri);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlaying();
    }

    private void initListeners() {

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlaying();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });
    }

    private void startPlaying() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();

            visualizerView.init(mediaPlayer);
            visualizerView.startVisualization();
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            visualizerView.stopVisualization();
            mediaPlayer.setLooping(false);
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MediaPlayer createMediaPlayer() {
        Uri uri2 = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.ilike);
        return MediaPlayer.create(getActivity(), uri2);
    }
}
