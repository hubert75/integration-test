package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;


@AutoValue
public abstract class PageIndexRequest {

    public static PageIndexRequest create(String pageIndex, Integer offsetIndex) {
        return new AutoValue_PageIndexRequest(pageIndex, offsetIndex);
    }

    @Nullable
    public abstract String pageIndex();

    public abstract Integer offsetIndex();


}
