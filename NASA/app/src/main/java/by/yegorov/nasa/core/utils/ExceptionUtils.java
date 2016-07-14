package by.yegorov.nasa.core.utils;

import by.yegorov.nasa.BuildConfig;

public class ExceptionUtils {

    public static void handleException(Exception e) {
        handleException(e, null);
    }

    public static void handleException(Exception e, String log) {
        if (!BuildConfig.DEBUG) {
            //TODO send to crash logging service
        } else {
            e.printStackTrace();
        }
    }
}
