/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.QuizTestEntity;

/**
 * @author tuan
 *
 */
public interface QuizTestRepository extends JpaRepository<QuizTestEntity, String> {

}
