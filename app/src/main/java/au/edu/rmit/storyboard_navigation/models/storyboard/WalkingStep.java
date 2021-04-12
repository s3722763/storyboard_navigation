package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class WalkingStep extends StoryboardStep {
    public WalkingStep(int step_number) {
        super(step_number, R.drawable.walkpictogram, "Walk");
    }


    @Override
    public void update(TaskRunner taskRunner) {
        return;
    }
}
