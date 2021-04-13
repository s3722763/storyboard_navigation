package au.edu.rmit.storyboard_navigation.models.storyboard;

import au.edu.rmit.storyboard_navigation.R;

public class PressStopButtonStep extends StoryboardStep {
    public PressStopButtonStep(int step_number) {
        super(step_number, R.drawable.pressstopbuttonpictogram, "Press the stop button to request stop");
    }

    @Override
    public void update() {
        return;
    }
}
