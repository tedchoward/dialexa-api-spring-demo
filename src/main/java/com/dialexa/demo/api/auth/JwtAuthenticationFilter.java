package com.dialexa.demo.api.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Created by ted on 4/18/17.
 */
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String encodedToken = ((HttpServletRequest) request).getHeader(AUTHORIZATION);

        if (encodedToken != null) {
            final JwtAuthToken token = new JwtAuthToken(encodedToken);
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        chain.doFilter(request, response);
    }
}
