/**
 *
 */
package com.csf.whoami.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.QuizQuestionEntity;

/**
 * @author tuan
 *
 */
public interface QuizQuestionRepository extends JpaRepository<QuizQuestionEntity, String> {

    List<QuizQuestionEntity> findAllByQuizId(Long id);
}
