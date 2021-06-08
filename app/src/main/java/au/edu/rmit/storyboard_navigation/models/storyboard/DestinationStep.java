package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class DestinationStep extends StoryboardStep {
    public DestinationStep(int step_number, String details, Location location) {
        super(step_number, R.drawable.destination_pictogram, details, location);
    }

    @Override
    public void update() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public void readFromParcel(Parcel source) {
        super.readFromParcel(source);
    }

    protected DestinationStep(Parcel in) {
        super(in);
    }

    public static final Creator<DestinationStep> CREATOR = new Creator<DestinationStep>() {
        @Override
        public DestinationStep createFromParcel(Parcel source) {
            return new DestinationStep(source);
        }

        @Override
        public DestinationStep[] newArray(int size) {
            return new DestinationStep[size];
        }
    };
}
