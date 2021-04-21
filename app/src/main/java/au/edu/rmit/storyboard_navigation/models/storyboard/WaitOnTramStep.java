package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class WaitOnTramStep extends StoryboardStep {
    private int tram_route_number;

    public WaitOnTramStep(int step_number, int tram_route_number, Location location) {
        super(step_number, R.drawable.waitontrampictogram, "Stay on stop until stop %d", location);
        this.tram_route_number = tram_route_number;
    }

    @Override
    public String get_details() {
        return String.format(this.details, this.tram_route_number);
    }

    @Override
    public void update() {
        return;
    }
}
