package by.yegorov.nasa;


import by.yegorov.nasa.core.dagger.PerApp;
import by.yegorov.nasa.ui.news.NewsAdapter;
import by.yegorov.nasa.ui.news.NewsDetailActivity;
import by.yegorov.nasa.ui.news.NewsDetailFragment;
import by.yegorov.nasa.ui.news.NewsListActivity;
import dagger.Component;

@PerApp
@Component(
        modules = {
                NasaModule.class,
                AndroidModule.class
        }
)
public interface NasaAppComponent {

    void inject(NewsListActivity newsListActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsDetailFragment newsDetailFragment);

    void inject(NewsAdapter newsAdapter);
}
