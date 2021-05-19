package au.edu.rmit.storyboard_navigation.models;

import android.location.Location;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.storyboard_navigation.models.ptv.PTVRoute;
import au.edu.rmit.storyboard_navigation.models.ptv.PTVRouteStop;
import au.edu.rmit.storyboard_navigation.models.storyboard.GetOffTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.GetOnTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.PressStopButtonStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.TouchOffMykiStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.TouchOnMykiStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WaitOnTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WaitStep;
import au.edu.rmit.storyboard_navigation.models.tramtracker.StopInformation;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;
import au.edu.rmit.storyboard_navigation.utils.LevenshteinDistance;
import au.edu.rmit.storyboard_navigation.work.TramTrackerGetStopsOnRoute;

public class TramRoute implements Route {
    private final PTVRoute route;
    private final PTVRouteStop start_stop;
    private final PTVRouteStop end_stop;
    private final Location start_stop_location;
    private final Location end_stop_location;
    private final float distance_covered;
    private List<StoryboardStep> steps;

    public TramRoute(PTVRouteStop start_stop, PTVRouteStop end_stop, Location start_stop_location, Location end_stop_location,
                     PTVRoute route) {
        this.start_stop = start_stop;
        this.end_stop = end_stop;
        this.start_stop_location = start_stop_location;
        this.end_stop_location = end_stop_location;
        this.distance_covered = end_stop_location.distanceTo(start_stop_location);
        this.route = route;
    }

    public PTVRouteStop getStart_stop() {
        return start_stop;
    }

    public PTVRouteStop getEnd_stop() {
        return end_stop;
    }

    @Override
    public Location getStartLocation() {
        return start_stop_location;
    }

    @Override
    public Location getEndLocation() {
        return end_stop_location;
    }

    @Override
    public void createRoute() {
        this.steps = new ArrayList<>();

        // This can fail as some route numbers contain a letter.
        int route_number = Integer.parseInt(this.route.getRoute_number());

        int start_stop_number = Integer.parseInt(this.start_stop.getStop_name().split("#")[1]);
        int end_stop_number = Integer.parseInt(this.end_stop.getStop_name().split("#")[1]);

        String up = "true";

        if (end_stop_number < start_stop_number) {
            up = "false";
        }

        TramTrackerGetStopsOnRoute getStopsOnRoute = new TramTrackerGetStopsOnRoute();
        TramTrackerResponse<ArrayList<StopInformation>> stopsOnRoute = getStopsOnRoute.get(route_number, up);

        int start_stop_id = 0;
        int end_stop_id = 0;
        int start_stop_distance = -1;
        int end_stop_distance = -1;

        for (StopInformation stopInformation : stopsOnRoute.getResponseObject()) {
            // Cant use the first set characters number as sometimes the stop number isn't 1 but somethjing like D1 or 119 even though its stop 1
            int new_start_stop_name_distance = LevenshteinDistance.distance(stopInformation.getStopName(), this.start_stop.getStop_name());
            int new_end_stop_name_distance = LevenshteinDistance.distance(stopInformation.getStopName(), this.end_stop.getStop_name());
            //Log.i("TramRouteCreate", String.format("%s & %s: %d", stopInformation.getStopName(), this.start_stop.getStop_name(), distance)));

            if (new_start_stop_name_distance < start_stop_distance || start_stop_distance == -1) {
                start_stop_distance = new_start_stop_name_distance;
                start_stop_id = stopInformation.getStopNo();
            }

            if (new_end_stop_name_distance < end_stop_distance || end_stop_distance == -1) {
                end_stop_distance = new_end_stop_name_distance;
                end_stop_id = stopInformation.getStopNo();
            }
        }

        steps.add(new WaitStep(1, start_stop_id, route_number, start_stop_location));
        steps.add(new GetOnTramStep(2, route_number, start_stop_location));
        steps.add(new TouchOnMykiStep(3, null));
        steps.add(new WaitOnTramStep(4, route_number, null));
        steps.add(new PressStopButtonStep(5, null));
        steps.add(new TouchOffMykiStep(5, null));
        steps.add(new GetOffTramStep(6, null));
    }

    @Override
    public List<StoryboardStep> getRoute() {
        return this.steps;
    }

    @Override
    public float getDistance() {
        return this.distance_covered;
    }

    @Override
    public String toString() {
        return String.format("%s: %f (Location: %f,%f -> %f,%f) on tram %s", this.getEnd_stop().getStop_name(), this.getDistance(), this.getStart_stop().getStop_latitude(),
                this.getStart_stop().getStop_longitude(), this.getEnd_stop().getStop_latitude(), this.getEnd_stop().getStop_longitude(), this.route.getRoute_name());
    }
}
