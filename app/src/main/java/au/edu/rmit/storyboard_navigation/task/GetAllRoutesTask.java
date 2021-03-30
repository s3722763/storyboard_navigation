package au.edu.rmit.storyboard_navigation.task;

import android.util.Log;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import au.edu.rmit.storyboard_navigation.models.tramtracker.RouteInfo;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;

public class GetAllRoutesTask implements Callable<TramTrackerResponse<ArrayList<RouteInfo>>> {
    public  TramTrackerResponse<ArrayList<RouteInfo>> get() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            URL url = new URL("http://tramtracker.com.au/Controllers/GetAllRoutes.ashx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType route_collection = mapper.getTypeFactory().constructCollectionType(ArrayList.class, RouteInfo.class);
            JavaType type = mapper.getTypeFactory().constructParametricType(TramTrackerResponse.class, route_collection);

            return mapper.readValue(responseStream, type);
        } catch (IOException e) {
            Log.e("storyboard_navi_msg", e.getMessage());
        }

        return null;
    }

    @Override
    public TramTrackerResponse<ArrayList<RouteInfo>> call() throws Exception {
        return get();
    }
}
