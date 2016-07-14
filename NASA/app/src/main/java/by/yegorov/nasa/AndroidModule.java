package by.yegorov.nasa;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import dagger.Module;
import dagger.Provides;

/**
 * Module for all Android related provisions
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Module()
public class AndroidModule {
    @Provides
    SharedPreferences provideDefaultSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressWarnings("ConstantConditions")
    @Provides
    PackageInfo providePackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    DownloadManager provideDownloadManager(Context context) {
        return getSystemService(context, Context.DOWNLOAD_SERVICE);
    }

    @Provides
    TelephonyManager provideTelephonyManager(Context context) {
        return getSystemService(context, Context.TELEPHONY_SERVICE);
    }

    @SuppressWarnings("unchecked")
    private <T> T getSystemService(Context context, String serviceConstant) {
        return (T) context.getSystemService(serviceConstant);
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Context context) {
        return getSystemService(context, Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    InputMethodManager provideInputMethodManager(final Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    ApplicationInfo provideApplicationInfo(final Context context) {
        return context.getApplicationInfo();
    }

    @Provides
    AccountManager provideAccountManager(final Context context) {
        return AccountManager.get(context);
    }

    @Provides
    ClassLoader provideClassLoader(final Context context) {
        return context.getClassLoader();
    }

    @Provides
    NotificationManager provideNotificationManager(final Context context) {
        return getSystemService(context, Context.NOTIFICATION_SERVICE);
    }

    @Provides
    PackageManager providePackageManager(final Context context) {
        return context.getPackageManager();
    }

    @Provides
    AlarmManager provideAlarmManager(final Context context) {
        return getSystemService(context, Context.ALARM_SERVICE);
    }

    @Provides
    LocationManager provideLocationManager(final Context context) {
        return getSystemService(context, Context.LOCATION_SERVICE);
    }

    @Provides
    WifiManager provideWifiManager(final Context context) {
        return getSystemService(context, Context.WIFI_SERVICE);
    }

    @Provides
    Resources provideResources(final Context context) {
        return context.getResources();
    }

    @Provides
    DisplayMetrics provideDisplayMetrics(final Resources resources) {
        return resources.getDisplayMetrics();
    }

    @Provides
    SearchManager provideSearchManager(final Context context) {
        return getSystemService(context, Context.SEARCH_SERVICE);
    }
}

