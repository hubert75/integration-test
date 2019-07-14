package com.binarapps.integrationtest.config;

import com.google.auto.value.AutoValue;
import com.typesafe.config.Config;

@AutoValue
public abstract class BusinessConfig {

    public static BusinessConfig create(Config config) {
        return new AutoValue_BusinessConfig(
                config.getConfig("business").getString("bank-url"));
    }

    public abstract String retrofitBaseUrl();

}
