package com.binarapps.integrationtest.client;

import com.binarapps.integrationtest.http.dto.Transactions;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("transactions")
    Single<Transactions> getTransactions(@Query("accountId") String accountId, @Query("pageIndex") String pageIndex);

}
