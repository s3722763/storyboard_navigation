package au.edu.rmit.storyboard_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import au.edu.rmit.storyboard_navigation.models.tramtracker.RouteInfo;
import au.edu.rmit.storyboard_navigation.work.GetAllRoutesTask;
import au.edu.rmit.storyboard_navigation.work.TaskRunner;

public class MainActivity extends AppCompatActivity {
    List<RouteInfo> routes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        TaskRunner runner = new TaskRunner();

        runner.executeAsync(new GetAllRoutesTask(), (data) -> {
            routes = data.getResponseObject();
        });
    }

    public void openStoryboardView(View view) {
        Button button_pushed = (Button)view;
        CharSequence text = button_pushed.getText();

        Log.i("storyboard_navi_msg", "Pushed button with text " + text);

        Intent intent = new Intent(MainActivity.this, storyboard_view_activity.class);
        intent.putExtra("route_name", text);
        this.startActivity(intent);
    }
}