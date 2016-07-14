package by.yegorov.nasa.core.api;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import by.yegorov.nasa.core.utils.NetworkUtils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@SuppressWarnings("unused")
public class ApiFactory {
    private static final long CACHE_SIZE = 30 * 1024 * 1024;
    private final Context context;
    private final String endpoint;
    private final RestAdapter.LogLevel logLevel;
    private final RestAdapter restAdapter;
    private final Gson gson;
    private File cacheDir;

    @SuppressWarnings("SameParameterValue")
    public ApiFactory(Context context, String endpoint, RestAdapter.LogLevel logLevel, Gson gson) {
        this.context = context;
        this.endpoint = endpoint;
        this.logLevel = logLevel;
        this.gson = gson;
        if (context != null) {
            cacheDir = new File(context.getCacheDir(), "responses");
        }
        restAdapter = createRestAdapter();
    }

    public NewsApi createNewsApi() {
        return restAdapter.create(NewsApi.class);
    }

    private RestAdapter createRestAdapter() {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new AuthorizeWithTokenRequestInterceptor())
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(logLevel)
                .setClient(new OkClient(getSSLOkHttpClient()))
                .build();
    }

    private class AuthorizeWithTokenRequestInterceptor implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            if (context != null) {
                request.addHeader("Accept", "application/json;versions=1");
                if (NetworkUtils.isNetworkAvailable(context)) {
                    int maxAge = 60;
                    request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                } else {
                    int maxStale = 60 * 60 * 24 * 28;
                    request.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
                }
            }
        }
    }

    private class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long start = System.currentTimeMillis();
            Response response = chain.proceed(chain.request().newBuilder().build());
            Response.Builder builder = response.newBuilder();
            if (context != null) {
                builder.removeHeader("Pragma").removeHeader("Cache-Control");
            }
            return builder.build();
        }
    }

    private OkHttpClient getSSLOkHttpClient() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final SSLSocketFactory sslSocketFactory;
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();

            okHttpClient.setReadTimeout(30 * 1000, TimeUnit.MILLISECONDS);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @SuppressLint("BadHostnameVerifier")
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    //only for test
                    return true;
                }
            });

            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.interceptors().add(new CacheInterceptor());
            okHttpClient.networkInterceptors().add(new CacheInterceptor());
            if (context != null) {
                Cache cache = new Cache(cacheDir, CACHE_SIZE);
                okHttpClient.setCache(cache);
            }

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
