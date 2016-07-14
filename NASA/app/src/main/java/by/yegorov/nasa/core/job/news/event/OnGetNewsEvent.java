package by.yegorov.nasa.core.job.news.event;


import java.util.List;

import by.yegorov.nasa.core.job.BaseJobEvent;
import by.yegorov.nasa.core.model.News;
import retrofit.RetrofitError;

@SuppressWarnings("WeakerAccess")
public class OnGetNewsEvent extends BaseJobEvent {
    public final String message;
    public final List<News> newses;

    public OnGetNewsEvent(boolean isInProgress, Throwable error, String message, List<News> newses) {
        super(isInProgress, error);
        this.message = message;
        this.newses = newses;
    }

    public static OnGetNewsEvent createProgress() {
        return new OnGetNewsEvent(true, null, null, null);
    }

    public static OnGetNewsEvent createWithSuccess(List<News> chats) {
        return new OnGetNewsEvent(false, null, null, chats);
    }

    public static OnGetNewsEvent createWithError(RetrofitError error) {
        return new OnGetNewsEvent(false, error, handleRetrofitError(error), null);
    }
}
