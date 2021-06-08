package au.edu.rmit.storyboard_navigation.exceptions;

public class AutoGenerateRouteException extends Exception {
    public final String reason;

    public AutoGenerateRouteException(String reason) {
        this.reason = reason;
    }
}
