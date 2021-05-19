package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class StoryboardStep implements Parcelable {
    int step_number;
    int pictogram_resource_id;
    String details;
    Location location;

    protected StoryboardStep(int step_number, int pictogram_resource_id, String details, Location location) {
        this.step_number = step_number;
        this.pictogram_resource_id = pictogram_resource_id;
        this.details = details;
        this.location = location;
    }

    public int get_pictogram_resource_id() {
        return this.pictogram_resource_id;
    }

    public String get_details() {
        return this.details;
    }

    public int get_step_number() {
        return this.step_number;
    }

    public Location get_location() {
        return this.location;
    }

    public void update() {};

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.step_number);
        dest.writeInt(this.pictogram_resource_id);
        dest.writeString(this.details);
        dest.writeParcelable(this.location, flags);
    }

    public void readFromParcel(Parcel source) {
        this.step_number = source.readInt();
        this.pictogram_resource_id = source.readInt();
        this.details = source.readString();
        this.location = source.readParcelable(Location.class.getClassLoader());
    }

    protected StoryboardStep(Parcel in) {
        this.step_number = in.readInt();
        this.pictogram_resource_id = in.readInt();
        this.details = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<StoryboardStep> CREATOR = new Creator<StoryboardStep>() {
        @Override
        public StoryboardStep createFromParcel(Parcel source) {
            return new StoryboardStep(source);
        }

        @Override
        public StoryboardStep[] newArray(int size) {
            return new StoryboardStep[size];
        }
    };
}
