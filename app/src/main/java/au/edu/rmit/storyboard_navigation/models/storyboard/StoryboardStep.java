package au.edu.rmit.storyboard_navigation.models.storyboard;

public abstract class StoryboardStep {
    final int step_number;
    final int pictogram_resource_id;
    String details;

    protected StoryboardStep(int step_number, int pictogram_resource_id) {
        this.step_number = step_number;
        this.pictogram_resource_id = pictogram_resource_id;
    }

    public int get_pictogram_resource_id() {
        return this.pictogram_resource_id;
    }

    public String get_details() {
        return this.details;
    }

    public int get_step_number() {
        return this.step_number;
    }
}
