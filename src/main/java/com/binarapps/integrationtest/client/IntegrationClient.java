package com.binarapps.integrationtest.client;

import com.binarapps.integrationtest.http.dto.PageIndexRequest;
import com.binarapps.integrationtest.http.dto.PagedList;
import com.binarapps.integrationtest.http.utils.TransactionsToPagedListMapper;
import io.reactivex.Single;


public class IntegrationClient {
    private final RetrofitApi api;

    private final TransactionsToPagedListMapper mapper;

    public IntegrationClient(RetrofitApi api, TransactionsToPagedListMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    public Single<PagedList> getTransactions(String accountId, Integer pageSize, PageIndexRequest indexRequest) {
        return api.getTransactions(accountId, indexRequest.pageIndex())
                .map(mapper.mapTransactionsToPagedList(indexRequest.offsetIndex(), pageSize));
    }

}
