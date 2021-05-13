package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class OSRMGetRouteResponse {
    @JsonProperty("code")
    private String code;
    @JsonProperty("waypoints")
    private ArrayList<Waypoint> waypoints;
    @JsonProperty("routes")
    private ArrayList<OSRMRouteInfo> routes;

    public String getCode() {
        return code;
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    public ArrayList<OSRMRouteInfo> getRoutes() {
        return routes;
    }

    public void setUrl(String url) {
        for (OSRMRouteInfo route : routes) {
            route.setUrl(url);
        }
    }
}
