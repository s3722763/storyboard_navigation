package au.edu.rmit.storyboard_navigation.models.tramtracker;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Distruption implements Parcelable {
    @JsonProperty("DisplayType")
    String displayType;
    @JsonProperty("MessageCount")
    int messageCount;
    @JsonProperty("Messages")
    ArrayList<String> messages;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.displayType);
        dest.writeInt(this.messageCount);
        dest.writeStringList(this.messages);
    }

    public void readFromParcel(Parcel source) {
        this.displayType = source.readString();
        this.messageCount = source.readInt();
        this.messages = source.createStringArrayList();
    }

    public Distruption() {
    }

    protected Distruption(Parcel in) {
        this.displayType = in.readString();
        this.messageCount = in.readInt();
        this.messages = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Distruption> CREATOR = new Parcelable.Creator<Distruption>() {
        @Override
        public Distruption createFromParcel(Parcel source) {
            return new Distruption(source);
        }

        @Override
        public Distruption[] newArray(int size) {
            return new Distruption[size];
        }
    };
}
