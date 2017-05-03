package com.dialexa.demo.api.auth;

import com.dialexa.demo.api.dtos.SessionDTO;
import com.dialexa.demo.api.services.AuthService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by ted on 5/3/17.
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthService authService;

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            val token = (String) authentication.getCredentials();
            val sessionDTO = new SessionDTO(token, jwtSecret);
            val validSession = authService.validateSession(sessionDTO.getSessionId());
            return new UserAuthentication(sessionDTO);
        } catch (Exception e) {
            throw new BadCredentialsException("Failed to verify token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
