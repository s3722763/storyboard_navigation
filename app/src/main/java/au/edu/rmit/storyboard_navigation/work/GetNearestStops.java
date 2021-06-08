package au.edu.rmit.storyboard_navigation.work;

import android.location.Location;
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
import java.util.Locale;

import au.edu.rmit.storyboard_navigation.models.ptv.NearestStopRequest;

public class GetNearestStops {
    public NearestStopRequest get(Location location) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            String uri = String.format(Locale.US, "/v3/stops/location/%f,%f?route_types=1&max_distance=600", location.getLatitude(), location.getLongitude());
            String urlString = PTVCalculateURLSignature.buildTTAPIURL("http://timetableapi.ptv.vic.gov.au", uri);
            System.out.println(urlString);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            Log.i("GetNearestStop", connection.getResponseMessage());

            JavaType type = mapper.getTypeFactory().constructType(NearestStopRequest.class);

            return mapper.readValue(responseStream, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
