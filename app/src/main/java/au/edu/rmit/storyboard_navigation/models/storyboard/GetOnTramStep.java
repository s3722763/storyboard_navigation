package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class GetOnTramStep extends StoryboardStep {
    public GetOnTramStep(int step_number) {
        super(step_number, R.drawable.getontrampictogram, "Get on tram %d");
    }

    @Override
    public void update(TaskRunner taskRunner) {
        return;
    }
}
