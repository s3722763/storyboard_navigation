package au.edu.rmit.storyboard_navigation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    private final int[] pictogram_ids = {
        R.id.pictogram_first,
        R.id.pictogram_second,
        R.id.pictogram_third,
        R.id.pictogram_fourth,
        R.id.pictogram_fifth
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyboard_view_activity);

       for (int i = 0; i < pictogram_ids.length; i++) {
           ImageView imageView = (ImageView)this.findViewById(pictogram_ids[i]);
           imageView.setImageResource(steps.get(i).get_pictogram_resource_id());
       }
    }
}