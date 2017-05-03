package com.dialexa.demo.api.services;

import com.dialexa.demo.api.models.Session;
import com.dialexa.demo.api.repositories.SessionsRepository;
import com.dialexa.demo.api.repositories.UsersRepository;
import de.mkammerer.argon2.Argon2;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by ted on 4/18/17.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final SessionsRepository sessionsRepository;
    private final UsersRepository usersRepository;
    private final Argon2 argon2;

    public Session login(@NonNull String email, @NonNull String password) {
        val user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("No user found for email '" + email + "'");
        }

        val match = argon2.verify(user.getPasswordHash(), password);
        if (!match) {
            throw new IllegalArgumentException("Password does not match");
        }

        user.setLoggedInAt(OffsetDateTime.now());
        usersRepository.save(user);

        val session = new Session();
        session.setUser(user);
        session.setExpiresAt(OffsetDateTime.now().plusDays(1));

        val savedSession = sessionsRepository.save(session);

        return savedSession;
    }

    public void logout(UUID sessionId) {
        sessionsRepository.delete(sessionId);
    }

    public boolean validateSession(UUID sessionId) {
        val session = sessionsRepository.findOne(sessionId);
        if (session == null) {
            return false;
        }

        val validSession = session.getExpiresAt().isAfter(OffsetDateTime.now());
        return validSession;
    }
}
