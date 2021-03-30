package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class TramTrackerResponse<T> {
    @JsonProperty("ResponseObject")
    T responseObjects;
    @JsonProperty("ResponseString")
    String responseString;
    @JsonAlias("hasError")
    @JsonProperty("HasError")
    boolean hasError;
    @JsonProperty("errorMessage")
    Optional<String> errorMessage;
    @JsonProperty("hasResponse")
    Optional<Boolean> hasResponse;
    @JsonProperty("timeRequested")
    EpochWithTimeZone timeRequested;
    @JsonProperty("timeResponded")
    EpochWithTimeZone timeResponded;
    @JsonProperty("webMethodCalled")
    String webMethodCalled;

    public EpochWithTimeZone getTimeRequested() {
        return timeRequested;
    }

    public void setTimeRequested(EpochWithTimeZone timeRequested) {
        this.timeRequested = timeRequested;
    }

    public EpochWithTimeZone getTimeResponded() {
        return timeResponded;
    }

    public void setTimeResponded(EpochWithTimeZone timeResponded) {
        this.timeResponded = timeResponded;
    }

    public String getWebMethodCalled() {
        return webMethodCalled;
    }

    public void setWebMethodCalled(String webMethodCalled) {
        this.webMethodCalled = webMethodCalled;
    }

    public Optional<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Optional<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Optional<Boolean> getHasResponse() {
        return hasResponse;
    }

    public void setHasResponse(Optional<Boolean> hasResponse) {
        this.hasResponse = hasResponse;
    }

    public T getResponseObject() {
        return responseObjects;
    }

    public void setResponseObject(T responseObjects) {
        this.responseObjects = responseObjects;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
