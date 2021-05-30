package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class WalkingStep extends StoryboardStep {
    public WalkingStep(int step_number, String details, Location location) {
        super(step_number, R.drawable.walkpictogram, "Walk " + details, location);
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

    protected WalkingStep(Parcel in) {
        super(in);
    }

    public static final Creator<WalkingStep> CREATOR = new Creator<WalkingStep>() {
        @Override
        public WalkingStep createFromParcel(Parcel source) {
            return new WalkingStep(source);
        }

        @Override
        public WalkingStep[] newArray(int size) {
            return new WalkingStep[size];
        }
    };
}
