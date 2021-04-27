package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

public abstract class StoryboardStep {
    final int step_number;
    final int pictogram_resource_id;
    final String details;
    final Location location;

    protected StoryboardStep(int step_number, int pictogram_resource_id, String details, Location location) {
        this.step_number = step_number;
        this.pictogram_resource_id = pictogram_resource_id;
        this.details = details;
        this.location = location;
    }

    public int get_pictogram_resource_id() {
        return this.pictogram_resource_id;
    }

    public String get_details() {
        return this.details;
    }

    public int get_step_number() {
        return this.step_number;
    }

    public Location get_location() {
        return this.location;
    }

    public abstract void update();
}
