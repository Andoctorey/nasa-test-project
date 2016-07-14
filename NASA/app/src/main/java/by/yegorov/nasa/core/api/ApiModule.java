package by.yegorov.nasa.core.api;


import android.content.Context;

import com.google.gson.Gson;

import by.yegorov.nasa.BuildConfig;
import by.yegorov.nasa.core.dagger.PerApp;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@SuppressWarnings("unused")
@Module()
public class ApiModule {

    @Provides
    @PerApp
    ApiFactory provideApiFactory(Context context, Gson gson) {
        return new ApiFactory(
                context,
                BuildConfig.NEWS_TEST_API,
                BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE,
                gson
        );
    }

    @Provides
    @PerApp
    NewsApi provideAuthApi(ApiFactory apiFactory) {
        return apiFactory.createNewsApi();
    }
}
