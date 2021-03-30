package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Distruption {
    @JsonProperty("DisplayType")
    String displayType;
    @JsonProperty("MessageCount")
    int messageCount;
    @JsonProperty("Messages")
    ArrayList<String> messages;
}
