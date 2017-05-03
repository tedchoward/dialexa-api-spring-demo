package com.dialexa.demo.api.auth;

import com.dialexa.demo.api.dtos.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;

/**
 * Created by ted on 5/3/17.
 */
@RequiredArgsConstructor
public class UserAuthentication implements Authentication {
    private final SessionDTO sessionDTO;
    private boolean authenticated = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sessionDTO.getScope().stream().map(r -> new SimpleGrantedAuthority(r)).collect(toSet());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return sessionDTO;
    }

    @Override
    public Object getPrincipal() {
        return sessionDTO.getUserId();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return sessionDTO.getUserId().toString();
    }
}
