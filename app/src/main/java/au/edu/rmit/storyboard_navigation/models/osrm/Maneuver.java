package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Maneuver {
    @JsonProperty("bearing_after")
    private int bearing_after;
    @JsonProperty("location")
    private float[] location;
    @JsonProperty("type")
    private String type;
    @JsonProperty("bearing_before")
    private String bearing_before;
    @JsonProperty("modifier")
    private String modifier;

    public int getBearing_after() {
        return bearing_after;
    }

    public float[] getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getBearing_before() {
        return bearing_before;
    }

    public String getModifier() {
        return modifier;
    }
}
