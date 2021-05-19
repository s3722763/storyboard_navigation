package au.edu.rmit.storyboard_navigation.models.tramtracker;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArrivalPrediction implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeByte(this.airconditioned ? (byte) 1 : (byte) 0);
        dest.writeString(this.destination);
        dest.writeByte(this.displayAC ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.distruptionMessage, flags);
        dest.writeByte(this.hasDisruption ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasPlannedOccupation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasSpecialEvent ? (byte) 1 : (byte) 0);
        dest.writeString(this.headBoardRouteNo);
        dest.writeInt(this.internalRouteNo);
        dest.writeByte(this.isLowFloorTram ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTTAvaliable ? (byte) 1 : (byte) 0);
        dest.writeString(this.plannedOccupationMessage);
        dest.writeParcelable(this.predictedArrivalDateTime, flags);
        dest.writeString(this.routeNo);
        dest.writeString(this.specialEventMessage);
        dest.writeString(this.tramClass);
        dest.writeInt(this.tripID);
        dest.writeInt(this.vehicleNo);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.airconditioned = source.readByte() != 0;
        this.destination = source.readString();
        this.displayAC = source.readByte() != 0;
        this.distruptionMessage = source.readParcelable(Distruption.class.getClassLoader());
        this.hasDisruption = source.readByte() != 0;
        this.hasPlannedOccupation = source.readByte() != 0;
        this.hasSpecialEvent = source.readByte() != 0;
        this.headBoardRouteNo = source.readString();
        this.internalRouteNo = source.readInt();
        this.isLowFloorTram = source.readByte() != 0;
        this.isTTAvaliable = source.readByte() != 0;
        this.plannedOccupationMessage = source.readString();
        this.predictedArrivalDateTime = source.readParcelable(EpochWithTimeZone.class.getClassLoader());
        this.routeNo = source.readString();
        this.specialEventMessage = source.readString();
        this.tramClass = source.readString();
        this.tripID = source.readInt();
        this.vehicleNo = source.readInt();
    }

    protected ArrivalPrediction(Parcel in) {
        this.type = in.readString();
        this.airconditioned = in.readByte() != 0;
        this.destination = in.readString();
        this.displayAC = in.readByte() != 0;
        this.distruptionMessage = in.readParcelable(Distruption.class.getClassLoader());
        this.hasDisruption = in.readByte() != 0;
        this.hasPlannedOccupation = in.readByte() != 0;
        this.hasSpecialEvent = in.readByte() != 0;
        this.headBoardRouteNo = in.readString();
        this.internalRouteNo = in.readInt();
        this.isLowFloorTram = in.readByte() != 0;
        this.isTTAvaliable = in.readByte() != 0;
        this.plannedOccupationMessage = in.readString();
        this.predictedArrivalDateTime = in.readParcelable(EpochWithTimeZone.class.getClassLoader());
        this.routeNo = in.readString();
        this.specialEventMessage = in.readString();
        this.tramClass = in.readString();
        this.tripID = in.readInt();
        this.vehicleNo = in.readInt();
    }

    public ArrivalPrediction() {}

    public static final Creator<ArrivalPrediction> CREATOR = new Creator<ArrivalPrediction>() {
        @Override
        public ArrivalPrediction createFromParcel(Parcel source) {
            return new ArrivalPrediction(source);
        }

        @Override
        public ArrivalPrediction[] newArray(int size) {
            return new ArrivalPrediction[size];
        }
    };
}
