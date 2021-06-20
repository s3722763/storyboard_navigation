package au.edu.rmit.storyboard_navigation.models;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import au.edu.rmit.storyboard_navigation.models.osrm.Leg;
import au.edu.rmit.storyboard_navigation.models.osrm.Maneuver;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMGetRouteResponse;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMNearest;
import au.edu.rmit.storyboard_navigation.models.osrm.OSRMRouteInfo;
import au.edu.rmit.storyboard_navigation.models.osrm.Step;
import au.edu.rmit.storyboard_navigation.models.osrm.Waypoint;
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
    public void createRoute(int start_step) {
        System.out.printf("Getting route from: %s to %s%n", this.startLocation, this.endLocation);

        OSRMGetRoute osrmGetRoute = new OSRMGetRoute();
        OSRMGetRouteResponse osrmGetRouteResponse = osrmGetRoute.get(this.startLocation, this.endLocation);

        this.osrmRoute = osrmGetRouteResponse.getRoutes().get(0);
        int starting_step = start_step;

        for (Leg leg : this.osrmRoute.getLegs()) {
            for (int i = 0; i < leg.getSteps().size(); i++) {
                Step step = leg.getSteps().get(i);
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

                        Maneuver new_maneuver = leg.getSteps().get(i + 1).getManeuver();
                        Location location_from = new Location("");
                        location_from.setLatitude(maneuver.getLocation()[0]);
                        location_from.setLongitude(maneuver.getLocation()[1]);

                        Location location_to = new Location("");
                        location_to.setLatitude(new_maneuver.getLocation()[0]);
                        location_to.setLongitude(new_maneuver.getLocation()[1]);

                        OSRMGetNearestStreetName osrmGetNearestStreetName = new OSRMGetNearestStreetName();
                        OSRMNearest nearest_from = osrmGetNearestStreetName.get(location_from);
                        OSRMNearest nearest_to = osrmGetNearestStreetName.get(location_to);

                        String details = "";

                        if (nearest_from.getWaypoints().size() == 1 && nearest_to.getWaypoints().size() == 1) {
                            if (nearest_from.getWaypoints().get(0).getName().equals("")) {
                                details = "to " + nearest_to.getWaypoints().get(0).getName();
                            } else if (nearest_to.getWaypoints().get(0).getName().equals("")) {
                                details = "away from " + nearest_from.getWaypoints().get(0).getName();
                            } else {
                                details = "along " + determineStreet(nearest_from.getWaypoints(), nearest_to.getWaypoints());
                            }
                        } else {
                            details = "along " + determineStreet(nearest_from.getWaypoints(), nearest_to.getWaypoints());
                        }

                        details += " for " + (int)Math.floor(step.getDistance()) + " metres";

                        WalkingStep walkingStep = new WalkingStep(starting_step++, details, location_to);
                        this.steps.add(walkingStep);

                        TurnStep turnStep = new TurnStep(starting_step++, turn_left, location_from);
                        this.steps.add(turnStep);
                        break;
                    }
                    case "fork": {
                        Location location = new Location("");
                        location.setLatitude(maneuver.getLocation()[0]);
                        location.setLongitude(maneuver.getLocation()[1]);

                        OSRMGetNearestStreetName osrmGetNearestStreetName = new OSRMGetNearestStreetName();
                        OSRMNearest nearest = osrmGetNearestStreetName.get(location);
                        String details = "to " + nearest.getWaypoints().get(0).getName();
                        details += " for " + (int)Math.floor(step.getDistance()) + " metres";
                        WalkingStep walkingStep = new WalkingStep(starting_step++, details, location);
                        this.steps.add(walkingStep);
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

                        WalkingStep walkStep = new WalkingStep(starting_step++, " along " + street_name + " for " + (int)Math.floor(step.getDistance()) + " metres", location);

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
                        WalkingStep walkingStep = new WalkingStep(starting_step++, "", location);
                        this.steps.add(walkingStep);
                        TurnStep turnStep = new TurnStep(starting_step++, turn_left, location);
                        this.steps.add(turnStep);
                        break;
                    }
                    default:
                        Log.e("SBNAutoGen", "Step now implemented: " + step.getManeuver().getType() + ": " + step.getManeuver().getModifier());
                        break;
                }
            }
        }

        System.out.println();
    }

    private String determineStreet(ArrayList<Waypoint> waypoints_from, ArrayList<Waypoint> waypoints_to) {
        TreeMap<Float, String> streets = new TreeMap<Float, String>();

        for (Waypoint waypoint_from : waypoints_from) {
            for (Waypoint waypoint_to : waypoints_to) {
                if (waypoint_from.getName().equals(waypoint_to.getName())) {
                    streets.put((waypoint_to.getDistance() + waypoint_from.getDistance()) / 2, waypoint_from.getName());
                }
            }
        }

        if (streets.size() == 0) {
            // If it got here it means that no street is close to any of the waypoints
            // Waypoints are already ordered from closest to furthest
            return waypoints_from.get(0).getName() + " to " + waypoints_to.get(0).getName();
        }

        return streets.firstEntry().getValue();
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
