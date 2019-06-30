package com.binarapps.integrationtest.server;

import com.binarapps.integrationtest.http.LinkingRoute;

import javax.inject.Inject;

import spark.Spark;

public class Server {
    private final LinkingRoute linkingRoute;

    @Inject
    public Server(LinkingRoute linkingRoute) {
        this.linkingRoute = linkingRoute;
    }

    public void start() {
        Spark.port(8200);

        Spark.exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(e.getMessage());
        });
        Spark.get("link", linkingRoute);
    }
}
