package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PTVRoute {
    @JsonProperty("route_type")
    private int route_type;
    @JsonProperty("route_id")
    private int route_id;
    @JsonProperty("route_name")
    private String route_name;
    // This is string because can have multiple routes for a stop
    // eg. "250-251 combined" is a valid response meaning both 250 and 251 use this stop
    @JsonProperty("route_number")
    private String route_number;
    @JsonProperty("route_gtfs_id")
    private String route_gtfs_id;
    @JsonProperty("geopath")
    private ArrayList<String> geopath;

    public int getRoute_type() {
        return route_type;
    }

    public int getRoute_id() {
        return route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public String getRoute_number() {
        return route_number;
    }

    public String getRoute_gtfs_id() {
        return route_gtfs_id;
    }

    public ArrayList<String> getGeopath() {
        return geopath;
    }
}
