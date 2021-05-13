package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Optional;

@JsonIgnoreProperties(value = { "disruptions" })
public class NearestStopRequest {
    @JsonProperty("stops")
    private ArrayList<NearestStop> stops;
    @JsonProperty("status")
    private PTVRequestStatus status;

    public ArrayList<NearestStop> getStops() {
        return stops;
    }

    public PTVRequestStatus getStatus() {
        return status;
    }
}
