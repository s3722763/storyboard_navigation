package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class TurnStep extends StoryboardStep {
    public TurnStep(int step_number, boolean left_turn, Location location) {
        super(step_number, R.drawable.walkpictogram, "Turn right", location);

        if (left_turn) {
            this.pictogram_resource_id = R.drawable.walkpictogram;
            this.details = "Turn left";
        }
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

    protected TurnStep(Parcel in) {
        super(in);
    }

    public static final Creator<TurnStep> CREATOR = new Creator<TurnStep>() {
        @Override
        public TurnStep createFromParcel(Parcel source) {
            return new TurnStep(source);
        }

        @Override
        public TurnStep[] newArray(int size) {
            return new TurnStep[size];
        }
    };
}
