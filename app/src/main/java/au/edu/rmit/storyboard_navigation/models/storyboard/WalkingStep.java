package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class WalkingStep extends StoryboardStep {
    public WalkingStep(int step_number, Location location) {
        super(step_number, R.drawable.walkpictogram, "Walk", location);
    }


    @Override
    public void update() {
        return;
    }
}
