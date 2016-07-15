package by.yegorov.nasa.core.job;

import java.net.SocketException;

import by.yegorov.nasa.core.utils.ExceptionUtils;
import retrofit.RetrofitError;
import retrofit.client.Response;

@SuppressWarnings("unused")
public abstract class BaseJobEvent {
    public final boolean isInProgress;
    public Throwable error;

    protected BaseJobEvent(boolean isInProgress, Throwable error) {
        this.isInProgress = isInProgress;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public void resetError() {
        error = null;
    }

    public boolean hasNetworkError() {
        return hasError()
                && (error instanceof SocketException
                || error instanceof RetrofitError);
    }

    protected static String handleRetrofitError(RetrofitError error) {
        if (error.getKind() != RetrofitError.Kind.NETWORK && (error.getResponse() == null || error.getResponse().getStatus() != 504)) {
            String message = "";
            Response response = error.getResponse();
            if (response != null) {
                message += " " + response.getStatus();
            }
            message += " " + error.getUrl();
            ExceptionUtils.handleException(error, message);
            return message;
        }
        return null;
    }
}

