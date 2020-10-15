package com.springjwt.expensetrackerapi.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @PostMapping
    public String registerUser(@RequestBody Map<String, Object> useMap) {

        return "asd";
    }
}
