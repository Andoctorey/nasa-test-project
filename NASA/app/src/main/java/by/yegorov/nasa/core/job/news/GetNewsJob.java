package by.yegorov.nasa.core.job.news;


import android.content.Context;

import com.path.android.jobqueue.Params;
import com.path.android.jobqueue.RetryConstraint;

import javax.inject.Inject;

import by.yegorov.nasa.NasaApp;
import by.yegorov.nasa.core.job.BaseJob;
import by.yegorov.nasa.core.job.news.event.OnGetNewsEvent;
import by.yegorov.nasa.service.NewsService;
import retrofit.RetrofitError;

@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class GetNewsJob extends BaseJob {

    @Inject
    protected transient NewsService newsService;

    public GetNewsJob() {
        super(new Params(0));
    }

    @Override
    public void onAdded() {
        bus.post(OnGetNewsEvent.createProgress());
    }

    @Override
    public void onRun() throws Throwable {
        try {
            bus.post(OnGetNewsEvent.createWithSuccess(newsService.getNews()));
        } catch (RetrofitError error) {
            bus.post(OnGetNewsEvent.createWithError(error));
        }
    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.CANCEL;
    }

    @Override
    public void inject(Context context) {
        NasaApp.getComponent(context).inject(this);
    }
}
