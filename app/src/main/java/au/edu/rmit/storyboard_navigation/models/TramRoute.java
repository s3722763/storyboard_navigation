package au.edu.rmit.storyboard_navigation.models;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.storyboard_navigation.models.ptv.PTVStop;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;

public class TramRoute implements Route {
    private final PTVStop start_stop;
    private final PTVStop end_stop;
    private final Location start_stop_location;
    private final Location end_stop_location;
    private final float distance_covered;
    private final String routeName;
    private List<StoryboardStep> steps;

    public TramRoute(PTVStop start_stop, PTVStop end_stop, Location start_stop_location, Location end_stop_location,
                     String routeName) {
        this.start_stop = start_stop;
        this.end_stop = end_stop;
        this.start_stop_location = start_stop_location;
        this.end_stop_location = end_stop_location;
        this.distance_covered = end_stop_location.distanceTo(start_stop_location);
        this.routeName = routeName;
    }

    public PTVStop getStart_stop() {
        return start_stop;
    }

    public PTVStop getEnd_stop() {
        return end_stop;
    }

    @Override
    public Location getStartLocation() {
        return start_stop_location;
    }

    @Override
    public Location getEndLocation() {
        return end_stop_location;
    }

    @Override
    public void createRoute() {
        this.steps = new ArrayList<>();
    }

    @Override
    public List<StoryboardStep> getRoute() {
        return this.steps;
    }

    @Override
    public float getDistance() {
        return this.distance_covered;
    }

    @Override
    public String toString() {
        return String.format("%s: %f (Location: %f,%f -> %f,%f) on tram %s", this.getEnd_stop().getStop_name(), this.getDistance(), this.getStart_stop().getStop_latitude(),
                this.getStart_stop().getStop_longitude(), this.getEnd_stop().getStop_latitude(), this.getEnd_stop().getStop_longitude(), this.routeName);
    }
}
