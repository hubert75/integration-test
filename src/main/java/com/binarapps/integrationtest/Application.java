package com.binarapps.integrationtest;

public class Application {
    /**
     * App main entry.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Factory.instance()
                .server()
                .start();
    }
}
