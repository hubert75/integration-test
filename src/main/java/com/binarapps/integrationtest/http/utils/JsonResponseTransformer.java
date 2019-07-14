package com.binarapps.integrationtest.http.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {

    private Gson gson;

    public JsonResponseTransformer(Gson gson){
        this.gson = gson;
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
