package au.edu.rmit.storyboard_navigation.models.ptv;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PTVRequestStatus {
    @JsonProperty("version")
    private String version;
    @JsonProperty("health")
    private int health;

    public String getVersion() {
        return version;
    }

    public int getHealth() {
        return health;
    }
}
