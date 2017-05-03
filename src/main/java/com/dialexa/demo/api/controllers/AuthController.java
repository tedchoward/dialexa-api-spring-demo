package com.dialexa.demo.api.controllers;

import com.dialexa.demo.api.dtos.LoginDTO;
import com.dialexa.demo.api.dtos.SessionDTO;
import com.dialexa.demo.api.services.AuthService;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Created by ted on 4/18/17.
 */
@RestController
@RequestMapping("/sessions")
public class AuthController {
    private final AuthService authService;
    private final String jwtSecret;

    public AuthController(AuthService authService, @Value("${JWT_SECRET}") String jwtSecret) {
        this.authService = authService;
        this.jwtSecret = jwtSecret;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String createSession(@RequestBody @Valid LoginDTO loginDTO) {
        val session = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return new SessionDTO(session).toJWT(jwtSecret);
    }

    @DeleteMapping("{sessionId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteSession(@PathVariable("sessionId") UUID sessionId) {
        authService.logout(sessionId);
    }
}
