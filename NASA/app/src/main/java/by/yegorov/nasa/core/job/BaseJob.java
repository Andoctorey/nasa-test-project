package by.yegorov.nasa.core.job;

import android.content.Context;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public abstract class BaseJob extends Job {

    @Inject
    protected transient Bus bus;

    protected BaseJob(Params params) {
        super(params);
    }

    public abstract void inject(Context context);
}
