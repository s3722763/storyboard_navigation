package au.edu.rmit.storyboard_navigation.work;

import android.location.Location;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import au.edu.rmit.storyboard_navigation.models.osrm.OSRMGetRouteResponse;

public class OSRMGetRoute {
    public OSRMGetRouteResponse get(Location start, Location end) {
        String url_str = String.format("https://routing.openstreetmap.de/routed-foot/route/v1/driving/%f,%f;%f,%f?overview=false&steps=true&alternatives=true",
                start.getLongitude(), start.getLatitude(), end.getLongitude(), end.getLatitude());
        //System.out.println(url_str);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        String debug_route_url = "https://www.openstreetmap.org/directions?engine=fossgis_osrm_foot&route="+  start.getLatitude() + "%2C" + start.getLongitude() + "%3B" +
                end.getLatitude() + "%2C" + end.getLongitude();
        System.out.println(debug_route_url);

        try {
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType type = mapper.getTypeFactory().constructType(OSRMGetRouteResponse.class);

            OSRMGetRouteResponse response = mapper.readValue(responseStream, type);
            response.setUrl(url_str);

            return  response;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
