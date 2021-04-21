package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class PressStopButtonStep extends StoryboardStep {
    public PressStopButtonStep(int step_number, Location location) {
        super(step_number, R.drawable.pressstopbuttonpictogram, "Press the stop button to request stop", location);
    }

    @Override
    public void update() {
        return;
    }
}
