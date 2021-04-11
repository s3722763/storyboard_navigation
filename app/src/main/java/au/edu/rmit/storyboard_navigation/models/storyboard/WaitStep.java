package au.edu.rmit.storyboard_navigation.models.storyboard;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.models.tramtracker.ArrivalPrediction;
import au.edu.rmit.storyboard_navigation.work.IGetWebInfo;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class WaitStep extends StoryboardStep implements IGetWebInfo<ArrivalPrediction> {
    private int stopNo;
    private int routeNo;
    private ArrivalPrediction arrivalPrediction;

    public WaitStep(int step_number) {
        super(step_number, R.drawable.waitpictogram, "Wait %d minutes for tram %d");
    }

    @Override
    public String get_details() {
        ZonedDateTime predictedArrivalTime = arrivalPrediction.getPredictedArrivalDateTime().getDatetime();
        Instant now = Instant.now();
        Duration timeDifference = Duration.between(now, predictedArrivalTime);
        return String.format(this.get_details(), timeDifference.getSeconds() / 60, "1");
    }

    @Override
    public void update_info(ArrivalPrediction response) {
        this.arrivalPrediction = response;
    }
}
