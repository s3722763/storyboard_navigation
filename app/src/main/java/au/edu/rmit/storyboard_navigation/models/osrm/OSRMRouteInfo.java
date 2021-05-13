package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class OSRMRouteInfo {
    @JsonProperty("legs")
    private ArrayList<Leg> legs;
    @JsonProperty("weight_name")
    private String weight_name;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("distance")
    private float distance;
    @JsonProperty("duration")
    private float duration;
    private String url;

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public String getWeight_name() {
        return weight_name;
    }

    public float getWeight() {
        return weight;
    }

    public float getDistance() {
        return distance;
    }

    public float getDuration() {
        return duration;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
