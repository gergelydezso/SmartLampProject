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

public class MusicVisualizationFragment extends Fragment {

    private static final String TAG = MusicVisualizationFragment.class.getSimpleName();
    private Button         startBtn;
    private Button         stopBtn;
    private Button         pauseBtn;
    private Button         playListDisplayBtn;
    private MediaPlayer    mediaPlayer;
    private VisualizerView visualizerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music_visualization, container, false);

        startBtn = (Button) rootView.findViewById(R.id.start_music_btn);
        stopBtn = (Button) rootView.findViewById(R.id.stop_music_btn);
        pauseBtn = (Button) rootView.findViewById(R.id.pause_music_btn);
        playListDisplayBtn = (Button) rootView.findViewById(R.id.show_playlist_btn);
        visualizerView = (VisualizerView) rootView.findViewById(R.id.visualizerView);

        initListeners();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            PlayListItem playListItem = data.getParcelableExtra(PlayListItem.ID);
            if (playListItem != null) {
                Toast.makeText(getActivity(), "Loaded: " + playListItem.getDisplayName(), Toast.LENGTH_SHORT)
                        .show();
                Uri uri = Uri.parse(playListItem.getUri());
                mediaPlayer = createMediaPlayer(uri);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlaying();
    }

    private void initListeners() {
        playListDisplayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlayList();
            }
        });

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

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pausePlaying();
            }
        });
    }

    private void startPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(.01f, .01f);
            mediaPlayer.start();

            visualizerView.init(mediaPlayer.getAudioSessionId());
            visualizerView.startVisualization();
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            visualizerView.stopVisualization();
            mediaPlayer.setLooping(false);
            mediaPlayer.stop();
        }
    }

    private void pausePlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    private void showPlayList() {
        startActivityForResult(new Intent(getActivity(), PlaylistActivity.class), 0);
    }

    private MediaPlayer createMediaPlayer(Uri uri) {
        return MediaPlayer.create(getActivity(), uri);
    }
}
