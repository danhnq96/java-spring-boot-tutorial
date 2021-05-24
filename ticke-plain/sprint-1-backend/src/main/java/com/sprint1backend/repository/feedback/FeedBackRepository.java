package com.sprint1backend.repository.feedback;

import com.sprint1backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
}
