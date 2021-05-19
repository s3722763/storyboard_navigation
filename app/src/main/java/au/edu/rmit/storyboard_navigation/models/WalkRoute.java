package au.edu.rmit.storyboard_navigation.models;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.storyboard_navigation.models.osrm.Leg;
import au.edu.rmit.storyboard_navigation.models.osrm.Maneuver;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMGetRouteResponse;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMNearest;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMRouteInfo;
import au.edu.rmit.storyboard_navigation.models.osrm.Step;
import au.edu.rmit.storyboard_navigation.models.storyboard.ForkStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.TurnStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WalkingStep;
import au.edu.rmit.storyboard_navigation.work.OSRMGetNearestStreetName;
import au.edu.rmit.storyboard_navigation.work.OSRMGetRoute;

public class WalkRoute implements Route {
    private OSRMRouteInfo osrmRoute;
    private final Location startLocation;
    private final Location endLocation;
    private List<StoryboardStep> steps;

    public WalkRoute(Location startLocation, Location endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.steps = new ArrayList<>();
    }

    @Override
    public void createRoute() {
        System.out.printf("Getting route from: %s to %s%n", this.startLocation, this.endLocation);

        OSRMGetRoute osrmGetRoute = new OSRMGetRoute();
        OSRMGetRouteResponse osrmGetRouteResponse = osrmGetRoute.get(this.startLocation, this.endLocation);
        this.osrmRoute = osrmGetRouteResponse.getRoutes().get(0);
        int starting_step = 1;

        for (Leg leg : this.osrmRoute.getLegs()) {
            for (Step step : leg.getSteps()) {
                Maneuver maneuver = step.getManeuver();

                switch (maneuver.getType()) {
                    case "depart":
                        // Turn direction
                        Log.e("SBNAutoGen", "Depart not impemented for step generation");
                        break;
                    case "turn": {
                        boolean turn_left = false;

                        if (maneuver.getModifier().equals("left")) {
                            turn_left = true;
                        } else if (maneuver.getModifier().equals("right")) {
                            turn_left = false;
                        } else if (maneuver.getModifier().equals("slight left")) {
                            turn_left = true;
                        } else if (maneuver.getModifier().equals("slight right")) {
                            turn_left = false;
                        } else {
                            Log.e("SBNAutoGen", "Unspecified turn condition: " + maneuver.getModifier());
                        }
                        Location location = new Location("");
                        location.setLatitude(maneuver.getLocation()[0]);
                        location.setLongitude(maneuver.getLocation()[1]);
                        TurnStep turnStep = new TurnStep(starting_step++, turn_left, location);
                        this.steps.add(turnStep);
                        OSRMGetNearestStreetName osrmGetNearestStreetName = new OSRMGetNearestStreetName();

                        OSRMNearest nearest = osrmGetNearestStreetName.get(location);

                        String street_name = nearest.getWaypoints().get(0).getName();
                        WalkingStep walkingStep = new WalkingStep(starting_step++, street_name, location);
                        this.steps.add(walkingStep);
                        break;
                    }
                    case "fork": {
                        Location location = new Location("");
                        location.setLatitude(maneuver.getLocation()[0]);
                        location.setLongitude(maneuver.getLocation()[1]);
                        ForkStep forkStep = new ForkStep(starting_step++, maneuver.getModifier(), location);
                        this.steps.add(forkStep);
                        break;
                    }
                    case "arrive": {
                        Location location = new Location("");
                        location.setLatitude(maneuver.getLocation()[0]);
                        location.setLongitude(maneuver.getLocation()[1]);
                        OSRMGetNearestStreetName osrmGetNearestStreetName = new OSRMGetNearestStreetName();
                        OSRMNearest nearest = osrmGetNearestStreetName.get(location);

                        String street_name = nearest.getWaypoints().get(0).getName();

                        WalkingStep walkStep = new WalkingStep(starting_step++, street_name, location);

                        this.steps.add(walkStep);
                        break;
                    }
                    case "end of road": {
                        boolean turn_left = false;

                        if (maneuver.getModifier().equals("left")) {
                            turn_left = true;
                        } else if (maneuver.getModifier().equals("right")) {
                            turn_left = false;
                        } else {
                            Log.e("SBNAutoGen", "Unspecified turn condition for end of road: " + maneuver.getModifier());
                        }
                        Location location = new Location("");
                        location.setLatitude(maneuver.getLocation()[0]);
                        location.setLongitude(maneuver.getLocation()[1]);
                        TurnStep turnStep = new TurnStep(starting_step++, turn_left, location);
                        this.steps.add(turnStep);
                        break;
                    }
                    default:
                        System.out.println(step.getManeuver().getType() + ": " + step.getManeuver().getModifier());
                        break;
                }
            }
        }

        System.out.println();
    }

    @Override
    public List<StoryboardStep> getRoute() {
        return this.steps;
    }

    @Override
    public float getDistance() {
        return this.osrmRoute.getDistance();
    }

    @Override
    public Location getStartLocation() {
        return this.startLocation;
    }

    @Override
    public Location getEndLocation() {
        return this.endLocation;
    }

    @Override
    public String toString() {
        return String.format("Walking: %f (Location: %f,%f -> %f,%f)\nURL: %s", this.getDistance(),this.getStartLocation().getLatitude(), this.getStartLocation().getLongitude(),
        this.endLocation.getLatitude(), this.endLocation.getLongitude(), this.osrmRoute.getUrl());
    }
}
