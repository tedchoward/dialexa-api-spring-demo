package com.dialexa.demo.api.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ted on 4/12/17.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditableEntity{
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID userId;

    @Email
    private String email;

    @NotEmpty
    private String passwordHash;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private OffsetDateTime loggedInAt;

    private OffsetDateTime archivedAt;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
