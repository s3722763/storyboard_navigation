package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(value = { "disruptions", "geopath", "status" })
public class GetStopsOnRouteResponse {
    @JsonProperty("stops")
    private ArrayList<PTVStop> stops;

    public ArrayList<PTVStop> getStops() {
        return stops;
    }
}
