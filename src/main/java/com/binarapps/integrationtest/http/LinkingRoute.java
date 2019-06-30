package com.binarapps.integrationtest.http;

import static java.util.Optional.ofNullable;

import com.binarapps.integrationtest.client.IntegrationClient;
import com.binarapps.integrationtest.http.dto.PagedList;
import com.binarapps.integrationtest.http.dto.Transaction;

import java.util.Optional;
import javax.inject.Inject;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.StringUtils;

public class LinkingRoute implements Route {
    private final IntegrationClient client;

    @Inject
    public LinkingRoute(IntegrationClient client) {
        this.client = client;
    }

    @Override
    public PagedList<Transaction> handle(Request request, Response response) throws Exception {
        String accountId = ofNullable(request.queryMap("accountId"))
                .map(QueryParamsMap::value)
                .orElseThrow(() -> new IllegalArgumentException("Missing accountId query param"));
        Optional<String> pageIndex = ofNullable(request.queryMap("pageIndex"))
                .map(QueryParamsMap::value)
                .filter(StringUtils::isNotBlank);

        return client.getTransactions(accountId,pageIndex).blockingGet();
    }
}
