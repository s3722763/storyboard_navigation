package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadStep extends StoryboardStep implements Parcelable {
    public CrossRoadStep(int step_number, Location location) {
        super(step_number, R.drawable.crossroadpictogram, "Cross the road", location);
    }

    protected CrossRoadStep(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CrossRoadStep> CREATOR = new Creator<CrossRoadStep>() {
        @Override
        public CrossRoadStep createFromParcel(Parcel in) {
            return new CrossRoadStep(in);
        }

        @Override
        public CrossRoadStep[] newArray(int size) {
            return new CrossRoadStep[size];
        }
    };

    @Override
    public void update() {
        return;
    }
}
