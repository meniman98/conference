package com.jack.huncho.conference.repository;

import com.jack.huncho.conference.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
