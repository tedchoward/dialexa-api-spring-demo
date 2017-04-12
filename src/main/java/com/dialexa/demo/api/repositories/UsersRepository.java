package com.dialexa.demo.api.repositories;

import com.dialexa.demo.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by ted on 4/12/17.
 */
@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
