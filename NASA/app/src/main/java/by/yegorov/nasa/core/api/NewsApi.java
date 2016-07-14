package by.yegorov.nasa.core.api;

import by.yegorov.nasa.core.api.base.BaseApiResponse;
import by.yegorov.nasa.core.model.News;
import retrofit.http.GET;

@SuppressWarnings("UnusedReturnValue")
public interface NewsApi {

    @GET("/")
    BaseApiResponse<News> getNews();

}
