package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class GetOffTramStep extends StoryboardStep {
    public GetOffTramStep(int step_number, Location location) {
        super(step_number, R.drawable.getofftrampictogram, "Get off tram, onto the platform", location);
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

    protected GetOffTramStep(Parcel in) {
        super(in);
    }

    public static final Creator<GetOffTramStep> CREATOR = new Creator<GetOffTramStep>() {
        @Override
        public GetOffTramStep createFromParcel(Parcel source) {
            return new GetOffTramStep(source);
        }

        @Override
        public GetOffTramStep[] newArray(int size) {
            return new GetOffTramStep[size];
        }
    };
}
