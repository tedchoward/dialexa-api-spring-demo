package com.dialexa.demo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ted on 4/12/17.
 *
 */
@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello "+ name + "!";
    }
}
