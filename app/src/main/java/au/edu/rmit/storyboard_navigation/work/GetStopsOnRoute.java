package au.edu.rmit.storyboard_navigation.work;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import au.edu.rmit.storyboard_navigation.models.ptv.GetStopsOnRouteResponse;

public class GetStopsOnRoute {
    private final String base_url = "/v3/stops/route/%d/route_type/%d";

    public GetStopsOnRouteResponse get(int route_id) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            // Trams are route_type 1
            String baseUrl = String.format(base_url, route_id, 1);
            String urlString = PTVCalculateURLSignature.buildTTAPIURL("http://timetableapi.ptv.vic.gov.au", baseUrl);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType type = mapper.getTypeFactory().constructType(GetStopsOnRouteResponse.class);

            return mapper.readValue(responseStream, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
