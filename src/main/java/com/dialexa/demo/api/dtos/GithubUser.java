package com.dialexa.demo.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by ted on 5/3/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GithubUser {
    private String login;
    private Long id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String type;
    private String name;
    private String blog;
    private String location;
    private Integer public_repos;
    private Integer public_gists;
}
