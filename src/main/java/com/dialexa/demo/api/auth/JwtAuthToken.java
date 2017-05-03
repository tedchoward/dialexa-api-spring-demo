package com.dialexa.demo.api.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by ted on 5/3/17.
 */
public class JwtAuthToken extends AbstractAuthenticationToken {
    private final String token;

    public JwtAuthToken(String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
