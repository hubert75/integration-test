package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;
import java.util.List;

@AutoValue
public abstract class PagedList<T> {
    public static <T> PagedList<T> create(String pageIndex, List<T> list) {
        return new AutoValue_PagedList<>(pageIndex, list);
    }

    public abstract String pageIndex();

    public abstract List<T> get();

    public static <T> TypeAdapter<PagedList> typeAdapter(Gson gson, Type[] types) {
        return new AutoValue_PagedList.GsonTypeAdapter(gson, types);
    }
}
