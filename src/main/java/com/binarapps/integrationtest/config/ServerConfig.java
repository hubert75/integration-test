package com.binarapps.integrationtest.config;

import com.google.auto.value.AutoValue;
import com.typesafe.config.Config;

@AutoValue
public abstract class ServerConfig {
    public static ServerConfig create(Config config) {
        return new AutoValue_ServerConfig(
                config.getConfig("http-server").getInt("port"));
    }

    public abstract int port();

}
