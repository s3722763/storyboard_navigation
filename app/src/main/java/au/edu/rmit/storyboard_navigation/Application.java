package au.edu.rmit.storyboard_navigation;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Application extends android.app.Application implements Configuration.Provider {
    @NonNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setExecutor(new ThreadPoolExecutor(5, 12, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()))
                .setMinimumLoggingLevel((Log.VERBOSE))
                .build();
    }
}
