package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Payment {
    public abstract String currency();

    public abstract String amount();

    public static TypeAdapter<Payment> typeAdapter(Gson gson) {
        return new AutoValue_Payment.GsonTypeAdapter(gson);
    }
}
