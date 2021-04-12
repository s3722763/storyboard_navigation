package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.util.Log;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.models.tramtracker.ArrivalPrediction;
import au.edu.rmit.storyboard_navigation.work.ArrivalPredictionTask;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class WaitStep extends StoryboardStep {
    private int stopNo;
    private int routeNo;
    private ArrivalPrediction arrivalPrediction;

    public WaitStep(int step_number, int stopNo, int routeNo) {
        super(step_number, R.drawable.waitpictogram, "Wait %d minutes for tram %d");
        this.stopNo = stopNo;
        this.routeNo = routeNo;
    }

    @Override
    public String get_details() {
        if (arrivalPrediction != null) {
            ZonedDateTime predictedArrivalTime = arrivalPrediction.getPredictedArrivalDateTime().getDatetime();
            Instant now = Instant.now();
            Duration timeDifference = Duration.between(now, predictedArrivalTime);
            return String.format(this.details, timeDifference.getSeconds() / 60, 1);
        } else {
            return "Loading";
        }
    }

    @Override
    public void update(TaskRunner taskRunner) {
        taskRunner.executeAsync(new ArrivalPredictionTask(this.stopNo, this.routeNo), (data) -> {
            this.arrivalPrediction = data.getResponseObject().get(0);
            Log.i("aaa", String.valueOf(this.arrivalPrediction.getVehicleNo()));
        });
    }
}
