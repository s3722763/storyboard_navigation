package au.edu.rmit.storyboard_navigation.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

import au.edu.rmit.storyboard_navigation.storyboard_view_activity;

public class UpdateStoryboardView implements Runnable {
    // Use weak reference to avoid memory leak
    private final WeakReference<storyboard_view_activity> activity;

    public UpdateStoryboardView(storyboard_view_activity activity) {
        this.activity = new WeakReference<storyboard_view_activity>(activity);
    }

    @Override
    public void run() {
        storyboard_view_activity activity = this.activity.get();

        if (activity != null) {
            activity.update(true);
        }
    }
}
