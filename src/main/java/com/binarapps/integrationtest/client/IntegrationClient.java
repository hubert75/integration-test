package com.binarapps.integrationtest.client;

import static java.util.Collections.emptyList;

import com.binarapps.integrationtest.http.dto.PagedList;
import com.binarapps.integrationtest.http.dto.Transaction;
import io.reactivex.Single;

import java.util.Optional;

public class IntegrationClient {
    private final RetrofitApi api;

    public IntegrationClient(RetrofitApi api) {
        this.api = api;
    }

    public Single<PagedList<Transaction>> getTransactions(
            String accountId,
            Optional<String> pageIndex) {
        return Single.just(PagedList.create("", emptyList()));
    }
}
