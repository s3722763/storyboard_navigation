package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;

import au.edu.rmit.storyboard_navigation.R;

public class GetOnTramStep extends StoryboardStep {
    private int tram_route_number;

    public GetOnTramStep(int step_number, int tram_route_number, Location location) {
        super(step_number, R.drawable.getontrampictogram, "Get on tram %d", location);
        this.tram_route_number = tram_route_number;
    }

    @Override
    public String get_details() {
        return String.format(this.details, tram_route_number);
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

    protected GetOnTramStep(Parcel in) {
        super(in);
        this.tram_route_number = in.readInt();
    }

    public static final Creator<GetOnTramStep> CREATOR = new Creator<GetOnTramStep>() {
        @Override
        public GetOnTramStep createFromParcel(Parcel source) {
            return new GetOnTramStep(source);
        }

        @Override
        public GetOnTramStep[] newArray(int size) {
            return new GetOnTramStep[size];
        }
    };
}
