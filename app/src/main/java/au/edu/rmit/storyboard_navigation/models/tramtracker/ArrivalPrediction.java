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
}
