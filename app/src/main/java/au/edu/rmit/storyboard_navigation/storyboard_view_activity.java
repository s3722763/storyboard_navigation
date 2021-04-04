package au.edu.rmit.storyboard_navigation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.storyboard_navigation.models.storyboard.StoryboardStep;
import au.edu.rmit.storyboard_navigation.models.storyboard.WalkingStep;

public class storyboard_view_activity extends AppCompatActivity {
    private List<StoryboardStep> steps;
    private int[] pictogram_ids = {
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

        steps = new ArrayList<>();
        steps.add(new WalkingStep(1));
        steps.add(new WalkingStep(2));
        steps.add(new WalkingStep(3));
        steps.add(new WalkingStep(4));
        steps.add(new WalkingStep(5));

       for (int i = 0; i < pictogram_ids.length; i++) {
           ImageView imageView = (ImageView)this.findViewById(pictogram_ids[i]);
           imageView.setImageResource(steps.get(i).get_pictogram_resource_id());
       }
    }
}