package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Transactions {

    public static Transactions create(String pageIndex, List<Transaction> list, String accountId) {
        return new AutoValue_Transactions(pageIndex, list, accountId);
    }

    public abstract String pageIndex();

    @SerializedName("transactions")
    public abstract List<Transaction> get();

    public abstract String accountId();

    public static TypeAdapter<Transactions> typeAdapter(Gson gson) {
        return new AutoValue_Transactions.GsonTypeAdapter(gson);
    }


}
