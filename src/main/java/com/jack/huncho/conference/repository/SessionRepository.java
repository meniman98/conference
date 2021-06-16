package com.jack.huncho.conference.repository;

import com.jack.huncho.conference.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
