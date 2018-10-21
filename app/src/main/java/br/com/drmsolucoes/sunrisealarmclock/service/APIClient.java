package br.com.drmsolucoes.sunrisealarmclock.service;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.com.drmsolucoes.sunrisealarmclock.SunriseApplication;
import br.com.drmsolucoes.sunrisealarmclock.util.Utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafaelneiva on 31/05/17.
 */

public class APIClient {
    private static APIClient mInstance;

    public synchronized static APIClient getInstance() {
        return mInstance;
    }

    private Retrofit mRetrofit;
    private OkHttpClient mClient;

    public APIClient(@NonNull String baseUrl) {
        mInstance = this;
        mClient = getClient();

        GsonBuilder builder = new GsonBuilder();

        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(checkConnectionInterceptor)
//                .addInterceptor(requestIntercept)
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();
    }

    private final Interceptor checkConnectionInterceptor = chain -> {
        if (!Utils.isOnline(SunriseApplication.instance)) {
            throw new NoConnectionException();
        }
        return chain.proceed(chain.request());
    };

    private HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return mLogging;
    }

    private final Interceptor requestIntercept = chain -> {

        final Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        final Request request = requestBuilder.build();
        final Response response = chain.proceed(request);
        return response;

    };

    public void cancelAllRequests() {
        getInstance().mClient.dispatcher().cancelAll();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return mClient;
    }

}


