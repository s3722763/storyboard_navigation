package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"disruption_ids", "stop_ticket"})
public class PTVRouteStop {
    @JsonProperty("stop_suburb")
    private String stop_suburb;
    @JsonProperty("route_type")
    private int route_type;
    @JsonProperty("stop_latitude")
    private float stop_latitude;
    @JsonProperty("stop_longitude")
    private float stop_longitude;
    @JsonProperty("stop_sequence")
    private float stop_sequence;
    @JsonProperty("stop_id")
    private int stop_id;
    @JsonProperty("stop_name")
    private String stop_name;
    @JsonProperty("stop_landmark")
    private String stop_landmark;
    @JsonProperty("stop_distance")
    private float stop_distance;

    public float getStop_distance() {
        return stop_distance;
    }

    public String getStop_suburb() {
        return stop_suburb;
    }

    public int getRoute_type() {
        return route_type;
    }

    public float getStop_latitude() {
        return stop_latitude;
    }

    public float getStop_longitude() {
        return stop_longitude;
    }

    public float getStop_sequence() {
        return stop_sequence;
    }

    public int getStop_id() {
        return stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public String getStop_landmark() {
        return stop_landmark;
    }
}
