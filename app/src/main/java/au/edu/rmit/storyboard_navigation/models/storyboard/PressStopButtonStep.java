package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class PressStopButtonStep extends StoryboardStep {
    public PressStopButtonStep(int step_number, Location location) {
        super(step_number, R.drawable.pressstopbuttonpictogram, "Press the stop button to request stop", location);
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

    protected PressStopButtonStep(Parcel in) {
        super(in);
    }

    public static final Creator<PressStopButtonStep> CREATOR = new Creator<PressStopButtonStep>() {
        @Override
        public PressStopButtonStep createFromParcel(Parcel source) {
            return new PressStopButtonStep(source);
        }

        @Override
        public PressStopButtonStep[] newArray(int size) {
            return new PressStopButtonStep[size];
        }
    };
}
