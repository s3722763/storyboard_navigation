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
import java.util.concurrent.Callable;

import au.edu.rmit.storyboard_navigation.models.tramtracker.ArrivalPrediction;
import au.edu.rmit.storyboard_navigation.models.tramtracker.TramTrackerResponse;

public class ArrivalPredictionTask implements Callable<TramTrackerResponse<ArrayList<ArrivalPrediction>>> {
    private final int stopNo;
    private final int routeNo;
    private final String base_url = "http://tramtracker.com.au/Controllers/GetNextPredictionsForStop.ashx?stopNo=%d&routeNo=%d&isLowFloor=false";

    public ArrivalPredictionTask(int stopNo, int routeNo) {
        this.stopNo = stopNo;
        this.routeNo = routeNo;
    }

    public TramTrackerResponse<ArrayList<ArrivalPrediction>> get() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        try {
            String urlString = String.format(base_url, this.stopNo, this.routeNo);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());

            JavaType route_collection = mapper.getTypeFactory().constructCollectionType(ArrayList.class, ArrivalPrediction.class);
            JavaType type = mapper.getTypeFactory().constructParametricType(TramTrackerResponse.class, route_collection);

            return mapper.readValue(responseStream, type);
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        return null;
    }

    @Override
    public TramTrackerResponse<ArrayList<ArrivalPrediction>> call() throws Exception {
        return get();
    }
}
