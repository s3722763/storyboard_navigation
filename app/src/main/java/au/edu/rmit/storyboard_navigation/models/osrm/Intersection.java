package au.edu.rmit.storyboard_navigation.models.osrm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Optional;

public class Intersection {
    @JsonProperty("out")
    private int out;
    @JsonProperty("in")
    private int in;
    @JsonProperty("entry")
    private ArrayList<Boolean> entry;
    @JsonProperty("location")
    private float[] location;
    @JsonProperty("bearings")
    private ArrayList<Integer> bearings;
    @JsonProperty("classes")
    private Optional<ArrayList<String>> classes;

    public Optional<ArrayList<String>> getClasses() {
        return classes;
    }

    public int getOut() {
        return out;
    }

    public int getIn() {
        return in;
    }

    public ArrayList<Boolean> getEntry() {
        return entry;
    }

    public float[] getLocation() {
        return location;
    }

    public ArrayList<Integer> getBearings() {
        return bearings;
    }
}
