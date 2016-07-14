package by.yegorov.nasa.service;

import java.util.List;

import javax.inject.Inject;

import by.yegorov.nasa.core.api.NewsApi;
import by.yegorov.nasa.core.model.News;


@SuppressWarnings("unused")
public class NewsService {

    private final NewsApi newsApi;

    @Inject
    public NewsService(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public List<News> getNews() {
        return newsApi.getNews().getItems();
    }
}
