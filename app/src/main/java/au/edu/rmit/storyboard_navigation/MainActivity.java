package au.edu.rmit.storyboard_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import au.edu.rmit.storyboard_navigation.models.tramtracker.RouteInfo;
import au.edu.rmit.storyboard_navigation.task.GetAllRoutesTask;
import au.edu.rmit.storyboard_navigation.task.TaskRunner;

public class MainActivity extends AppCompatActivity {
    List<RouteInfo> routes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        TaskRunner runner = new TaskRunner();

        runner.executeAsync(new GetAllRoutesTask(), (data) -> {
            routes = data.getResponseObject();

        });
    }

    public void openStoryboardView(View view) {
        Intent intent = new Intent(MainActivity.this, storyboard_view_activity.class);
        this.startActivity(intent );
    }
}