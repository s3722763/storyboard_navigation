package au.edu.rmit.storyboard_navigation.work;

import android.app.Dialog;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import au.edu.rmit.storyboard_navigation.exceptions.AutoGenerateRouteException;
import au.edu.rmit.storyboard_navigation.models.Route;
import au.edu.rmit.storyboard_navigation.models.TramRoute;
import au.edu.rmit.storyboard_navigation.models.WalkRoute;
import au.edu.rmit.storyboard_navigation.models.ptv.NearestStop;
import au.edu.rmit.storyboard_navigation.models.ptv.NearestStopRequest;
import au.edu.rmit.storyboard_navigation.models.ptv.PTVRoute;
import au.edu.rmit.storyboard_navigation.models.ptv.PTVRouteStop;
import au.edu.rmit.storyboard_navigation.models.storyboard.DestinationStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;

public class GenerateStoryboardRoute {
    public void run(Location startLocation, Location endLocation, List<StoryboardStep> steps, AtomicInteger progress) throws IOException, AutoGenerateRouteException {
        GetNearestStops getNearestStops = new GetNearestStops();
        NearestStopRequest response = getNearestStops.get(startLocation);

        Log.i("SBN-AutoGen", "Found " + response.getStops().size() + " stops");
        float shortest_route_distance = 0;
        List<Route> shortestRoute = new ArrayList<Route>();

        if (response.getStops().size() == 0) {
            throw new AutoGenerateRouteException("No stops found near you");
        }

        for (NearestStop stop : response.getStops()) {
            //Log.i("SBN-AutoGen", stop.getStop_name());

            Location toLocation = new Location("");
            toLocation.setLatitude(stop.getStop_latitude());
            toLocation.setLongitude(stop.getStop_longitude());

            float distance_to_first_stop = toLocation.distanceTo(startLocation);

            GetStopsOnRoute getStops = new GetStopsOnRoute();

            //Log.i("SBN-AutoGen", toLocation.getDistance(fromLocation));
            for (PTVRoute route : stop.getRoute()) {
                int route_id = route.getRoute_id();
                //Log.i("SBN-AutoGen", route_id);
                ArrayList<PTVRouteStop> stops = getStops.get(route_id).getStops();

                int index = -1;

                for (int i = 0; i < stops.size(); i++) {
                    PTVRouteStop routeStop = stops.get(i);
                    // TODO: Check order of stops
                    if (routeStop.getStop_id() == stop.getStop_id()) {
                        index = i;
                        break;
                    }
                }

                PTVRouteStop originalStop = stops.get(index);
                PTVRouteStop targetStop = stops.get(index);
                // Number of stops getting further from the destination
                // This tries to account for routes were the tram will go further away before coming back closer
                int num_stop_getting_further = 0;
                Location targetLocation = new Location("");
                targetLocation.setLatitude(stops.get(index).getStop_latitude());
                targetLocation.setLongitude(stops.get(index).getStop_longitude());

                Location startingLocation = new Location("");
                startingLocation.setLatitude(stops.get(index).getStop_latitude());
                startingLocation.setLongitude(stops.get(index).getStop_longitude());

                if (index >= 0) {
                    float distance_to_destination = targetLocation.distanceTo(endLocation);

                    for (int i = index; i < stops.size(); i++) {
                        PTVRouteStop nextStop = stops.get(i);
                        Location nextStopLocation = new Location("");
                        nextStopLocation.setLatitude(nextStop.getStop_latitude());
                        nextStopLocation.setLongitude(nextStop.getStop_longitude());
                        float next_stop_distance = nextStopLocation.distanceTo(endLocation);

                        if (next_stop_distance  < distance_to_destination) {
                            targetStop = nextStop;
                            distance_to_destination = next_stop_distance;
                            targetLocation = nextStopLocation;
                            num_stop_getting_further = 0;
                        } else {
                            num_stop_getting_further += 1;
                        }

                        if (num_stop_getting_further >= 8) {
                            break;
                        }
                    }

                    index += 1;
                }

                progress.set(50);

                // Dont want city circle trams
                if (targetLocation.distanceTo(startingLocation) != 0 && !route.getRoute_name().contains("City Circle")) {
                    // TODO: Change for multiple potential routes
                    float distance_to_end = targetLocation.distanceTo(endLocation);
                    float tram_distance = targetLocation.distanceTo(startingLocation);

                    float total_distance = distance_to_first_stop + tram_distance + distance_to_end;

                    System.out.printf("Comparing distance %f < %f: ",  total_distance, shortest_route_distance);

                    if (shortestRoute.size() == 0) {
                        Log.i("SBN-AutoGen", "Empty");
                        shortest_route_distance = total_distance;
                        /*System.out.printf("Getting route from: %s to %s%n", startLocation, toLocation);
                        System.out.printf("Getting route from: %s to %s%n", startingLocation, targetLocation);
                        System.out.printf("Getting route from: %s to %s%n", targetLocation, endLocation);*/
                        shortestRoute.add(new WalkRoute(startLocation, toLocation));
                        shortestRoute.add(new TramRoute(originalStop, targetStop, startingLocation, targetLocation, route));
                        shortestRoute.add(new WalkRoute(targetLocation, endLocation));
                    } else if (total_distance < shortest_route_distance) {
                        Log.i("SBN-AutoGen", "Yes");
                        shortest_route_distance = total_distance;
                        shortestRoute.clear();
                        shortestRoute.add(new WalkRoute(startLocation, toLocation));
                        shortestRoute.add(new TramRoute(originalStop, targetStop, startingLocation, targetLocation, route));
                        shortestRoute.add(new WalkRoute(targetLocation, endLocation));
                    } else {
                        Log.i("SBN-AutoGen", "No");
                    }
                }/* else {

                    // No change in stops
                    //Log.i("SBN-AutoGen", "Dont take this tram route");
                }*/
            }
        }

        steps.clear();

        int start_step = 1;
        for (Route r : shortestRoute) {
            r.createRoute(start_step);
            start_step = start_step + r.getRoute().size();
            //Log.i("SBN-AutoGen", step.get_details());
            steps.addAll(r.getRoute());
        }

        progress.set(100);
    }
}
