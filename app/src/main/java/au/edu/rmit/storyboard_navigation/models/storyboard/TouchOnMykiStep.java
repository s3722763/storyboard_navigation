package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class TouchOnMykiStep extends StoryboardStep {
    public TouchOnMykiStep(int step_number) {
        super(step_number, R.drawable.touchonmykipictogram, "Tap Myki card on card reader");
    }

    @Override
    public void update(TaskRunner taskRunner) {
        return;
    }
}
