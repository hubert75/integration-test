package com.binarapps.integrationtest;

import com.binarapps.integrationtest.client.IntegrationModule;
import com.binarapps.integrationtest.config.ConfigModule;
import com.binarapps.integrationtest.server.Server;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ConfigModule.class,
        IntegrationModule.class
})
public interface Factory {

    static Factory instance() {
        Config config = ConfigFactory.load();

        return DaggerFactory.builder()
                .configModule(new ConfigModule(config))
                .build();
    }

    Server server();
}
