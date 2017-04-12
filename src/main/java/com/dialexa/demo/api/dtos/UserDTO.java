package com.dialexa.demo.api.dtos;

import com.dialexa.demo.api.models.User;
import lombok.Value;

import java.util.UUID;

/**
 * Created by ted on 4/12/17.
 */
@Value
public class UserDTO {
    private final UUID userId;
    private final String email;
    private final String firstName;
    private final String lastName;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
