package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class TouchOffMykiStep extends StoryboardStep {
    public TouchOffMykiStep(int step_number, Location location) {
        super(step_number, R.drawable.touchonmykipictogram, "Tap Myki card on card reader", location);
    }

    @Override
    public void update() {
        return;
    }
}
