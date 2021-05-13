package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class OSRMNearest {
    @JsonProperty
    ArrayList<Waypoint> waypoints;
    @JsonProperty
    String code;

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    public String getCode() {
        return this.code;
    }
}
