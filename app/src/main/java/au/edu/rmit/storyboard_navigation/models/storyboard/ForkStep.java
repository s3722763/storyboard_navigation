package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class ForkStep extends StoryboardStep {
    public ForkStep(int step_number, String direction, Location location) {
        super(step_number, R.drawable.walkpictogram, "Turn " + direction, location);
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

    protected ForkStep(Parcel in) {
        super(in);
    }

    public static final Creator<ForkStep> CREATOR = new Creator<ForkStep>() {
        @Override
        public ForkStep createFromParcel(Parcel source) {
            return new ForkStep(source);
        }

        @Override
        public ForkStep[] newArray(int size) {
            return new ForkStep[size];
        }
    };
}
