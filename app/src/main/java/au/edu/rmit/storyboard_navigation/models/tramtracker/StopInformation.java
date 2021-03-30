package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopInformation {
    @JsonProperty("StopID")
    int stopID;
    @JsonProperty("Description")
    String description;
    @JsonProperty("StopName")
    String stopName;
    @JsonProperty("StopNo")
    int stopNo;
    @JsonProperty("DistanceToLocation")
    float distanceToLocation;
    @JsonProperty("Destination")
    String destination;
    @JsonProperty("Suburb")
    String suburb;
    @JsonProperty("Latitude")
    float latitude;
    @JsonProperty("Longitude")
    float longitude;
    @JsonProperty("RouteNo")
    int routeNo;
    @JsonProperty("CityDirection")
    String cityDirection;
    @JsonProperty("FlagStopNo")
    String flagStopNo;

    public int getStopNo() {
        return stopNo;
    }

    public void setStopNo(int stopNo) {
        this.stopNo = stopNo;
    }

    public int getStopID() {
        return stopID;
    }

    public void setStopID(int stopID) {
        this.stopID = stopID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }
}
