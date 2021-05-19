package au.edu.rmit.storyboard_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.storyboard_navigation.models.nominatim.SearchResponce;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.work.GenerateStoryboardRoute;
import au.edu.rmit.storyboard_navigation.work.GetLocationFromName;

public class generate_route extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_route);
    }

    public void generateRouteClick(View view) throws InterruptedException {
        String from = ((TextView)this.findViewById(R.id.fromDestinationID)).getText().toString();
        String to = ((TextView)this.findViewById(R.id.toDestinationID)).getText().toString();
        Log.i("SBNGenRoute", String.format("Generating route from %s to %s", from, to));

        ArrayList<StoryboardStep> steps = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GenerateStoryboardRoute generateStoryboardRoute = new GenerateStoryboardRoute();
                GetLocationFromName getLocationFromName = new GetLocationFromName();
                List<SearchResponce> fromLocations = getLocationFromName.get(from);
                List<SearchResponce> toLocations = getLocationFromName.get(to);

                Location startLocation = new Location("");
                startLocation.setLatitude(fromLocations.get(0).getLat());
                startLocation.setLongitude(fromLocations.get(0).getLon());
                Location endLocation = new Location("");
                endLocation.setLatitude(toLocations.get(0).getLat());
                endLocation.setLongitude(toLocations.get(0).getLon());

                try {
                    generateStoryboardRoute.run(startLocation, endLocation, steps);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread.join();
        Intent intent = new Intent(generate_route.this, storyboard_view_activity.class);
        intent.putParcelableArrayListExtra("storyboard", steps);
        this.startActivity(intent);
    }
}