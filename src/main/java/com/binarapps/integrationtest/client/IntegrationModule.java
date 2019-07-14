package com.binarapps.integrationtest.client;

import com.binarapps.integrationtest.config.BusinessConfig;
import com.binarapps.integrationtest.http.utils.AutoValueGsonFactory;
import com.binarapps.integrationtest.http.utils.PageIndexTransformer;
import com.binarapps.integrationtest.http.utils.TransactionsToPagedListMapper;
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
    IntegrationClient provideIntegrationClient(RetrofitApi api, TransactionsToPagedListMapper mapper) {
        return new IntegrationClient(api, mapper);
    }

    @Provides
    @Singleton
    RetrofitApi provideRetrofitApi(Retrofit retrofit) {
        return retrofit.create(RetrofitApi.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson, BusinessConfig serverConfig) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(serverConfig.retrofitBaseUrl())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    PageIndexTransformer providePageIndexTransformer(){
        return new PageIndexTransformer();
    }

    @Provides
    @Singleton
    TransactionsToPagedListMapper provideMapper(){
        return new TransactionsToPagedListMapper();
    }
}
