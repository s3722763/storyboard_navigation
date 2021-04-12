package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class CrossRoadToStopStep extends StoryboardStep {
    public CrossRoadToStopStep(int step_number) {
        super(step_number, R.drawable.crossroadtostoppictogram, "Cross the road to get to the tram stop");
    }

    @Override
    public void update(TaskRunner taskRunner) {
        return;
    }
}
