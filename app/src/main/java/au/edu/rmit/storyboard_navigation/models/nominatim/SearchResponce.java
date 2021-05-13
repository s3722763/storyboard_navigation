package au.edu.rmit.storyboard_navigation.models.nominatim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"licence", "boundingbox", "class", "type", "icon"})
public class SearchResponce {
    @JsonProperty("place_id")
    private long place_id;
    @JsonProperty("osm_type")
    private String osm_type;
    @JsonProperty("osm_id")
    private long osm_id;
    @JsonProperty("lat")
    private float lat;
    @JsonProperty("lon")
    private float lon;
    @JsonProperty("display_name")
    private String display_name;
    @JsonProperty("importance")
    private float importance;

    public long getPlace_id() {
        return place_id;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public long getOsm_id() {
        return osm_id;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public float getImportance() {
        return importance;
    }
}
