package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"nodes"})
public class Waypoint {
    @JsonProperty("hint")
    private String hint;
    @JsonProperty("distance")
    private float distance;
    @JsonProperty("location")
    private float[] location;
    @JsonProperty("name")
    private String name;

    public String getHint() {
        return hint;
    }

    public float getDistance() {
        return distance;
    }

    public float[] getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
