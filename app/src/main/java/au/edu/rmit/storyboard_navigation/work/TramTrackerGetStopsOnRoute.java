package au.edu.rmit.storyboard_navigation.work;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import au.edu.rmit.storyboard_navigation.models.tramtracker.StopInformation;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;

public class TramTrackerGetStopsOnRoute {
    private final String base_url = "http://tramtracker.com.au/Controllers/GetStopsByRouteAndDirection.ashx?r=%d&u=%s";

    public TramTrackerResponse<ArrayList<StopInformation>>get(int route_id, String up) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            // Trams are route_type 1
            String urlString = String.format(Locale.US, base_url, route_id, up);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, StopInformation.class);
            JavaType type = mapper.getTypeFactory().constructParametricType(TramTrackerResponse.class, collectionType);

            return mapper.readValue(responseStream, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
