package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Step {
    @JsonProperty("intersections")
    private ArrayList<Intersection> intersections;
    @JsonProperty("driving_side")
    private String driving_side;
    @JsonProperty("geometry")
    private String geometry;
    @JsonProperty("duration")
    private float duration;
    @JsonProperty("distance")
    private float distance;
    @JsonProperty("name")
    private String name;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("maneuver")
    private Maneuver maneuver;
    @JsonProperty("ref")
    private String ref;

    public ArrayList<Intersection> getIntersections() {
        return intersections;
    }

    public String getDriving_side() {
        return driving_side;
    }

    public String getGeometry() {
        return geometry;
    }

    public float getDuration() {
        return duration;
    }

    public float getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public String getMode() {
        return mode;
    }

    public Maneuver getManeuver() {
        return maneuver;
    }

    public String getRef() {
        return ref;
    }
}
