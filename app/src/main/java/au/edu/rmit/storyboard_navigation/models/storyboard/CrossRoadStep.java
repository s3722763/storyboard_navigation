package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadStep extends StoryboardStep {
    public CrossRoadStep(int step_number) {
        super(step_number, R.drawable.crossroadpictogram, "Cross the road");
    }

    @Override
    public void update() {
        return;
    }
}
