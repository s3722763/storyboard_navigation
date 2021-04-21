package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class GetOffTramStep extends StoryboardStep {
    public GetOffTramStep(int step_number, Location location) {
        super(step_number, R.drawable.getofftrampictogram, "Get off tram, onto the platform", location);
    }

    @Override
    public void update() {
        return;
    }
}
