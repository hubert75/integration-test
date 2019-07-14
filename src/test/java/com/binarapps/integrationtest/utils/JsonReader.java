package com.binarapps.integrationtest.utils;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public final class JsonReader {

    public static String loadJsonFile() {
        ClassLoader classLoader = JsonReader.class.getClassLoader();
        JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
        String result = null;
        try (FileReader reader = new FileReader(classLoader.getResource("transactions.json").getPath())) {
            JSONArray array = (JSONArray) jsonParser.parse(reader);
            result = array.toJSONString();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
