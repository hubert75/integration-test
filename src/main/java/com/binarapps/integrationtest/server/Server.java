package com.binarapps.integrationtest.server;

import com.binarapps.integrationtest.config.ServerConfig;
import com.binarapps.integrationtest.http.LinkingRoute;

import javax.inject.Inject;

import com.binarapps.integrationtest.http.utils.JsonResponseTransformer;
import com.google.gson.Gson;
import spark.Spark;

public class Server {
    private final LinkingRoute linkingRoute;

    private final Gson gson;

    private final ServerConfig serverConfig;

    @Inject
    public Server(LinkingRoute linkingRoute, Gson gson, ServerConfig serverConfig) {
        this.linkingRoute = linkingRoute;
        this.gson = gson;
        this.serverConfig = serverConfig;
    }

    public void start() {
        Spark.port(serverConfig.port());

        Spark.exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(e.getMessage());
        });
        Spark.get("link", linkingRoute, new JsonResponseTransformer(gson));
        Spark.after((req, res) -> res.type("application/json"));
    }
}
