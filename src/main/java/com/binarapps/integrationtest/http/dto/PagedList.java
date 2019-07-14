package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.List;

@AutoValue
public abstract class PagedList {
    public static PagedList create(String pageIndex, List<?> list) {
        return new AutoValue_PagedList(pageIndex, list);
    }

    @Nullable
    public abstract String pageIndex();

    @SerializedName("transactions")
    public abstract List<?> get();

    public static TypeAdapter<PagedList> typeAdapter(Gson gson) {
        return new AutoValue_PagedList.GsonTypeAdapter(gson);
    }
}
