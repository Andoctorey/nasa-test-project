package by.yegorov.nasa;


import by.yegorov.nasa.core.dagger.PerApp;
import by.yegorov.nasa.ui.news.NewsDetailActivity;
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
}
