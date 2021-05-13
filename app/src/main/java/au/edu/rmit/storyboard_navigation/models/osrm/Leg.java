package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Leg {
    @JsonProperty("steps")
    private ArrayList<Step> steps;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("distance")
    private float distance;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("duration")
    private float duration;

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public float getWeight() {
        return weight;
    }

    public float getDistance() {
        return distance;
    }

    public String getSummary() {
        return summary;
    }

    public float getDuration() {
        return duration;
    }
}
