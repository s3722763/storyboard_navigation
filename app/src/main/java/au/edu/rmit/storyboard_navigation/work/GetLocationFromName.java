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

import au.edu.rmit.storyboard_navigation.models.nominatim.SearchResponce;
import au.edu.rmit.storyboard_navigation.models.tramtracker.ArrivalPrediction;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;

public class GetLocationFromName {
    public ArrayList<SearchResponce> get(String query) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            query = query.replace(' ', '+');
            String urlString = String.format("https://nominatim.openstreetmap.org/search?q=%s&format=json", query);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType search_collection = mapper.getTypeFactory().constructCollectionType(ArrayList.class, SearchResponce.class);
            JavaType type = mapper.getTypeFactory().constructType(search_collection);

            return mapper.readValue(responseStream, type);
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        return null;
    }
}
