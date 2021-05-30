package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class TouchOffMykiStep extends StoryboardStep {
    public TouchOffMykiStep(int step_number, Location location) {
        super(step_number, R.drawable.touchoffmykipictogram, "Tap Myki card on card reader", location);
    }

    @Override
    public void update() {
        return;
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

    protected TouchOffMykiStep(Parcel in) {
        super(in);
    }

    public static final Creator<TouchOffMykiStep> CREATOR = new Creator<TouchOffMykiStep>() {
        @Override
        public TouchOffMykiStep createFromParcel(Parcel source) {
            return new TouchOffMykiStep(source);
        }

        @Override
        public TouchOffMykiStep[] newArray(int size) {
            return new TouchOffMykiStep[size];
        }
    };
}
