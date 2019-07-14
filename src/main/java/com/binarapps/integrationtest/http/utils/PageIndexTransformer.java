package com.binarapps.integrationtest.http.utils;

import com.binarapps.integrationtest.http.dto.PageIndexRequest;

import java.util.Optional;
import java.util.function.Function;

public final class PageIndexTransformer {

    public PageIndexRequest prepareRequestParams(Optional<String> pageIndex) {
        return pageIndex.map(p -> p.split("__", 2))
                .map(mapParamToRequestObject())
                .filter(r -> r.offsetIndex() >= 0)
                .orElse(PageIndexRequest.create(null, 0));
    }

    private Function<String[], PageIndexRequest> mapParamToRequestObject() {
        return s -> {
            if (s.length == 1) {
                return PageIndexRequest.create(s[0], 0);
            }
            try {
                return PageIndexRequest.create(s[0], Integer.valueOf(s[1]));
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("incorrect pageIndex param");
            }
        };
    }

}
