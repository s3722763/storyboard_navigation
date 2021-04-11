package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class WaitOnTramStep extends StoryboardStep {
    public WaitOnTramStep(int step_number) {
        super(step_number, R.drawable.waitontrampictogram, "Stay on stop until stop %d");
    }
}
