package com.binarapps.integrationtest.config;

import com.typesafe.config.Config;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ConfigModule {
    private final Config config;

    public ConfigModule(Config config) {
        this.config = config;
    }

    @Provides
    @Singleton
    ServerConfig provideServerConfig() {
        return ServerConfig.create(config);
    }

    @Provides
    @Singleton
    BusinessConfig provideBusinessConfig() {
        return BusinessConfig.create(config);
    }
}
