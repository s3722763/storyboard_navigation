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

import au.edu.rmit.storyboard_navigation.models.osrm.OSRMNearest;

public class OSRMGetNearestStreetName {
    public OSRMNearest get(Location location) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            String url_str = String.format("http://router.project-osrm.org/nearest/v1/driving/%f,%f", location.getLatitude(), location.getLongitude());
            //System.out.println(url_str);
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType type = mapper.getTypeFactory().constructType(OSRMNearest.class);

            return mapper.readValue(responseStream, type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
