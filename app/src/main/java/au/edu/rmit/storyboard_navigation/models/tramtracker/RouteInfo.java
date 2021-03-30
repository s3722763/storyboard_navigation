package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteInfo {
    @JsonProperty("RouteNo")
    int routeNo;
    @JsonProperty("InternalRouteNo")
    int internalRouteNo;
    @JsonProperty("AlphaNumericRouteNo")
    String alphaNumericRouteNo;
    @JsonProperty("Destination")
    String destination;
    @JsonProperty("IsUpDestination")
    boolean isUpDestination;
    @JsonProperty("HasLowFloor")
    boolean hasLowFloor;

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public int getInternalRouteNo() {
        return internalRouteNo;
    }

    public void setInternalRouteNo(int internalRouteNo) {
        this.internalRouteNo = internalRouteNo;
    }

    public String getAlphaNumericRouteNo() {
        return alphaNumericRouteNo;
    }

    public void setAlphaNumericRouteNo(String alphaNumericRouteNo) {
        this.alphaNumericRouteNo = alphaNumericRouteNo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isUpDestination() {
        return isUpDestination;
    }

    public void setUpDestination(boolean upDestination) {
        isUpDestination = upDestination;
    }

    public boolean isHasLowFloor() {
        return hasLowFloor;
    }

    public void setHasLowFloor(boolean hasLowFloor) {
        this.hasLowFloor = hasLowFloor;
    }
}
