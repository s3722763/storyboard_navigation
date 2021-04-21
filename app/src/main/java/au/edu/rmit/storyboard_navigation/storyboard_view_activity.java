package au.edu.rmit.storyboard_navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import au.edu.rmit.storyboard_navigation.models.storyboard.CrossRoadStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.CrossRoadToStopStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.GetOffTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.GetOnTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.PressStopButtonStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.TouchOffMykiStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.TouchOnMykiStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WaitOnTramStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WaitStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WalkingStep;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;
import au.edu.rmit.storyboard_navigation.work.UpdateStoryboardView;

public class storyboard_view_activity extends AppCompatActivity {
    // Route 30 stop 10 is stopID 3660
    private List<StoryboardStep> steps;

    private final int[] step_number_ids = {
            R.id.step_number_first,
            R.id.step_number_second,
            R.id.step_number_third,
            R.id.step_number_fourth,
            R.id.step_number_fifth
    };

    private final int[] pictogram_ids = {
            R.id.pictogram_first,
            R.id.pictogram_second,
            R.id.pictogram_third,
            R.id.pictogram_fourth,
            R.id.pictogram_fifth
    };

    private final int[] exit_button_ids = {
            R.id.exit_button_1,
            R.id.exit_button_2,
            R.id.exit_button_3,
            R.id.exit_button_4,
            R.id.exit_button_5,
    };

    private final int step_description_ids[] = {
            R.id.step_description_first,
            R.id.step_description_second,
            R.id.step_description_third,
            R.id.step_description_fourth,
            R.id.step_description_fifth
    };

    Handler handler;
    UpdateStoryboardView updateStoryboardView;
    int counter = 0;
    TaskRunner taskRunner;
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    Timer timer;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;

    public storyboard_view_activity() {
        this.handler = new Handler();
        this.updateStoryboardView = new UpdateStoryboardView(this);
        taskRunner = new TaskRunner();
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        timer = new Timer();
        setupSteps();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        scheduledThreadPoolExecutor.scheduleAtFixedRate(timer, 5, 5, TimeUnit.SECONDS);
        setContentView(R.layout.activity_storyboard_view_activity);

        TextView view = (TextView) this.findViewById(R.id.destination_text_id);
        view.setText(this.getIntent().getCharSequenceExtra("route_name"));

        ImageButton exit_arrow_button = (ImageButton) this.findViewById(R.id.back_button_id);
        exit_arrow_button.setImageResource(R.drawable.arrow);

        setupLocation();
        this.update(false);
    }

    public void update(boolean increment) {
        Log.i("SBUpdate", "Updating");


        if (increment) {
            //this.handler.postDelayed(updateStoryboardView, 1000);
            counter += 1;
        } else {
            List<Thread> threads = new ArrayList<>(steps.size());

            for (StoryboardStep step : steps) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        step.update();
                    }
                });

                thread.start();
                threads.add(thread);
                //Log.i("AA", String.valueOf(step.get_step_number()));
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        boolean display_end_button = true;

        for (int id : this.exit_button_ids) {
            View view = this.findViewById(id);
            view.setVisibility(View.GONE);
        }

        for (int i = 0; i < pictogram_ids.length; i++) {
            TextView stepNumberTextView = (TextView) this.findViewById(step_number_ids[i]);
            ImageView imageView = (ImageView) this.findViewById(pictogram_ids[i]);
            TextView stepDescriptionTextView = (TextView) this.findViewById(step_description_ids[i]);

            if ((i + counter) < (steps.size() - 1)) {
                StoryboardStep step = steps.get(i + counter);

                stepNumberTextView.setText(String.valueOf(step.get_step_number()));
                imageView.setImageResource(step.get_pictogram_resource_id());
                stepDescriptionTextView.setText(step.get_details());
            } else {
                stepNumberTextView.setText("");
                imageView.setImageResource(0);
                stepDescriptionTextView.setText("");
                LinearLayout parent = (LinearLayout) stepNumberTextView.getParent();
                parent.setBackground(null);

            }
        }
    }

    public void backClick(View view) {
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.update(true);
        }

        return false;
    }

    void setupLocation() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // Can initialize location requests
                Log.i("SBN-Location", "Can initiate location requests");
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings not satisfied but can be fixed
                    Log.e("SBN-Location", "Incorrect location settings");
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("SBN-Location", "Incorrect permissions for location");
            return;
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

                for (Location location : locationResult.getLocations()) {
                    Log.i("SBN-Location", "Latitude: " + location.getLatitude() + "\tLongitude: " + location.getLongitude() + "\tAccuracy: " + location.getAccuracy());
                    currentLocation = location;
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void setupSteps() {
        steps = new ArrayList<StoryboardStep>(13);

        Location step1Location = new Location("");
        step1Location.setLatitude(-37.804507);
        step1Location.setLongitude(144.973703);
        steps.add(new WalkingStep(1, step1Location));

        Location step2Location = new Location("");
        step2Location.setLatitude(-37.807688);
        step2Location.setLongitude(144.973124);
        steps.add(new WalkingStep(2, step2Location));

        Location step3Location = new Location("");
        step3Location.setLatitude(-37.807715);
        step3Location.setLongitude(144.973349);
        steps.add(new CrossRoadStep(3, step3Location));

        Location step4Location = new Location("");
        step4Location.setLatitude(-37.807989);
        step4Location.setLongitude(144.973632);
         steps.add(new CrossRoadToStopStep(4, step4Location));

        Location step5Location = new Location("");
        step5Location.setLatitude(0.0);
        step5Location.setLongitude(0.0);
        steps.add(new WaitStep(5, 3660, 30, step5Location));

        Location step6Location = new Location("");
        step6Location.setLatitude(0.0);
        step6Location.setLongitude(0.0);
        steps.add(new GetOnTramStep(6, 30, step6Location));

        Location step7Location = new Location("");
        step7Location.setLatitude(0.0);
        step7Location.setLongitude(0.0);
        steps.add(new TouchOnMykiStep(7, step7Location));

        Location step8Location = new Location("");
        step8Location.setLatitude(0.0);
        step8Location.setLongitude(0.0);
        steps.add(new WaitOnTramStep(8, 30, step7Location));

        Location step9Location = new Location("");
        step9Location.setLatitude(0.0);
        step9Location.setLongitude(0.0);
        steps.add(new TouchOffMykiStep(9, step9Location));

        Location step10Location = new Location("");
        step10Location.setLatitude(0.0);
        step10Location.setLongitude(0.0);
        steps.add(new GetOffTramStep(10, step10Location));

        Location step11Location = new Location("");
        step11Location.setLatitude(-37.809555);
        step11Location.setLongitude(144.963901);
        steps.add(new WalkingStep(11, step11Location));

        Location step12Location = new Location("");
        step12Location.setLatitude(-37.809596);
        step12Location.setLongitude(144.963734);
        steps.add(new CrossRoadStep(12, step12Location));

        Location step13Location = new Location("");
        step13Location.setLatitude(-37.808093);
        step13Location.setLongitude(144.963032);
        steps.add(new WalkingStep(13, step13Location));
    }


    private class Timer implements Runnable {
        @Override
        public void run() {
            Log.i("AA", "AA");
            update(false);
            Log.i("AA", "BB");
        }
    }
}