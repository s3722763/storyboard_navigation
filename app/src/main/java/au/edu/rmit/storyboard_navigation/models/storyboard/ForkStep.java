package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class ForkStep extends StoryboardStep {
    public ForkStep(int step_number, String direction, Location location) {
        super(step_number, R.drawable.walkpictogram, "Turn " + direction, location);
    }

    @Override
    public void update() {

    }
}
