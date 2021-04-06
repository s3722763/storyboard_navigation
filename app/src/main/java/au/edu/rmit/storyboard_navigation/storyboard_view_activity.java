package au.edu.rmit.storyboard_navigation;

import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.CharSequenceTransformation;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import au.edu.rmit.storyboard_navigation.work.UpdateStoryboardView;

public class storyboard_view_activity extends AppCompatActivity {
    private List<StoryboardStep> steps = new ArrayList<StoryboardStep>(Arrays.asList(
            new WalkingStep(1),
            new CrossRoadStep(2),
            new CrossRoadToStopStep(3),
            new WaitStep(4),
            new GetOnTramStep(5),
            new TouchOnMykiStep(6),
            new WaitOnTramStep(7),
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

    public storyboard_view_activity() {
        this.handler = new Handler();
        this.updateStoryboardView = new UpdateStoryboardView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyboard_view_activity);

        this.update();
    }

    public void update() {
        Log.i("SBUpdate", "Updating");

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

        //this.handler.postDelayed(updateStoryboardView, 1000);
        counter += 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.update();
        }

        return false;
    }
}