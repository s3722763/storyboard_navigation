package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;

public class GetOffTramStep extends StoryboardStep {
    public GetOffTramStep(int step_number) {
        super(step_number, R.drawable.getofftrampictogram, "Get off tram, onto the platform");
    }

    @Override
    public void update() {
        return;
    }
}
