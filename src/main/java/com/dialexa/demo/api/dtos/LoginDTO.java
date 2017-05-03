package com.dialexa.demo.api.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ted on 4/18/17.
 */
@Getter
@Setter
public class LoginDTO {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
