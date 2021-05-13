package au.edu.rmit.storyboard_navigation.models.storyboard;

import android.location.Location;

import au.edu.rmit.storyboard_navigation.R;

public class TurnStep extends StoryboardStep {
    public TurnStep(int step_number, boolean left_turn, Location location) {
        super(step_number, R.drawable.walkpictogram, "Turn right", location);

        if (left_turn) {
            this.pictogram_resource_id = R.drawable.walkpictogram;
            this.details = "Turn left";
        }
    }

    @Override
    public void update() {

    }
}
