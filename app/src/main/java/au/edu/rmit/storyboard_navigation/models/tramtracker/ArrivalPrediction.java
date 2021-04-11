package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArrivalPrediction {
    @JsonProperty("__type")
    String type;
    @JsonProperty("AirConditioned")
    boolean airconditioned;
    @JsonProperty("Destination")
    String destination;
    @JsonProperty("DisplayAC")
    boolean displayAC;
    @JsonProperty("DisruptionMessage")
    Distruption distruptionMessage;
    @JsonProperty("HasDisruption")
    boolean hasDisruption;
    @JsonProperty("HasPlannedOccupation")
    boolean hasPlannedOccupation;
    @JsonProperty("HasSpecialEvent")
    boolean hasSpecialEvent;
    @JsonProperty("HeadBoardRouteNo")
    String headBoardRouteNo;
    @JsonProperty("InternalRouteNo")
    int internalRouteNo;
    @JsonProperty("IsLowFloorTram")
    boolean isLowFloorTram;
    @JsonProperty("IsTTAvailable")
    boolean isTTAvaliable;
    @JsonProperty("PlannedOccupationMessage")
    String plannedOccupationMessage;
    @JsonProperty("PredictedArrivalDateTime")
    EpochWithTimeZone predictedArrivalDateTime;
    @JsonProperty("RouteNo")
    String routeNo;
    @JsonProperty("SpecialEventMessage")
    String specialEventMessage;
    @JsonProperty("TramClass")
    String tramClass;
    @JsonProperty("TripID")
    int tripID;
    @JsonProperty("VehicleNo")
    int vehicleNo;

    public String getType() {
        return type;
    }

    public boolean isAirconditioned() {
        return airconditioned;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isDisplayAC() {
        return displayAC;
    }

    public Distruption getDistruptionMessage() {
        return distruptionMessage;
    }

    public boolean hasDisruption() {
        return hasDisruption;
    }

    public boolean hasPlannedOccupation() {
        return hasPlannedOccupation;
    }

    public boolean hasSpecialEvent() {
        return hasSpecialEvent;
    }

    public String getHeadBoardRouteNo() {
        return headBoardRouteNo;
    }

    public int getInternalRouteNo() {
        return internalRouteNo;
    }

    public boolean isLowFloorTram() {
        return isLowFloorTram;
    }

    public boolean isTTAvaliable() {
        return isTTAvaliable;
    }

    public String getPlannedOccupationMessage() {
        return plannedOccupationMessage;
    }

    public EpochWithTimeZone getPredictedArrivalDateTime() {
        return predictedArrivalDateTime;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public String getSpecialEventMessage() {
        return specialEventMessage;
    }

    public String getTramClass() {
        return tramClass;
    }

    public int getTripID() {
        return tripID;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }
}
