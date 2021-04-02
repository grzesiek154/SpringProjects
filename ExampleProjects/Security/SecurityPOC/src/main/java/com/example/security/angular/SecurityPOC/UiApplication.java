package com.example.security.angular.SecurityPOC;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UiApplication {

    public Map<String,Object> home() {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");

        return model;
    }

}
