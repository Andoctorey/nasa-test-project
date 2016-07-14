package by.yegorov.nasa;


import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import timber.log.Timber;

public class NasaApp extends Application {

    private NasaAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogging();
        initRuntimeErrorsHandling();
        component = DaggerNasaAppComponent.builder()
                .nasaModule(new NasaModule((this)))
                .build();
    }

    public static NasaAppComponent getComponent(Context context) {
        return ((NasaApp) context.getApplicationContext()).component;
    }

    private void initRuntimeErrorsHandling() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyFlashScreen().penaltyLog().build());
        } else {
            //TODO init crash logging service
        }
    }

    private void initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
