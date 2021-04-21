package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class CrossRoadStep extends StoryboardStep {
    public CrossRoadStep(int step_number, Location location) {
        super(step_number, R.drawable.crossroadpictogram, "Cross the road", location);
    }

    @Override
    public void update() {
        return;
    }
}
