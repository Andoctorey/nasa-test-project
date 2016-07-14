package by.yegorov.nasa.core.job;

import com.path.android.jobqueue.log.CustomLogger;

import by.yegorov.nasa.BuildConfig;
import timber.log.Timber;

@SuppressWarnings("unused")
public class TimberJobQueueLogger implements CustomLogger {
    @Override
    public boolean isDebugEnabled() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void d(String text, Object... args) {
        Timber.d(text, args);
    }

    @Override
    public void e(Throwable t, String text, Object... args) {
        Timber.e(t, text, args);
    }

    @Override
    public void e(String text, Object... args) {
        Timber.e(text, args);
    }
}
