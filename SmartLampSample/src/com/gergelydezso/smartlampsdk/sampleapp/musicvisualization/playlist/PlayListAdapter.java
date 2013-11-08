package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.gergelydezso.smartlampsdk.sampleapp.R;

import java.util.List;

/**
 * An adapter for the playlist.
 * Handles the selection and display of items.
 *
 * @author robert.fejer
 */
public class PlayListAdapter extends ArrayAdapter<PlayListItem> {
    private final Context context;
    private final List<PlayListItem> playListItems;

    public PlayListAdapter(Context context, List<PlayListItem> playListItems) {
        super(context, R.layout.playlist_row_layout, playListItems);

        this.context = context;
        this.playListItems = playListItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.playlist_row_layout, parent, false);
        TextView playListEntry = (TextView) rowView.findViewById(R.id.playlist_entry);
        PlayListItem currentItem = playListItems.get(position);

        playListEntry.setText(currentItem.getDisplayName());
        rowView.setTag(currentItem);

        return rowView;
    }
}
