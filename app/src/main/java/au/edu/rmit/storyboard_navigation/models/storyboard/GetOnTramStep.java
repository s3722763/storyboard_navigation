package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class GetOnTramStep extends StoryboardStep {
    private final int tram_route_number;

    public GetOnTramStep(int step_number, int tram_route_number, Location location) {
        super(step_number, R.drawable.getontrampictogram, "Get on tram %d", location);
        this.tram_route_number = tram_route_number;
    }

    @Override
    public String get_details() {
        return String.format(this.details, tram_route_number);
    }

    @Override
    public void update() {
        return;
    }
}
