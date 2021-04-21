package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadToStopStep extends StoryboardStep {
    public CrossRoadToStopStep(int step_number, Location location) {
        super(step_number, R.drawable.crossroadtostoppictogram, "Cross the road to get to the tram stop", location);
    }

    @Override
    public void update() {
        return;
    }
}
