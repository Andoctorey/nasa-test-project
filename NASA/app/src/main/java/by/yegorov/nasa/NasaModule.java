package by.yegorov.nasa;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;
import com.squareup.otto.Bus;

import java.util.Date;

import by.yegorov.nasa.core.api.gson.DateAdapter;
import by.yegorov.nasa.core.bus.AndroidBus;
import by.yegorov.nasa.core.dagger.PerApp;
import by.yegorov.nasa.core.job.BaseJob;
import by.yegorov.nasa.core.job.TimberJobQueueLogger;
import dagger.Module;
import dagger.Provides;

@SuppressWarnings("unused")
@Module()
public class NasaModule {

    final NasaApp app;

    public NasaModule(NasaApp app) {
        this.app = app;
    }

    @Provides
    Context provideContext() {
        return app.getBaseContext();
    }

    @Provides
    @PerApp
    NasaApp provideAluaApp() {
        return app;
    }

    @Provides
    @PerApp
    Application provideApplication(NasaApp app) {
        return app;
    }

    @Provides
    @PerApp
    JobManager provideJobManager(final Context context) {
        Configuration configuration = new Configuration.Builder(context)
                .customLogger(new TimberJobQueueLogger())
                .loadFactor(1)
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        ((BaseJob) job).inject(context);
                    }
                })
                .build();
        return new JobManager(context, configuration);
    }

    @Provides
    @PerApp
    Bus provideBus() {
        return new AndroidBus();
    }

    @Provides
    @PerApp
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateAdapter())
                .create();
    }
}
