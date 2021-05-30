package au.edu.rmit.storyboard_navigation.models.tramtracker;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@JsonDeserialize(using = EpochWithTimeZoneDeserializer.class)
public class EpochWithTimeZone implements Parcelable {
    String epoch_string;
    ZonedDateTime datetime;

    public EpochWithTimeZone(String epoch_string) {
        this.epoch_string = epoch_string;

        epoch_string = epoch_string.replace("+", "~+");
        String[] parts = epoch_string.split("~");
        long epoch = Long.parseLong(parts[0]);
        //System.out.println(epoch);
        this.datetime = Instant.ofEpochMilli(epoch).atZone(ZoneId.of(parts[1]));
        //System.out.println(datetime.toString());
    }

    public ZonedDateTime getDatetime() {
        return this.datetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.epoch_string);
        dest.writeSerializable(this.datetime);
    }

    public void readFromParcel(Parcel source) {
        this.epoch_string = source.readString();
        this.datetime = (ZonedDateTime) source.readSerializable();
    }

    protected EpochWithTimeZone(Parcel in) {
        this.epoch_string = in.readString();
        this.datetime = (ZonedDateTime) in.readSerializable();
    }

    public static final Creator<EpochWithTimeZone> CREATOR = new Creator<EpochWithTimeZone>() {
        @Override
        public EpochWithTimeZone createFromParcel(Parcel source) {
            return new EpochWithTimeZone(source);
        }

        @Override
        public EpochWithTimeZone[] newArray(int size) {
            return new EpochWithTimeZone[size];
        }
    };
}
