package au.edu.rmit.storyboard_navigation.task;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskRunner {
    //(Minimum number of threads, maximum number of threads, time in TimeUnit, TimeUnit, queue)
    private final Executor executor = new ThreadPoolExecutor(5, 12, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private final Handler handler = new Handler(Looper.getMainLooper());

    public interface Callback<T> {
        void onComplete(T result);
    }

    public <T> void executeAsync(Callable<T> callable, Callback<T> callback) {
        executor.execute(() -> {
            try {
                final T result = callable.call();
                handler.post(() -> {
                    callback.onComplete(result);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
