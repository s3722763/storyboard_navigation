package au.edu.rmit.storyboard_navigation;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private List<StoryboardStep> steps = new ArrayList<StoryboardStep>(Arrays.asList(
            new WalkingStep(1),
            new CrossRoadStep(2),
            new CrossRoadToStopStep(3),
            new WaitStep(4, 1555, 0),
            new GetOnTramStep(5, 1),
            new TouchOnMykiStep(6),
            new WaitOnTramStep(7, 1),
            new PressStopButtonStep(8),
            new TouchOffMykiStep(9),
            new GetOffTramStep(10)
    ));

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

    public storyboard_view_activity() {
        this.handler = new Handler();
        this.updateStoryboardView = new UpdateStoryboardView(this);
        taskRunner = new TaskRunner();
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        timer = new Timer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        scheduledThreadPoolExecutor.scheduleAtFixedRate(timer, 5, 5, TimeUnit.SECONDS);
        setContentView(R.layout.activity_storyboard_view_activity);

        TextView view  = (TextView)this.findViewById(R.id.destination_text_id);
        view.setText(this.getIntent().getCharSequenceExtra("route_name"));

        ImageButton button = (ImageButton)this.findViewById(R.id.back_button_id);
        button.setImageResource(R.drawable.arrow);

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

    private class Timer implements Runnable {
        @Override
        public void run() {
            Log.i("AA", "AA");
            update(false);
            Log.i("AA", "BB");
        }
    }
}