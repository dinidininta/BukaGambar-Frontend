package com.example.bukagambarfrontend.ServiceGenerator;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by WIN8 on 5/28/2017.
 */

public class HttpTimeout {

    public static OkHttpClient getTimeout(){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

        return okHttpClient;
    }

}
