package com.dialexa.demo.api.dtos;

import com.dialexa.demo.api.models.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import lombok.val;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Created by ted on 4/18/17.
 */
@Value
public class SessionDTO {
    private static final String AUDIENCE = "5e98ddbd-2404-401c-8146-dbe7a6f7f561";
    private static final String ISSUER = "http://127.0.0.1:8080";

    private final UUID sessionId;
    private final UUID userId;
    private final Date issuedAt;
    private final Date expiresAt;
    private final Set<String> scope;

    public SessionDTO(Session session) {
        this.sessionId = session.getSessionId();
        this.userId = session.getUser().getUserId();
        this.issuedAt = Date.from(session.getCreatedAt().toInstant());
        this.expiresAt = Date.from(session.getExpiresAt().toInstant());
        this.scope = session.getUser().getRoles().stream().map(r -> r.getName()).collect(toSet());
    }

    public SessionDTO(String token, String jwtSecret) {
        val decodedToken = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

        final Collection<String> scope = decodedToken.getBody().get("scope", Collection.class);

        this.sessionId = UUID.fromString(decodedToken.getBody().getId());
        this.userId = UUID.fromString(decodedToken.getBody().getSubject());
        this.issuedAt = decodedToken.getBody().getIssuedAt();
        this.expiresAt = decodedToken.getBody().getExpiration();
        this.scope = new HashSet<String>(scope);
    }

    public String toJWT(String jwtSecret) {
        return Jwts.builder()
                .setId(sessionId.toString())
                .setIssuer(ISSUER)
                .setAudience(AUDIENCE)
                .setSubject(userId.toString())
                .claim("scope", scope)
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
