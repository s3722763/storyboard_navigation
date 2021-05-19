package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class TouchOnMykiStep extends StoryboardStep {
    public TouchOnMykiStep(int step_number, Location location) {
        super(step_number, R.drawable.touchonmykipictogram, "Tap Myki card on card reader", location);
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

    protected TouchOnMykiStep(Parcel in) {
        super(in);
    }

    public static final Creator<TouchOnMykiStep> CREATOR = new Creator<TouchOnMykiStep>() {
        @Override
        public TouchOnMykiStep createFromParcel(Parcel source) {
            return new TouchOnMykiStep(source);
        }

        @Override
        public TouchOnMykiStep[] newArray(int size) {
            return new TouchOnMykiStep[size];
        }
    };
}
