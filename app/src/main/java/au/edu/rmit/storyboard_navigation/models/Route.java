package au.edu.rmit.storyboard_navigation.models;

import android.location.Location;

import java.util.List;

import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;

public interface Route {
    public List<StoryboardStep> getRoute();
    public float getDistance();

    public Location getStartLocation();
    public Location getEndLocation();
    public void createRoute(int start_step);
}
