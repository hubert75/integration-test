package com.binarapps.integrationtest.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class IntegrationModule {
    @Provides
    @Singleton
    IntegrationClient provideIntegrationClient(RetrofitApi api) {
        return new IntegrationClient(api);
    }

    @Provides
    @Singleton
    RetrofitApi provideRetrofitApi(Retrofit retrofit) {
        return retrofit.create(RetrofitApi.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://localhost:8080/")
                .client(client)
                .build();
    }
}
