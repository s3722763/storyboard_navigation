package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadToStopStep extends StoryboardStep {
    public CrossRoadToStopStep(int step_number) {
        super(step_number, R.drawable.crossroadtostoppictogram, "Cross the road to get to the tram stop");
    }

    @Override
    public void update() {
        return;
    }
}
