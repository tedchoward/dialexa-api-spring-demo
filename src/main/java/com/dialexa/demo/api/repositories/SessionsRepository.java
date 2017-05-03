package com.dialexa.demo.api.repositories;

import com.dialexa.demo.api.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by ted on 4/18/17.
 */
@Repository
public interface SessionsRepository extends JpaRepository<Session, UUID> {
}
