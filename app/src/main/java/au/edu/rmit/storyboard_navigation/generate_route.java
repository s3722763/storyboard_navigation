package au.edu.rmit.storyboard_navigation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import au.edu.rmit.storyboard_navigation.exceptions.AutoGenerateRouteException;
import au.edu.rmit.storyboard_navigation.models.nominatim.SearchResponce;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.work.GenerateStoryboardRoute;
import au.edu.rmit.storyboard_navigation.work.GetLocationFromName;

public class generate_route extends AppCompatActivity {
    private final int SHOW_STORYBOARD_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_route);
    }

    public void generateRouteClick(View view) throws InterruptedException {
        String from = ((TextView)this.findViewById(R.id.fromDestinationID)).getText().toString();
        String to = ((TextView)this.findViewById(R.id.toDestinationID)).getText().toString();
        ((Button)this.findViewById(R.id.generateRouteButton)).setClickable(false);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.generateRouteProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        Log.i("SBNGenRoute", String.format("Generating route from %s to %s", from, to));

        ArrayList<StoryboardStep> steps = new ArrayList<>();

        AtomicInteger progress = new AtomicInteger(0);

        Thread generate_thread = new Thread(new Runnable() {
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
                    generateStoryboardRoute.run(startLocation, endLocation, steps, progress);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (AutoGenerateRouteException e) {
                    CharSequence charSequence = e.reason;
                    Snackbar snackbar = Snackbar.make(view, charSequence, BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        Thread update_progress_bar_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress.get() < 100) {

                    progressBar.setProgress(progress.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    generate_thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(generate_route.this, storyboard_view_activity.class);
                intent.putParcelableArrayListExtra("storyboard", steps);
                intent.putExtra("route_name", from + " to " + to);
                startActivityForResult(intent, SHOW_STORYBOARD_CODE);
            }
        });

        generate_thread.start();
        update_progress_bar_thread.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHOW_STORYBOARD_CODE) {
            this.finish();
        }
    }
}