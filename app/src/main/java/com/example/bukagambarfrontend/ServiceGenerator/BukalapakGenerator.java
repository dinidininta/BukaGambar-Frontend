package com.example.bukagambarfrontend.ServiceGenerator;

import android.util.Base64;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by WIN8 on 5/27/2017.
 */

public class BukalapakGenerator {

    public static final String API_BASE_URL = "https://api.bukalapak.com/v2";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new OkClient(HttpTimeout.getTimeout()));

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String userid, String token) {
        if (userid != null && token != null) {
            // concatenate username and password with colon for authentication
            String credentials = userid + ":" + token;
            // create Base64 encodet string
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", basic);
                    request.addHeader("Content-Type", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

    public static <S> S createServiceWithoutHeader(Class<S> serviceClass, String userid, String token) {
        if (userid != null && token != null) {
            // concatenate username and password with colon for authentication
            String credentials = userid + ":" + token;
            // create Base64 encodet string
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", basic);
//                    request.addHeader("Content-Type", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
