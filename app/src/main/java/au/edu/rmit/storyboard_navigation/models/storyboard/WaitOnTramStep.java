package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class WaitOnTramStep extends StoryboardStep {
    private int tram_route_number;

    public WaitOnTramStep(int step_number, int tram_route_number, Location location) {
        super(step_number, R.drawable.waitontrampictogram, "Stay on stop until stop %d", location);
        this.tram_route_number = tram_route_number;
    }

    @Override
    public String get_details() {
        return String.format(this.details, this.tram_route_number);
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
        dest.writeInt(this.tram_route_number);
    }

    public void readFromParcel(Parcel source) {
        super.readFromParcel(source);
        this.tram_route_number = source.readInt();
    }

    protected WaitOnTramStep(Parcel in) {
        super(in);
        this.tram_route_number = in.readInt();
    }

    public static final Creator<WaitOnTramStep> CREATOR = new Creator<WaitOnTramStep>() {
        @Override
        public WaitOnTramStep createFromParcel(Parcel source) {
            return new WaitOnTramStep(source);
        }

        @Override
        public WaitOnTramStep[] newArray(int size) {
            return new WaitOnTramStep[size];
        }
    };
}
