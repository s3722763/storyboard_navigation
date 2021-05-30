package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadToStopStep extends StoryboardStep {
    public CrossRoadToStopStep(int step_number, Location location) {
        super(step_number, R.drawable.crossroadtostoppictogram, "Cross the road to get to the tram stop", location);
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

    protected CrossRoadToStopStep(Parcel in) {
        super(in);
    }

    public static final Creator<CrossRoadToStopStep> CREATOR = new Creator<CrossRoadToStopStep>() {
        @Override
        public CrossRoadToStopStep createFromParcel(Parcel source) {
            return new CrossRoadToStopStep(source);
        }

        @Override
        public CrossRoadToStopStep[] newArray(int size) {
            return new CrossRoadToStopStep[size];
        }
    };
}
