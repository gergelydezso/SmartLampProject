package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.playlist;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.gergelydezso.smartlampsdk.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the available musics to play.
 *
 * @author robert.fejer
 */
public class PlaylistActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private PlayListAdapter listAdapter;
    private ListView playList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_layout);

        getLoaderManager().initLoader(0, null, this);

        playList = (ListView) findViewById(R.id.playlist);

        initPlayList();
        initListeners();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] columns = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Video.Media.SIZE,
                android.provider.MediaStore.MediaColumns.DATA};

        CursorLoader cursorLoader = new CursorLoader(this, uri, columns, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<PlayListItem> playListItems = new ArrayList<PlayListItem>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PlayListItem playListItem = createPlayListItemFromCursor(cursor);
            playListItems.add(playListItem);

            cursor.moveToNext();
        }

        updatePlayList(playListItems);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void initPlayList() {
        listAdapter = new PlayListAdapter(this, new ArrayList<PlayListItem>());
        playList.setAdapter(listAdapter);
    }

    private void initListeners() {
        playList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlayListItem playListItem = (PlayListItem) view.getTag();
                setupActivityResultBasedOnSelectedItem(playListItem);
                finish();
            }
        });
    }

    private PlayListItem createPlayListItemFromCursor(Cursor cursor) {
        String uri = cursor.getString(1);
        String displayName = cursor.getString(2);

        PlayListItem playListItem = new PlayListItem();
        playListItem.setUri(uri);
        playListItem.setDisplayName(displayName);

        return playListItem;
    }

    private void updatePlayList(List<PlayListItem> playListItems) {
        listAdapter.clear();
        listAdapter.addAll(playListItems);
        listAdapter.notifyDataSetChanged();
    }

    private void setupActivityResultBasedOnSelectedItem(PlayListItem playListItem) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PlayListItem.ID, playListItem);
        setResult(RESULT_OK, resultIntent);
    }
}
