package com.dialexa.demo.api.controllers;

import com.dialexa.demo.api.dtos.UserDTO;
import com.dialexa.demo.api.models.User;
import com.dialexa.demo.api.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ted on 4/12/17.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UsersController {
    private final UsersRepository usersRepository;

    @GetMapping
    public Page<UserDTO> getUsers(Pageable pageable) {
        return usersRepository.findAll(pageable).map(u -> new UserDTO(u));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDTO createUser(@RequestBody @Valid User user) {
        val savedUser = usersRepository.save(user);
        return new UserDTO(savedUser);
    }

    @GetMapping("/byEmail")
    public UserDTO getUser(@RequestParam("email") String email) {
        val user = usersRepository.findByEmail(email);
        return new UserDTO(user);
    }

}
