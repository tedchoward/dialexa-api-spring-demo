package com.dialexa.demo.api.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ted on 4/18/17.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID roleId;
    @NotEmpty
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
