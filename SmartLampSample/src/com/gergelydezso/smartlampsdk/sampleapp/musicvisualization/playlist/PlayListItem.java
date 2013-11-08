package com.gergelydezso.smartlampsdk.sampleapp.musicvisualization.playlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A POJO to hold playlist item related informations.
 *
 * @author robert.fejer
 */
public class PlayListItem implements Parcelable {
    public static final String ID = "PlayListItem";

    private String displayName;
    private String uri;

    public PlayListItem() {

    }

    public PlayListItem(Parcel parcelIn) {
        String[] data = new String[2];
        parcelIn.readStringArray(data);

        displayName = data[0];
        uri = data[1];
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {displayName, uri});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PlayListItem createFromParcel(Parcel in) {
            return new PlayListItem(in);
        }

        public PlayListItem[] newArray(int size) {
            return new PlayListItem[size];
        }
    };
}
