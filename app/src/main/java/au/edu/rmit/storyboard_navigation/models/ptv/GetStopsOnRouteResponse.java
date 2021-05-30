package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "disruptions", "geopath", "status" })
public class GetStopsOnRouteResponse {
    @JsonProperty("stops")
    private ArrayList<PTVRouteStop> stops;

    public ArrayList<PTVRouteStop> getStops() {
        return stops;
    }
}
