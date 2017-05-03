package com.dialexa.demo.api.controllers;

import com.dialexa.demo.api.dtos.GithubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ted on 5/3/17.
 */
@RestController
@RequestMapping(value = "/github", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GithubController {
    private final RestTemplate restTemplate;

    @GetMapping("/emoji")
    public Map<String, Object> getAllEmoji() {
        return restTemplate.getForObject("https://api.github.com/emojis", Map.class);
    }

    @GetMapping("/users")
    public GithubUser getUser(@RequestParam("userName") String userName) {
        return restTemplate.getForObject("https://api.github.com/users/{userName}", GithubUser.class, userName);
    }
}
