package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class NearestStop {
    @JsonProperty("disruption_ids")
    private ArrayList<Integer> disruption_ids;
    @JsonProperty("stop_distance")
    private float stop_distance;
    @JsonProperty("stop_suburb")
    private String stop_suburb;
    @JsonProperty("stop_name")
    private String stop_name;
    @JsonProperty("stop_id")
    private int stop_id;
    @JsonProperty("route_type")
    private int route_type;
    @JsonProperty("routes")
    private ArrayList<PTVRoute> route;
    @JsonProperty("stop_latitude")
    private float stop_latitude;
    @JsonProperty("stop_longitude")
    private float stop_longitude;
    @JsonProperty("stop_landmark")
    private String stop_landmark;
    @JsonProperty("stop_sequence")
    private int stop_sequence;

    public ArrayList<Integer> getDisruption_ids() {
        return disruption_ids;
    }

    public float getStop_distance() {
        return stop_distance;
    }

    public String getStop_suburb() {
        return stop_suburb;
    }

    public String getStop_name() {
        return stop_name;
    }

    public int getStop_id() {
        return stop_id;
    }

    public int getRoute_type() {
        return route_type;
    }

    public ArrayList<PTVRoute> getRoute() {
        return route;
    }

    public float getStop_latitude() {
        return stop_latitude;
    }

    public float getStop_longitude() {
        return stop_longitude;
    }

    public String getStop_landmark() {
        return stop_landmark;
    }

    public int getStop_sequence() {
        return stop_sequence;
    }
}
