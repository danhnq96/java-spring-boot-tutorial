package com.sprint1backend.repository.message;

import com.sprint1backend.entity.IconMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends JpaRepository<IconMessage, Long> {
}
