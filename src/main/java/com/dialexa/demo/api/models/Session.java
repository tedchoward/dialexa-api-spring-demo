package com.dialexa.demo.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by ted on 4/18/17.
 */
@Entity
@Table(name = "sessions")
@Getter
@Setter
public class Session extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID sessionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private OffsetDateTime expiresAt;
}
