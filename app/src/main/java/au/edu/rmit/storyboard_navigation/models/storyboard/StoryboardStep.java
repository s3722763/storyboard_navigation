package au.edu.rmit.storyboard_navigation.models.storyboard;

public abstract class StoryboardStep {
    int step_number;
    int pictogram_resource_id;
    String details;

    protected StoryboardStep(int step_number, int pictogram_resource_id) {
        this.step_number = step_number;
        this.pictogram_resource_id = pictogram_resource_id;
    }
}