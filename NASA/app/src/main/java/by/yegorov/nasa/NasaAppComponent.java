package by.yegorov.nasa;


import by.yegorov.nasa.core.api.ApiModule;
import by.yegorov.nasa.core.dagger.PerApp;
import by.yegorov.nasa.core.image.ImageModule;
import by.yegorov.nasa.core.job.news.GetNewsJob;
import by.yegorov.nasa.ui.news.NewsAdapter;
import by.yegorov.nasa.ui.news.NewsDetailActivity;
import by.yegorov.nasa.ui.news.NewsDetailFragment;
import by.yegorov.nasa.ui.news.NewsListActivity;
import dagger.Component;

@PerApp
@Component(
        modules = {
                NasaModule.class,
                AndroidModule.class,
                ApiModule.class,
                ImageModule.class
        }
)
public interface NasaAppComponent {

    void inject(NewsListActivity newsListActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsDetailFragment newsDetailFragment);

    void inject(NewsAdapter newsAdapter);

    void inject(GetNewsJob getNewsJob);
}
