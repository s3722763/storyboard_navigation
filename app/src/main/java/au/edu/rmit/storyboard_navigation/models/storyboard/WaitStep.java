package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;
import android.os.Parcel;
import android.util.Log;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.models.tramtracker.ArrivalPrediction;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;
import au.edu.rmit.storyboard_navigation.work.ArrivalPredictionTask;

public class WaitStep extends StoryboardStep {
    private int stopNo;
    private int routeNo;
    private ArrivalPrediction arrivalPrediction;

    public WaitStep(int step_number, int stopNo, int routeNo, Location location) {
        super(step_number, R.drawable.waitpictogram, "Wait %d minutes for tram %d", location);
        this.stopNo = stopNo;
        this.routeNo = routeNo;
    }

    @Override
    public String get_details() {
        if (arrivalPrediction != null) {
            ZonedDateTime predictedArrivalTime = arrivalPrediction.getPredictedArrivalDateTime().getDatetime();
            Instant now = Instant.now();
            Duration timeDifference = Duration.between(now, predictedArrivalTime);
            return String.format(this.details, timeDifference.getSeconds() / 60, this.routeNo);
        } else {
            return "Loading";
        }
    }

    @Override
    public void update() {
        ArrivalPredictionTask task = new ArrivalPredictionTask(this.stopNo, this.routeNo);

        TramTrackerResponse<ArrayList<ArrivalPrediction>> data;
        data = task.get();

        arrivalPrediction = data.getResponseObject().get(0);
        Log.i("WaitStepUpdate", "Tram number: " + String.valueOf(arrivalPrediction.getVehicleNo()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.stopNo);
        dest.writeInt(this.routeNo);
        dest.writeParcelable(this.arrivalPrediction, flags);
    }

    public void readFromParcel(Parcel source) {
        super.readFromParcel(source);
        this.stopNo = source.readInt();
        this.routeNo = source.readInt();
        this.arrivalPrediction = source.readParcelable(ArrivalPrediction.class.getClassLoader());
    }

    protected WaitStep(Parcel in) {
        super(in);
        this.stopNo = in.readInt();
        this.routeNo = in.readInt();
        this.arrivalPrediction = in.readParcelable(ArrivalPrediction.class.getClassLoader());
    }

    public static final Creator<WaitStep> CREATOR = new Creator<WaitStep>() {
        @Override
        public WaitStep createFromParcel(Parcel source) {
            return new WaitStep(source);
        }

        @Override
        public WaitStep[] newArray(int size) {
            return new WaitStep[size];
        }
    };
}
