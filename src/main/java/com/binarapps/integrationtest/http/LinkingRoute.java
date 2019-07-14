package com.binarapps.integrationtest.http;

import static java.util.Optional.ofNullable;

import com.binarapps.integrationtest.client.IntegrationClient;
import com.binarapps.integrationtest.http.dto.PagedList;

import java.util.Optional;
import java.util.function.Function;
import javax.inject.Inject;

import com.binarapps.integrationtest.http.utils.PageIndexTransformer;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.StringUtils;

public class LinkingRoute implements Route {
    private final IntegrationClient client;

    private final PageIndexTransformer transformer;

    @Inject
    public LinkingRoute(IntegrationClient client, PageIndexTransformer transformer) {
        this.client = client;
        this.transformer = transformer;
    }

    @Override
    public PagedList handle(Request request, Response response) {
        String accountId = ofNullable(request.queryMap("accountId"))
                .map(QueryParamsMap::value)
                .orElseThrow(() -> new IllegalArgumentException("Missing accountId query param"));
        Optional<String> pageIndex = ofNullable(request.queryMap("pageIndex"))
                .map(QueryParamsMap::value)
                .filter(StringUtils::isNotBlank);
        Integer pageSize = ofNullable(request.queryMap("pageSize"))
                .map(QueryParamsMap::value)
                .filter(StringUtils::isNotBlank).map(parsePageSizeToInteger())
                .filter(value -> value > 0)
                .orElse(5);
        return client.getTransactions(accountId, pageSize,
                transformer.prepareRequestParams(pageIndex)).blockingGet();
    }

    private Function<String, Integer> parsePageSizeToInteger() {
        return ps -> {
            try {
                return Integer.valueOf(ps);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("pageSize should be positive integer number");
            }
        };
    }

}
